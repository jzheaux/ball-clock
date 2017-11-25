package com.joshcummings.ballclock.view;

import com.google.gson.Gson;
import com.joshcummings.ballclock.fsm.BallClockMachine;
import com.joshcummings.ballclock.fsm.TimeLimitTerminus;
import com.joshcummings.ballclock.metrics.Timer;

/**
 * A view for rendering end user output to a console
 */
public class PlainCommandLineView implements View {
    private BallClockMachineJsonRenderer renderer = new BallClockMachineJsonRenderer(new Gson());
        
    @Override
    public void renderMachine(BallClockMachine machine) {
        if ( machine.terminus() instanceof TimeLimitTerminus ) {
            System.out.println(renderer.toJson(machine));
        } else {
            System.out.printf("%d balls cycle after %.0f days.%n", machine.hopper().balls().size(), machine.ts() / (60 * 24d));
        }
    }

    @Override
    public void renderTimer(Timer timer) {
       long time = timer.snapped();
       System.out.printf("Completed in %d milliseconds (%.3f seconds)%n", time, time / 1000d);
    }
    
    @Override
    public void renderErrorMessage(String message) {
        System.err.println(message);
    }
}
