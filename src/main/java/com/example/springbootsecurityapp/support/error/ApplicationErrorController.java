package com.example.springbootsecurityapp.support.error;

import com.example.springbootsecurityapp.dto.ErrorRS;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ApplicationErrorController extends AbstractErrorController {
  public ApplicationErrorController(final ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping
  public ResponseEntity<ErrorRS> error(final HttpServletResponse response) {
    final HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());
    return ResponseEntity.status(httpStatus).body(new ErrorRS("global_error"));
  }
}