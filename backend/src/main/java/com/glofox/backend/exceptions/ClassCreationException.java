package com.glofox.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClassCreationException extends RuntimeException {

  public ClassCreationException(String message) {
    super(message);

  }
}
