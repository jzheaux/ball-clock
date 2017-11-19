package com.joshcummings.ballclock.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * A model for the main track or hopper in the ball clock
 */
public class Hopper {
    private Queue<Integer> balls;
    private int size;
    
    public Hopper(int size) {
        if ( size < 27 || size > 127 ) {
            throw new IllegalArgumentException("Valid range for balls is [27,127]");
        }
        
        balls = new ArrayBlockingQueue<>(size);
        this.size = size;
        for ( int i = 0; i < size; i++ ) {
            balls.offer(i);
        }
    }
    
    public boolean isFull() {
        return balls.size() == size;
    }
    
    public void sendBall(Track track) {
        track.addBall(balls.poll());
    }
    
    public void returnBall(Integer ball) {
        balls.offer(ball);
    }
    
    public Collection<Integer> balls() {
        return new ArrayList<>(balls);
    }
}
