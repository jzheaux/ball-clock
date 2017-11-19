package com.joshcummings.ballclock.fsm;

import java.util.HashSet;
import java.util.Set;

/**
 * A terminus that keeps track of visited states, declaring when a cycle has been found.
 */
public class CycleTerminus implements Terminus {
    private Set<State> states = new HashSet<>();
    private int        size;

    public CycleTerminus(int size) {
        this.size = size;
    }

    @Override
    public boolean isTerminus(State state) {
        if (state.hopper().size() < size) {
            return false;
        }

        return !states.add(state);
    }
}
