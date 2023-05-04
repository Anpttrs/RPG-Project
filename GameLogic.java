import java.util.Scanner;

public class GameLogic {
    private Map map;
    private Room currentRoom;
    private Scanner scanner;
    private Player player;

    public GameLogic(Map map, Player player){
        this.map = map;
        this.currentRoom = map.getRoom("Lobby");
        this.scanner = new Scanner(System.in);
        this.player = player;
    }

    //Method for placing weapons in the desired room.
    public void placeWeaponsInRoom() {
        Room lobby = map.getRoom("Lobby");
        Room hallwayA = map.getRoom("Hallway A");
        Room hallwayB = map.getRoom("Hallway B");
        Room SecurityRoom = map.getRoom("Security Room");
        Room bioHazardStorage = map.getRoom("Bio Hazard Storage");
        Room researchLab = map.getRoom("Research Lab");
        Room medicalBay = map.getRoom("Medical Bay");
        //Create weapons
        Weapon oleReliable = new Weapon("Wooden Bat", 5);
        Weapon pistol = new Weapon("Pistol", 35);
        Weapon shotgun = new Weapon("Shotgun", 70);
        Weapon assultRifle = new Weapon("Assult Rifle", 50);
        Weapon knife = new Weapon("Knife", 25);
        Weapon crowbar = new Weapon("Crowbar", 20);
        Weapon boom = new Weapon("Boom Boom", 100);

        //add weapons to rooms
        lobby.addWeapon(pistol);
        lobby.addWeapon(boom);
        researchLab.addWeapon(assultRifle);
        medicalBay.addWeapon(knife);
        SecurityRoom.addWeapon(crowbar);
        bioHazardStorage.addWeapon(shotgun);

        //Place zombies in room
        map.addZombiesToRoom();
    }

    public Player getPlayer() {
        return this.player;
    }

    //Helper method to pick-up weapons based on the current room
    public static void weaponPickup(String weaponName, Room currentRoom, Player player) {
        Weapon foundWeapon = null;
        for(Weapon weapon : currentRoom.getWeapons()) {
            if (weapon.getWeaponName().equalsIgnoreCase(weaponName)) {
                foundWeapon = weapon;
                break;
            }
        }

        if (foundWeapon != null) {
            //Remove weapon from the room when the user picks up the weapon. 
            currentRoom.removeWeapon(foundWeapon);
            currentRoom.updateDescription(foundWeapon);
            player.equippedWeapon(foundWeapon);
            System.out.println("You have picked up the " + weaponName + ".");
            System.out.println("This weapon can inflict " + foundWeapon.getWeaponDamage() + " damage.");
            player.equippedWeapon(foundWeapon);                     
            System.out.println();
        } else {
            System.out.println("There is no " + weaponName + " in this room.");
            System.out.println();
        }
    }


    public void attackZombie() {
        if(currentRoom.getZombies().size() != 1) {
            System.out.println("There is no zombies in this room. Thats a relief");
            return;
        } 
        Zombie zombie = currentRoom.getZombies().get(0);
        System.out.println("Zombie (health: " + zombie.getHealth() + ")");

        int damage = player.getDamage();
        zombie.takeDamage(damage);
        System.out.println("You attacked the zombie and did " + damage + " damage.");
        System.out.println();
        if (zombie.isDead()) {
            currentRoom.removeZombie(zombie);
            System.out.println("Awesome job! You killed the zombie!");
            if (currentRoom.getZombies().isEmpty()) {
                System.out.println("The room is clear of zombies.");
                System.out.println();
            }
        } 
    }

     private boolean allZombiesKilled() {
        for (Room room : map.getAllRooms()) {
            if (!room.getZombies().isEmpty()) {
                return false;
            }
        }
        return true;
     }
    

    //Main logic of the game
    public void startGame() {
        boolean exit = false;
        while(!exit) {
            System.out.println(currentRoom);

            //When the player enters a room - if there is a weapon in the room prompt them with the option to pick up the weapon or choose an exit. 
            //If there is no weapon in the room, then only list the exits.
            if(!currentRoom.getWeapons().isEmpty()) {
                System.out.println("This room contains the following weapons:");
                for (Weapon weapon : currentRoom.getWeapons()) {
                    currentRoom.updateDescription(weapon);
                    System.out.println("- " + weapon.getWeaponName());
                }
                System.out.println();
                System.out.println("\nPlease choose an exit or type 'take <weapon name>' to pick up the weapon.");
            } else {
                System.out.println("\nPlease choose an exit.");
            }

            String userInput = scanner.nextLine();
            System.out.println();
            
            if (userInput.equals("exit")) {
                //Provide user with a message, depening on if they clear the facility or quit the game before clearing. 
                if (allZombiesKilled()) {
                    System.out.println("Great job clearing the facility.");
                    break;
                } else {
                    System.out.println("You have chosen to exit the facility. Better luck next time.");
                    break;
                }
            }

            //Oracle - .startsWith() Tests if the substring of this string beginning at the specified index starts with the specified prefix.
            //Might change to option of YES or NO to avoid confusion for the player. 
            if (userInput.toLowerCase().startsWith("take ")) {
                String weaponName = userInput.substring(5);
                weaponPickup(weaponName, currentRoom, player);
            } else {
                //Use while loop to continue prompting the user to enter a valid exit based on the current room.
                while(!currentRoom.listExits().contains(userInput)) {
                    System.out.println("Invalid exit.");
                    System.out.println("Please choose an exit.");
                    userInput = scanner.nextLine();
                } //If userInput matches one of the listed exits for the currentRoom, then getRoom based on the userInput.
                if(currentRoom.listExits().contains(userInput)) {
                    currentRoom = map.getRoom(userInput);
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }

            if (!currentRoom.getZombies().isEmpty()) {
                System.out.println("There is a zombie in this room.");
                System.out.println("Do you want to attack the zombie? (Y/N)");
                userInput = scanner.nextLine();
                System.out.println();
            }

            if (userInput.equalsIgnoreCase("Y")) {
                while(!currentRoom.getZombies().isEmpty()) {
                    attackZombie();
                    if (allZombiesKilled()) {
                        System.out.println("Facility cleared. Proceeding to exit.");
                        System.out.println();
                        exit = true;
                        break;
                    }
                } 
            }
        } 
    }
}