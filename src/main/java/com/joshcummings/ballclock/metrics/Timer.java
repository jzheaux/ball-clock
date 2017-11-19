package com.joshcummings.ballclock.metrics;

/**
 * A simple class for starting and stopping a timer
 */
public class Timer {
    private long now = System.currentTimeMillis();

    public void start() {
        now = System.currentTimeMillis();
    }

    public long snap() {
        return System.currentTimeMillis() - now;
    }
}
