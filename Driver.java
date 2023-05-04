import java.util.HashMap;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        FileTextReader reader = new FileTextReader();
        HashMap<String, Room> rooms = new FileTextReader().readFile("RoomInfo.txt");
        Map createMap = new Map(rooms);
        
        Player player = new Player(20);
        GameLogic gameLogic = new GameLogic(createMap, player);
        gameLogic.placeWeaponsInRoom();
        gameLogic.startGame();

        Room currentRoom = createMap.getRoom("Lobby");
     
    }
}