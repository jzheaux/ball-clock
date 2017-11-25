package com.joshcummings.ballclock.model;

import java.util.ArrayList;
import java.util.Collection;

import com.joshcummings.ballclock.util.CappedArrayDeque;

/**
 * A model for the main track or hopper in the ball clock
 */
public class Hopper {
    private CappedArrayDeque<Ball> balls;
    private int numInOrder;
    private int size;
    
    public Hopper(int size) {
        if ( size < 27 || size > 127 ) {
            throw new IllegalArgumentException("Valid range for balls is [27,127]");
        }
        
        balls = new CappedArrayDeque<>(size);
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
    
    public void sendBall(Track track) {
        track.addBall(balls.poll());
    }
    
    public void returnBall(Ball ball) {
        if ( ball.number() == 0 ) {
            numInOrder=1;
        } else if ( numInOrder >= 1 && balls.peekLast().number() + 1 == ball.number() ) {
            numInOrder++;
        }
        balls.offer(ball);
    }
    
    public Collection<Ball> balls() {
        return new ArrayList<>(balls);
    }
    
    public boolean equals(Object obj) {
        if ( obj instanceof Hopper ) {
            return ((Hopper)obj).balls.equals(this.balls);
        }
        if ( obj instanceof CappedArrayDeque ) {
            return obj.equals(this.balls);
        }
        return false;
    }
}
