package org.example.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinate {
    private String key;
    private int x;
    private int y;
    private int blood;

}