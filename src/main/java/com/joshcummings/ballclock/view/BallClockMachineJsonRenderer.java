package com.joshcummings.ballclock.view;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.joshcummings.ballclock.fsm.BallClockMachine;
import com.joshcummings.ballclock.model.Ball;

/**
 * A convenience class for rendering the ball clock machine as JSON according to the spec
 */
public class BallClockMachineJsonRenderer {
    private static final String ONE_MINUTE_KEY = "Min";
    private static final String FIVE_MINUTES_KEY = "FiveMin";
    private static final String HOUR_KEY = "Hour";
    private static final String HOPPER_KEY = "Main";
    
    private final Gson gson;
    
    public BallClockMachineJsonRenderer(Gson gson) {
        this.gson = gson;
    }
    
    public List<Integer> offsetByOne(Collection<Ball> balls) {
        return balls.stream().map(i -> i.number() + 1).collect(Collectors.toList());
    }
    
    public String toJson(BallClockMachine machine) {
        Map<String, Object> tracks = new LinkedHashMap<>();
        
        tracks.put(ONE_MINUTE_KEY, offsetByOne(machine.oneMinute().balls()));
        tracks.put(FIVE_MINUTES_KEY, offsetByOne(machine.fiveMinutes().balls()));
        tracks.put(HOUR_KEY, offsetByOne(machine.oneHour().balls()));
        tracks.put(HOPPER_KEY, offsetByOne(machine.hopper().balls()));
        
        return gson.toJson(tracks);
    }
}
