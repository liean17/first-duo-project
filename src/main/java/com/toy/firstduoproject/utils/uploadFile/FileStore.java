package com.toy.firstduoproject.utils.uploadFile;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {

    private String fileDir = "";

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFileName(originalFilename);

        return storeFilename;
    }

    public String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractedExt(originalFilename);
        return uuid + "." + ext;
    }

    public String extractedExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos+1);
    }

//    public List<Image> storeFiles(List<MultipartFile> attachFiles) throws Exception{
//
//    }
}
