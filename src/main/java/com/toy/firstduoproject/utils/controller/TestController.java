package com.toy.firstduoproject.utils.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test")
    public List<Long> test1(){
        List<Long> testList = new ArrayList<>();
        testList.add(1L);
        testList.add(2L);
        return testList;
    }
}
