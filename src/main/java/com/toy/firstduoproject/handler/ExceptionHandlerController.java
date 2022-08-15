package com.toy.firstduoproject.handler;

import com.toy.firstduoproject.handler.ex.ExistException;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.handler.ex.PostEmptyException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    //
//    @ExceptionHandler(BindException.class)
//    public String validationException(BindException e, Model model){
//        BindingResult bindingResult = e.getBindingResult();
//
//        model.addAttribute("error",bindingResult);
//        StringBuilder builder = new StringBuilder();
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            builder.append("[");
//            builder.append(fieldError.getField());
//            builder.append("](은)는 ");
//            builder.append(fieldError.getDefaultMessage());
//            builder.append(" 입력된 값: [");
//            builder.append(fieldError.getRejectedValue());
//            builder.append("]");
//        }
//
//        return "signupForm";
//    }
}
