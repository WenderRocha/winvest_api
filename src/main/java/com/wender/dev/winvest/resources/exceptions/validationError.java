package com.wender.dev.winvest.resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class validationError extends StandardError{

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public validationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
