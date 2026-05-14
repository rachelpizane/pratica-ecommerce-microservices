package edu.rachelpizane.icompras.pedidos.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private String field;

    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
    }
}
