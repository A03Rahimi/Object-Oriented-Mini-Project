import java.io.Serializable;

public class Character implements Serializable {
    private final Player player;
    private final String name;
    private final String job;
    private final String profession;
    private final String interest;

    private int goldAmount;
    private int score;
    private final  int strengthAmount;
    private final int agilityAmount;
    private final int powerAmount;

    public Character(Player player,String name, String job, String profession, String interest){
        this.player = player;
        this.name = name;
        this.job = job;
        this.profession = profession;
        this.interest = interest;

        // generate random values as initial stats of each character
        this.goldAmount = RandomInt.get(10000) + 1;  // Random value from 1 to 10000
        this.strengthAmount = RandomInt.get(1000) + 1;
        this.agilityAmount = RandomInt.get(1000) + 1;
        this.powerAmount = RandomInt.get(100) + 1;
        this.score = 0;
    }

    // User-personalized details to print in GUI
    public String getDetails(){
        return (") " + this.name +", "+ this.job+", "+this.profession+", "+this.interest+"\n");
    }

    public String getName() {
        return this.name;
    }

    public Player getPlayer() {
        return this.player;
    }

    // randomly-generated details to use in GUI the game section
    public String getWarDetails(){
        return ("Your Details:\n\nCurrent Gold: " + this.goldAmount + "\nCurrent Strength:" + this.strengthAmount + "\nCurrent Agility:" + this.agilityAmount+ "\nCurrent power:" + this.powerAmount + "\n\nScore:" + this.score);
    }

    public void addGold(int amount){
        this.goldAmount += amount;
    }
    public int getScore(){
        return this.score;
    }
    public void giveScore(){
        this.score++;
    }
    public void takeScore(){
        this.score--;
    }

    // sum of stats to compare with opponent
    public int getSumOfStats(){
        return (this.powerAmount + this.strengthAmount + this.agilityAmount);
    }
    public int getGoldAmount(){
        return this.goldAmount;
    }
}