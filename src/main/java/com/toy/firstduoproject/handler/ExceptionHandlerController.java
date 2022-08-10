package com.toy.firstduoproject.handler;

import com.toy.firstduoproject.handler.ex.ExistException;
import com.toy.firstduoproject.handler.ex.MemberDifferentException;
import com.toy.firstduoproject.handler.ex.PostEmptyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(ExistException.class)
    public String existException(ExistException e){
        return "중복오류입니다.";
    }

    @ExceptionHandler(MemberDifferentException.class)
    public String differentException(MemberDifferentException e) {return "글을 수정할 권한이 없습니다.";}

    @ExceptionHandler(PostEmptyException.class)
    public String postEmptyException(PostEmptyException e){return "제목 혹은 내용이 비어있습니다.";}
}
