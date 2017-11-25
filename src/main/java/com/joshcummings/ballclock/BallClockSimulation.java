package com.joshcummings.ballclock;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.joshcummings.ballclock.config.CommandLineArgs;
import com.joshcummings.ballclock.fsm.BallClockMachine;
import com.joshcummings.ballclock.fsm.CycleTerminus;
import com.joshcummings.ballclock.fsm.TimeLimitTerminus;
import com.joshcummings.ballclock.metrics.Timer;
import com.joshcummings.ballclock.view.PlainCommandLineView;
import com.joshcummings.ballclock.view.PrettyCommandLineView;
import com.joshcummings.ballclock.view.View;

/**
 * The engine for running ball clock simulations.
 *
 */
public class BallClockSimulation {
    /**
     * A state machine for tracking the state of the Ball Clock at any give time t
     */
    private final BallClockMachine machine;
    
    /**
     * The rendering strategy for outputting results to the end user. Defaults to unadorned console output.
     */
    private View view = new PlainCommandLineView();
    
    /**
     * An indicator regarding level of output to the end user, defaults to false
     */
    private boolean verbose;
    
    /**
     * Mode 1 (Cycle Days) constructor. Using this constructor will execute a simulation that runs
     * the ball clock until the balls in the hopper return to their initial state.
     * 
     * @param balls - The number of balls total in the machine
     */
    public BallClockSimulation(int balls) {
        this.machine = new BallClockMachine(balls, new CycleTerminus(balls));
    }

    /**
     * Mode 2 (Clock State) constructor. Using this constructor will execute a simulation that runs
     * the ball clock for the number of minutes specified.
     * 
     * @param balls - The number of balls total in the machine
     * @param time - The time in minutes to run the simulation
     */
    public BallClockSimulation(int balls, int minutes) {
        this.machine = new BallClockMachine(balls, new TimeLimitTerminus(minutes));
    }

    public void setView(View view) {
        this.view = view;
    }
    
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    public BallClockMachine runSimulation() {
        while (machine.transition()) {
            if ( view != null && verbose ) {
                view.renderMachine(machine);
            }
        }
        return machine;
    }

    public static void main(String[] args) {
        CommandLineArgs parsed = new CommandLineArgs();
        JCommander commander = new JCommander(parsed);
        commander.setProgramName("ball-clock");
        try {
            commander.parse(args);
            
            if ( args.length == 0 ) {
                commander.usage();
                return;
            }
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            e.getJCommander().usage();
            return;
        }

        View view = parsed.pretty() ? 
            new PrettyCommandLineView() :
            new PlainCommandLineView();

        try {
            BallClockSimulation simulation =
                parsed.time() == null ? 
                    new BallClockSimulation(parsed.balls()) :
                    new BallClockSimulation(parsed.balls(), parsed.time());
            
            simulation.setView(view);
            simulation.setVerbose(parsed.verbose());


            Timer timer = new Timer();            
            BallClockMachine machine = simulation.runSimulation();
            timer.snap();
            
            view.renderMachine(machine);
            view.renderTimer(timer);
        } catch (Exception e) {
            view.renderErrorMessage("Invalid Input: " + e.getMessage());
        }
    }
}
