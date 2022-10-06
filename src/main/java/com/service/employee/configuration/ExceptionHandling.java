package com.service.employee.configuration;

import com.service.employee.dto.response.ErrorData;
import com.service.employee.dto.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeException.class)
    protected ResponseEntity<Object> handleController(EmployeeException ex, WebRequest request) {

        var response = new ResponseBody<>();
        response.setFailResponse(ErrorData.builder()
                .msg(ex.getMessage())
                .build());
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.OK, request);

    }
}
