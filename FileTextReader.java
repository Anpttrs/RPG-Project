import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class FileTextReader {
    public HashMap<String, Room> readFile(String filePath){
        HashMap<String, Room> rooms = new HashMap<>();
        Map map = new Map(rooms);
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while((line = reader.readLine()) != null) {
                String[] roomInfo = line.split(";");
                Room room = new Room(roomInfo[0], roomInfo[1]);
                String[] exits = roomInfo[2].split(",");
                for (String exit : exits) {
                    room.addExit(exit);
                }
                rooms.put(room.getName().toLowerCase(), room);
            }
        } catch(IOException e) {
            System.out.println("Error cant find file");
        }
        return rooms;
    }
}