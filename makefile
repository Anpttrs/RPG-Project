default: Map.java Zombie.java Room.java Driver.java Weapon.java GameLogic.java Player.java
	javac Map.java Zombie.java Room.java Driver.java Weapon.java GameLogic.java Player.java

run: Map.class Zombie.class Room.class Driver.class Weapon.class GameLogic.class Player.class
	java Driver

clean:
	rm -f *.class