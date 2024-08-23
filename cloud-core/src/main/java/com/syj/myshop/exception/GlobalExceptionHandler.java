package com.syj.myshop.exception;

import com.syj.myshop.common.IErrorCode;
import com.syj.myshop.common.ServerException;
import com.syj.myshop.common.ErrorCode;
import com.syj.myshop.msg.R;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice@Slf4j
public class GlobalExceptionHandler {


    /**
     * 创建一个带有特定 HTTP 状态码的 ResponseEntity 对象。
     *
     * @param r         The R object to wrap.
     * @param httpStatus The HTTP status code to use.
     * @return A ResponseEntity with the given R object and HTTP status code.
     */
    private <U> ResponseEntity<R<U>> createResponseEntity(R<U> r, HttpStatus httpStatus) {
        return new ResponseEntity<>(r, httpStatus);
    }

    @ExceptionHandler(value = ServerException.class)
    public ResponseEntity<R<String>> handleServerException(ServerException exception) {
        IErrorCode errorCode = exception.getErrorCode();
        R<String> response = buildErrorResponse(errorCode.getCode(), errorCode.getMsg(), null);
        return createResponseEntity(response, HttpStatus.valueOf(errorCode.getCode()));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<R<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        R<Map<String, String>> response = buildErrorResponse(ErrorCode.ILLEGAL_ARGUMENT.getCode(),
                                                             ErrorCode.ILLEGAL_ARGUMENT.getMsg(), errors);
        return createResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<R<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        R<Map<String, String>> response = buildErrorResponse(ErrorCode.ILLEGAL_ARGUMENT.getCode(),
                                                             ErrorCode.ILLEGAL_ARGUMENT.getMsg(), errors);
        return createResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<R<String>> handleAllExceptions(Exception exception) {
        log.error("系统异常-->",exception);
        R<String> response = buildErrorResponse(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg(), null);
        return createResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private <U> R<U> buildErrorResponse(int code, String message, U data) {
        R<U> response = new R<>();
        response.setCode(code);
        response.setMsg(message);
        response.setData(data);
        return response;
    }
}
