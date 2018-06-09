Instructions from: https://nil.cs.uno.edu/courses/csci4525/projects/chess/

To see a sample tournament between some of these bots, open a terminal window and execute the following command:

java -jar chess.jar 2 bots/random.jar bots/greedy.jar bots/novice.jar

Include human.jar in the list of bots to add a human player. For example, to play 1 game between two human players:

java -jar chess.jar 1 bots/human.jar bots/human.jar
To play (as white) against a bot:

java -jar chess.jar 1 human.jar bots/novice.jar

f the bots are taking a long time to decide on moves, you can try increasing the amount of memory that the Java virtual machine uses. The -Xms command line argument specifies the minimum amount of memory to use, and the -Xmx command line argument specifies the maximum amount to use. You can also allocate more space to young generation objects using -XX:NewSize command line argument to save on garbage collection time. To run the above example with exactly 4 gigabytes of memory, 3 of which are used for new objects:

java -Xms4g -Xmx4g -XX:NewSize=3g -jar chess.jar 2 bots/random.jar bots/greedy.jar bots/novice.jar


To run an entire tournament and play as my bot:

java -Xms4g -Xmx4g -XX:NewSize=3g -jar chess.jar 2 srobert6.jar bots/random.jar bots/greedy.jar bots/novice.jar bots/beginner.jar bots/intermediate.jar

