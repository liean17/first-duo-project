package com.toy.firstduoproject.handler;

import com.toy.firstduoproject.handler.ex.ExistException;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.handler.ex.PostEmptyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ExistException.class)
    public String existException(String msg) {
        return msg;
    }

    @ExceptionHandler(NoPermissionException.class)
    public String noPermissionException(String msg) {
        return msg;
    }

    @ExceptionHandler(PostEmptyException.class)
    public String postEmptyException(String msg) {
        return msg;
    }
}
