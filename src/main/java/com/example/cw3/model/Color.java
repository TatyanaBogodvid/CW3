package com.example.cw3.model;

import org.springframework.lang.Nullable;

public enum Color {
    BLACK, WHITE, RED, GREEN, BLUE;

    @Nullable
    public static Color parse(String color){
        for(Color c : values()){
            if(c.name().equals(color)){
                return c;
            }
        }
        return null;
    }
}
