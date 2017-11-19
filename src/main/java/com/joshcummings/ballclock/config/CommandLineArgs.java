package com.joshcummings.ballclock.config;

import java.util.List;

import com.beust.jcommander.Parameter;

/**
 * The various configurations possible for the app.
 * 
 * Note the use of the args array, which is to comply with the documentation which states that
 * these two parameters ought to be space-delimited and without any kind of parameter key.
 */
public class CommandLineArgs {
    @Parameter(description = "[number of balls:Int[27,127]] [number of minutes:Int]")
    List<Integer> args;

    @Parameter(description = "pretty print", names = "--pretty")
    boolean prettyPrint;
    
    @Parameter(description = "verbose", names = "--verbose")
    boolean verbose;
    
    @Parameter(description = "ball count, between 27 and 127", names = "--balls")
    Integer balls;
    
    @Parameter(description = "time in minutes", names = "--runtime")
    Integer time;
    
    public Integer balls() {
        return balls == null ? 
                args.size() < 1 ? null : args.get(0) :
                balls;
    }

    public Integer time() {
        return time == null ?
                args.size() < 2 ? null : args.get(1) :
                time;
    }
    
    public Boolean pretty() {
        return prettyPrint;
    }
    
    public Boolean verbose() {
        return verbose;
    }
}
