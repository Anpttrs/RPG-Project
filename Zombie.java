public class Zombie {
    //Declare variables
    private int health;

    public Zombie(int health){
        this.health = health;
    }

    //Setters and Getters for health.
    public int getHealth(){
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isDead() {
        return health <= 0;
    }
}