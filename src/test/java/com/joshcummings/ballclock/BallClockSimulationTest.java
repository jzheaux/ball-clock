package com.joshcummings.ballclock;

import com.google.gson.Gson;
import com.joshcummings.ballclock.fsm.BallClockMachine;
import com.joshcummings.ballclock.view.BallClockMachineJsonRenderer;

import org.junit.Assert;
import org.junit.Test;

/**
 * An integration test, which exericses the entire applicationo on the use cases outlined
 * in the write-up.
 */
public class BallClockSimulationTest
{
    BallClockMachineJsonRenderer renderer = new BallClockMachineJsonRenderer(new Gson());
    
    @Test
    public void testGiven30_Return15()
    {
        BallClockSimulation bc = new BallClockSimulation(30);
        BallClockMachine machine = bc.runSimulation();
        Assert.assertEquals(15, machine.ts() / (60*24));
    }
    
    @Test
    public void testGiven45_Return378()
    {
        BallClockSimulation bc = new BallClockSimulation(45);
        BallClockMachine machine = bc.runSimulation();
        Assert.assertEquals(378, machine.ts() / (60*24));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGivenTooFew_ThrowException() {
        new BallClockSimulation(10);
        Assert.fail("Should have thrown an exception");
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testGivenTooMany_ThrowException() {
        new BallClockSimulation(1234);
        Assert.fail("Should have thrown an exception");
    }
    
    @Test
    public void testGiven30And325_ReturnValid() {
        BallClockSimulation bc = new BallClockSimulation(30, 325);
        BallClockMachine machine = bc.runSimulation();
        
        String expected = "{\"Min\":[]," +
                            "\"FiveMin\":[22,13,25,3,7]," + 
                            "\"Hour\":[6,12,17,4,15]," +
                            "\"Main\":[11,5,26,18,2,30,19,8,24,10,29,20,16,21,28,1,23,14,27,9]}";
        Assert.assertEquals(expected, renderer.toJson(machine));
    }
}
