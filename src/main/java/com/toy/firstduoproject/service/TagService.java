package com.toy.firstduoproject.service;

import com.toy.firstduoproject.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;


}
