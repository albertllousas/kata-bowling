# Kata Bowling

## The problem

### American Ten-Pin Bowling

Create a program, which, given a valid sequence of rolls for one line of American Ten-Pin Bowling, produces the total score for the game. 

Here are some things that the program will not do:

- We will not check for valid rolls.
- We will not check for correct number of rolls and frames.
- We will not provide scores for intermediate frames.

Depending on the application, this might or might not be a valid way to define a complete story, but we do it here for purposes of keeping the kata light. 
I think you’ll see that improvements like those above would go in readily if they were needed for real.

We can briefly summarize the scoring for this form of bowling:

mezclar esto

Each game, or “line” of bowling, includes ten turns, or “frames” for the bowler.

- In each frame, the bowler gets up to two tries to knock down all the pins.
- If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.
- If in two tries he knocks them all down, this is called a “spare” and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
- If on his first try in the frame he knocks down all the pins, this is called a “strike”. His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
- If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.
- The game score is the total of all frame scores.

Scoring Rules

- In each frame, the bowler gets up to two tries to knock down all the pins.

Strike
If you knock down all 10 pins in the first shot of a frame, you get a strike.
How to score: A strike earns 10 points plus the sum of your next two shots.

Spare

If you knock down all 10 pins using both shots of a frame, you get a spare.
How to score: A spare earns 10 points plus the sum of your next one shot.

Open Frame

If you do not knock down all 10 pins using both shots of your frame (9 or fewer pins knocked down), you have an open frame.
How to score: An open frame only earns the number of pins knocked down.

The 10th Frame

The 10th frame is a bit different:
If you roll a strike in the first shot of the 10th frame, you get 2 more shots.
If you roll a spare in the first two shots of the 10th frame, you get 1 more shot.
If you leave the 10th frame open after two shots, the game is over and you do not get an additional shot.
How to Score: The score for the 10th frame is the total number of pins knocked down in the 10th frame.

More info on the rules at:
 
 - [How to Score for Bowling](https://www.topendsports.com/sport/tenpin/scoring.htm)
 - [Keeping_Score](https://www.bowl.com/Welcome/Welcome_Home/Keeping_Score/)
 - [Bowling score online calculator](http://www.bowlinggenius.com/)


### Problem input/output
(When scoring “X” indicates a strike, “/” indicates a spare, “-” indicates a miss)

- X X X X X X X X X XXX (12 rolls: 12 strikes) = 10 frames * 30 points = 300
- 9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
- 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150

## The Solution

The solution could be easier, just parsing the string, but part of the problem was explore kotlin and its features

### Environment
```bash
java -version 
java version "1.8.0_131"
Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)
```
```bash
gradle -version   

------------------------------------------------------------
Gradle 4.0.1
------------------------------------------------------------

Build time:   2017-07-07 14:02:41 UTC
Revision:     38e5dc0f772daecca1d2681885d3d85414eb6826

Groovy:       2.4.11
Ant:          Apache Ant(TM) version 1.9.6 compiled on June 29 2015
JVM:          1.8.0_131 (Oracle Corporation 25.131-b11)
OS:           Mac OS X 10.13.3 x86_64
```
### Running the tests
```bash
gradle test 
```

### Approach

Immutable and functional.
Just immutable domain and functions

https://stackoverflow.com/questions/14494747/add-images-to-readme-md-on-github
http://www.fryes4fun.com/Bowling/scoring.htm
https://github.com/hontas/bowling-game-kata/blob/master/README.md

Model
Image de https://github.com/hontas/bowling-game-kata/blob/master/README.md
Explanation:-> 
10 frames is a game
Frame can be:  poner la class
Solution design 
- parsing
- model
- score algorithm 
explain algorithm 

