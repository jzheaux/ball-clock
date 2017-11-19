package com.joshcummings.ballclock.fsm;

/**
 * A contract for deciding when to end the simulation
 */
public interface Terminus {
    boolean isTerminus(State state);
}
