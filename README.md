Ball-Clock Simulator
====================

To use the ball clock simulator, simply run the ball-clock command line so:

Getting Set Up
--------------

Make sure you have permissions to run ball-clock, the shell script:

chmod +x ball-clock

Mode 1 (Cycle Days)
-------------------

./ball-clock 30

will produce

30 balls cycle after 15 days.
Completed in 112 milliseconds (0.112 seconds)

Mode 2 (Clock State)
--------------------

./ball-clock 30 325

will produce

{"Min":[],"FiveMin":[22,13,25,3,7],"Hour":[6,12,17,4,15],"Main":[11,5,26,18,2,30,19,8,24,10,29,20,16,21,28,1,23,14,27,9]}
Completed in 74 milliseconds (0.074 seconds)

Additional Parameters
---------------------

--pretty - Pretty Print

  Running either mode using the --pretty flag _before_ the numeric arguments will result in the output being moderately more user-friendly.

  ./ball-clock --pretty 30

  will result in a color-coded output

--verbose - Verbose Output

  Running with this flag _before_ the numeric arguments will result in the machine's state being output after each state change

  ./ball-clock --verbose --pretty 30

--balls, --runtime - Balls and Runtime

  These flags take precedence over the command-line overflow:

  ./ball-clock --balls 30 --runtime 325 --pretty


