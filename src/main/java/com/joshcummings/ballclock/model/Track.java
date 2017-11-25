package com.joshcummings.ballclock.model;

import java.util.ArrayList;
import java.util.Collection;

import com.joshcummings.ballclock.util.CappedArrayDeque;

/**
 * A model for any non-hopper track in the ball clock
 */
public class Track {
    private CappedArrayDeque<Ball> balls;
    
    /**
     * The track that the extra ball should overflow into.
     */
    private Track next;
    
    /**
     * The track that the remaining balls should overflow into. Note that if {@param next} is null,
     * then the extra ball subsequently overflows to here.
     */
    private Hopper hopper;
    
    public Track(int size, Track next, Hopper hopper) {
        balls = new CappedArrayDeque<>(size);
        this.next = next;
        this.hopper = hopper;
    }
    
    public void addBall(Ball ball) {
        if ( !balls.offerLast(ball) ) {
            while ( !balls.isEmpty() ) {
                hopper.returnBall(balls.pollLast());
            }
            if ( next == null ) {
                hopper.returnBall(ball);
            } else {
                next.addBall(ball);
            }
        }
    }
    
    public Collection<Ball> balls() {
        return new ArrayList<>(balls);
    }
    
    public String toString() {
        return balls.toString();
    }
}
