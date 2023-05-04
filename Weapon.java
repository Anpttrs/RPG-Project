public class Weapon{
    //Declare variables
    private String weaponName;
    private int weaponDamage;

    //Weapons are created in the gameLogic class. 
    public Weapon(String weaponName, int weaponDamage){
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
    }

    public String getWeaponName(){
        return this.weaponName;
    }

    public int getWeaponDamage() {
        return this.weaponDamage;
    }
}