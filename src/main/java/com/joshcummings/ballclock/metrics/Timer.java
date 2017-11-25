package com.joshcummings.ballclock.metrics;

/**
 * A simple class for starting and stopping a timer
 */
public class Timer {
    private long now = System.currentTimeMillis();
    private long snap;
    
    public void start() {
        now = System.currentTimeMillis();
    }

    public long snap() {
        return snap = System.currentTimeMillis() - now;
    }
    
    public long snapped() {
        return snap;
    }
}
