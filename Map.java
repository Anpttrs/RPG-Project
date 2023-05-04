import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;


public class Map {
    HashMap<String, Room> rooms;

    public Map(HashMap<String, Room> rooms) {
       this.rooms = rooms;
    }
    
    /**
     * Returns the Room assocaited with the string name given
     * @param roomName the name of the room to be returned, room name must be valid
     * @return the Room object assocaited with the name, the name will be in lowercase
     */
    public Room getRoom(String roomName){
        String name = roomName.toLowerCase();
        return rooms.get(name);
    }

    public ArrayList<Room> getAllRooms(){
        return new ArrayList<>(rooms.values());
    }

    // Use an ArrayList to get all the rooms and then use the Random function to place zombies in random rooms. 
    public void addZombiesToRoom() {
        //Use hashMap.values() to return the collection of values in the map and store those values/rooms in the ArrayList. 
        ArrayList<Room> roomList = new ArrayList<>(rooms.values());
        Random random = new Random();

        for (int i = 0; i < 4; i++) { 
            //Generate and return number based on the size of the room. In this case the range will be from 0-6.
            int randomIndex = random.nextInt(roomList.size());
            Room randomRoom = roomList.get(randomIndex);
            randomRoom.addZombie(new Zombie(100));
            roomList.remove(randomIndex);
        }
    }
}