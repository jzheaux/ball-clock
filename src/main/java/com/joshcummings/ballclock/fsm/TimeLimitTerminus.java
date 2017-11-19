package com.joshcummings.ballclock.fsm;

/**
 * A terminus that keeps track of the number of states visited
 * so far, terminating when a given number of states has been reached.
 */
public class TimeLimitTerminus implements Terminus {
    private int limit;

    public TimeLimitTerminus(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean isTerminus(State state) {
        return state.ts() >= limit;
    }
}
