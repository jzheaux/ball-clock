package com.joshcummings.ballclock.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * A model for any non-hopper track in the ball clock
 */
public class Track {
    private Deque<Integer> balls;
    
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
        balls = new LinkedBlockingDeque<>(size);
        this.next = next;
        this.hopper = hopper;
    }
    
    public Collection<Integer> addBall(Integer ball) {
        Collection<Integer> backToHopper = new ArrayList<Integer>();
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
        return backToHopper;
    }
    
    public Collection<Integer> balls() {
        return new ArrayList<>(balls);
    }
    
    public String toString() {
        return balls.toString();
    }
}
