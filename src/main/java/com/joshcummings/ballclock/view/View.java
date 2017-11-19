package com.joshcummings.ballclock.view;

import com.joshcummings.ballclock.fsm.BallClockMachine;
import com.joshcummings.ballclock.metrics.Timer;

/**
 * A contract for describing how to send the end user updates regarding the application's progress and results
 */
public interface View {
    void renderMachine(BallClockMachine machine);
    
    void renderTimer(Timer timer);
    
    void renderErrorMessage(String message);
    

}
