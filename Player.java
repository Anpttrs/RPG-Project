public class Player{
    private int damage; 
    private Weapon equippedWeapon;
    private int defaultDamage;

    public Player(int defaultDamage) {
        this.defaultDamage = defaultDamage;
    }

    public int getDamage() {
        if (equippedWeapon != null) {
            return equippedWeapon.getWeaponDamage();
        } else {
            return defaultDamage;
        }
    }

    public void equippedWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
}