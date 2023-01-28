package com.example.cw3.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class Socks {
    private Size size;
    private Color color;
    private int cottonPart;

    public Socks(Size size, Color color, int cottonPart) {
        this.size = size;
        this.color = color;
        this.cottonPart = cottonPart;
    }
}
