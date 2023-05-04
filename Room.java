import java.util.ArrayList;

public class Room{
    String name;
    String description;
    String defaultDescription;
    ArrayList<String> exits;
    ArrayList<Weapon> weapons;
    private ArrayList<Zombie> zombies;
    
    /**
     * Initialize a room
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.defaultDescription = description;
        this.exits = new ArrayList<>();
        this.weapons = new ArrayList<>();
        zombies = new ArrayList<>();
    }

    /* Generate getters and setters for the names and descriptions */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Adds an exit to the room
     * @param exit room name of the exit to be added to the room
     */
    public void addExit(String exit) {
        exits.add(exit);
    }

    //Method to add weapon to the room
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
        updateDescription(weapon);
    }

    //Method to remove weapon from the room
    public boolean removeWeapon(Weapon weapon) {
        return weapons.remove(weapon);
    }

    //Get weapon from the room
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    //Method for adding zombies to the room
    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    //Remove zombie when they are killed
    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
        if (zombies.isEmpty()) {
            //Update room description to remove zombie
            description = defaultDescription;
        }
    }

    //Getter for zombies
    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void updateDescription(Weapon weapon) {
        String weaponName = weapon.getWeaponName(); 
        String weaponDescription = "A " + weaponName + " is lying on the ground.";
        if (description.contains(weaponDescription)) {
            description = defaultDescription.replace(weaponDescription, "");    
        } 
    } 
    
    /**
     * List all of the rooms as a string
     * @return returns all of the names of the rooms on new lines
     */
    
     public String listExits() {
        String exitList = "";
        //Use enhanced for-loop to list the exits for the room.
        for(String exit : exits) {
            exitList += exit + "\n";
        }
        return exitList;
    } 

    /**
     * Generates a string representation of the room using the name and description and lists all of the exits.
     */
    public String toString() {
        return name + ": " + description + "\n" + "\nExits:\n" + listExits();
    }
}
