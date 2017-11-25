package com.joshcummings.ballclock.fsm;

import com.joshcummings.ballclock.model.Hopper;
import com.joshcummings.ballclock.model.Track;

/**
 * The state machine representing a ball clock
 */
public class BallClockMachine {
    private Terminus terminus;
    private Hopper   hopper;
    private Track    oneHour;
    private Track    fiveMinutes;
    private Track    oneMinute;

    private State    current;

    public BallClockMachine(int size, Terminus terminus) {
        this.hopper = new Hopper(size);
        this.oneHour = new Track(11, null, hopper);
        this.fiveMinutes = new Track(11, oneHour, hopper);
        this.oneMinute = new Track(4, fiveMinutes, hopper);

        this.terminus = terminus;
        this.current = new State(hopper);
    }

    public boolean transition() {
        // not convinced that creating an immutable state object is the "right" way.
        // I like the immutability, but it creates a couple of representation headaches.
        
//        current = new State(ts++, hopper); 
        if (terminus.isTerminus(current)) {
            return false;
        } else {
            current.next();

            hopper.sendBall(oneMinute);
            return true;
        }
    }

    public Terminus terminus() {
        return terminus;
    }
    
    public Hopper hopper() {
        return hopper;
    }
    
    public Track oneMinute() {
        return oneMinute;
    }
    
    public Track fiveMinutes() {
        return fiveMinutes;
    }
    
    public Track oneHour() {
        return oneHour;
    }
    public Integer ts() {
        return current.ts();
    }
}
