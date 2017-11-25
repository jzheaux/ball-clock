package com.joshcummings.ballclock.model;

import java.util.ArrayList;
import java.util.Collection;

import java.util.ArrayDeque;

/**
 * A model for the main track or hopper in the ball clock
 */
public class Hopper {
    private ArrayDeque<Ball> balls;
    private int numInOrder;
    private int size;
    
    public Hopper(int size) {
        if ( size < 27 || size > 127 ) {
            throw new IllegalArgumentException("Valid range for balls is [27,127]");
        }
        
        balls = new ArrayDeque<>(size);
        this.size = size;
        for ( int i = 0; i < size; i++ ) {
            balls.offer(new Ball(i));
        }
    }
    
    public boolean isFull() {
        return balls.size() == size;
    }
    
    public boolean isInOrder() {
        return numInOrder == size;
    }
    
    public Ball next() {
        return balls.pollFirst();
    }
    
    public ArrayDeque<Ball> sendBall(Track track) {
        return track.addBall(balls.pollFirst());
    }
    
    public void returnBall(Ball ball) {
        if ( ball.number() == 0 ) {
            numInOrder = 1;
        } else if ( balls.peekLast().number() + 1 == ball.number() ) {
            numInOrder++;
        }
        balls.offerLast(ball);
    }
    
    public Collection<Ball> balls() {
        return new ArrayList<>(balls);
    }
}
