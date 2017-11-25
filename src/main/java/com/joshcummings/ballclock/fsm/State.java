package com.joshcummings.ballclock.fsm;
import com.joshcummings.ballclock.model.Hopper;

public class State {
    private int             ts = 0;
    private Hopper hopper;

    public State(Hopper hopper) {
        this.hopper = hopper;
    }

    public Integer ts() {
        return ts;
    }

    public void next() {
        ts++;
    }
    
    public Hopper hopper() {
        return hopper;
    }
}
