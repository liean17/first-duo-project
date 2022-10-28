package com.toy.firstduoproject.posts.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.toy.firstduoproject.image.entity.Image;
import com.toy.firstduoproject.member.entity.Member;
import com.toy.firstduoproject.postType.entity.PostType;
import com.toy.firstduoproject.image.repository.ImageRepository;
import com.toy.firstduoproject.posts.dto.PostSaveRequestDto;
import com.toy.firstduoproject.posts.dto.PostUpdateRequestDto;
import com.toy.firstduoproject.posts.entity.Posts;
import com.toy.firstduoproject.posts.repository.PostRepository;
import com.toy.firstduoproject.utils.uploadFile.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;

    //C
    public Posts createPost(PostSaveRequestDto requestDto, Member member) throws IOException {

        Posts post = Posts.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .postType(requestDto.getPostType())
                .build();

        Posts savedPost = postRepository.save(post);
        List<MultipartFile> attachFiles = requestDto.getAttachFiles();
        extracted(attachFiles, savedPost);

        return savedPost;
    }

    private void extracted(List<MultipartFile> attachFiles, Posts savedPost) throws IOException {

        for(MultipartFile file : attachFiles) {
            String storeFilename = fileStore.storeFile(file);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket,storeFilename,file.getInputStream(),objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String imagePath = amazonS3Client.getUrl(S3Bucket, storeFilename).toString();

            Image image = Image.builder()
                    .storeFilename(imagePath)
                    .posts(savedPost)
                    .build();
            imageRepository.save(image);
        }
    }


    //R
    public Posts findPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("글이 존재하지 않습니다."));
    }

    //Rs
    public Page<Posts> findAll(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize(), Sort.Direction.DESC, "id"); // <- Sort 추가
        return postRepository.findAll(pageable);
    }

    /**
     *
     * @return 타입이 공지인 Post 의 List
     */
    public List<Posts> findNotice(){
        return postRepository.findAllByPostType(PostType.NOTICE);
    }

    //U
    public void updatePost(Long postId, PostUpdateRequestDto requestDto){
        Posts post = postRepository.findById(postId).orElseThrow();

        String title = requestDto.getTitle();
        String content = requestDto.getContent();

        post.update(title,content);
        post.changePostType(requestDto.getPostType());
        postRepository.save(post);
        //return post;
    }

    //D
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
