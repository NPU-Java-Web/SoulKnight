package org.example.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinate {
    private String key;
    private int x;
    private int y;

    public double getDistance(double x2, double y2) {
        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
    }
}