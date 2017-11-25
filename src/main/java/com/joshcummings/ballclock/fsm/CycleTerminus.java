package com.joshcummings.ballclock.fsm;

/**
 * A terminus that keeps track of visited states, declaring when a cycle has been found.
 */
public class CycleTerminus implements Terminus {  
    public CycleTerminus(int size) { 
    }

    @Override
    public boolean isTerminus(State state) {
        if (!state.hopper().isFull()) {
            return false;
        }

        return state.hopper().isInOrder();
    }
}
