package com.joshcummings.ballclock.model;

public class Ball {
    private final int number;
    
    public Ball(int number) {
        this.number = number;
    }
    
    public int number() {
        return number;
    }
    
    public String toString() {
        return String.valueOf(number);
    }
}
