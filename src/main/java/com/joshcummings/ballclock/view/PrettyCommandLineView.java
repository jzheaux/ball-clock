package com.joshcummings.ballclock.view;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import com.google.gson.GsonBuilder;
import com.joshcummings.ballclock.fsm.BallClockMachine;
import com.joshcummings.ballclock.fsm.TimeLimitTerminus;
import com.joshcummings.ballclock.metrics.Timer;

/**
 * A view for rendering end user output to a console with some basic highlighting for easier readability
 */
public class PrettyCommandLineView implements View {
    private BallClockMachineJsonRenderer renderer = 
        new BallClockMachineJsonRenderer(
            new GsonBuilder().setPrettyPrinting().create());
    
    @Override
    public void renderMachine(BallClockMachine machine) {
        if ( machine.terminus() instanceof TimeLimitTerminus ) {
            System.out.println(renderer.toJson(machine));
        } else {
            System.out.println(ansi().fg(GREEN)
                                .a(machine.hopper().balls().size()).reset()
                                .a(" balls cycle after ").fg(GREEN)
                                .a(String.format("%.0f", machine.ts() / (60 * 24d))).reset()
                                .a(" days."));
        }
    }

    @Override
    public void renderTimer(Timer timer) {
            long time = timer.snap();
            System.out.println(ansi()
                                .a("Completed in ").fg(YELLOW)
                                .a(time).reset()
                                .a(" milliseconds (").fg(YELLOW)
                                .a(String.format("%.3f", time / 1000d)).reset()
                                .a(" seconds)"));        
    }

    @Override
    public void renderErrorMessage(String message) {
        System.err.println(ansi().fg(RED).a(message).reset());
    }
}
