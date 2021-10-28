package com.wender.dev.winvest.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultOperation {
    LOSS(0, "LOSS"),
    WIN(1, "WIN");

    private Integer code;
    private String description;


    public static ResultOperation toEnum(Integer code){
        if(code == null){
            return null;
        }

        for (ResultOperation x : ResultOperation.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Result Operation invalid! " + code);
    }
}
