/* 
Name: Andrew Patterson
Date: 04/05/2023
CPSC 1060: Final Project
Lab Section (002)
Link to github: https://github.com/Anpttrs/RPG-Project
*/

import java.util.HashMap;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        FileTextReader reader = new FileTextReader();
        HashMap<String, Room> rooms = new FileTextReader().readFile("RoomInfo.txt");
        Map createMap = new Map(rooms);
        
        //Player damage towards zombies is initally set to 20(No weapons, just your hands)
        Player player = new Player(20);
        GameLogic gameLogic = new GameLogic(createMap, player);
        gameLogic.placeWeaponsInRoom();
        gameLogic.startGame();

        Room currentRoom = createMap.getRoom("Lobby");
     
    }
}
