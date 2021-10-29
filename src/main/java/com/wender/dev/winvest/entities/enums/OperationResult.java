package com.wender.dev.winvest.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationResult {
    LOSS(0, "LOSS"),
    WIN(1, "WIN"),
    DRAW(2, "DRAW");

    private Integer code;
    private String description;


    public static OperationResult toEnum(Integer code){
        if(code == null){
            return null;
        }

        for (OperationResult x : OperationResult.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Result Operation invalid! " + code);
    }
}
