package com.joshcummings.ballclock.fsm;

import java.util.ArrayList;
import java.util.Collection;

import com.joshcummings.ballclock.model.Hopper;

public class State {
    private final Integer             ts;
    private final Collection<Integer> hopper;

    public State(Integer ts, Hopper hopper) {
        this.ts = ts;
        this.hopper = new ArrayList<>(hopper.balls());
    }

    public Integer ts() {
        return ts;
    }

    public Collection<Integer> hopper() {
        return hopper;
    }

    public int hashCode() {
        return hopper.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof State) {
            return ((State)obj).hopper.equals(this.hopper);
        }
        return false;
    }
}
