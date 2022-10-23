import java.util.Random;
// a class to generate random enemy objects for each character
public final class EnemyGenerator{
    final int goldAmount;
    final int strengthAmount;
    final int agilityAmount;
    final int powerAmount;

    public EnemyGenerator(){
        this.goldAmount = generateGoldAmount();
        this.strengthAmount = generateStrengthAmount();
        this.agilityAmount = generateAgilityAmount();
        this.powerAmount = generatePowerAmount();
    }

    public int generateGoldAmount(){
        return RandomInt.get(10000) + 1;
    }

    public int generateStrengthAmount(){
        return RandomInt.get(1000) + 1;
    }

    public int generateAgilityAmount(){
        return RandomInt.get(1000) + 1;
    }

    public int generatePowerAmount(){
        return RandomInt.get(100) + 1;
    }

    // get all enemy stats corresponding to character stats needed to be shown in GUI
    public String getWarDetails(){
        return ("Enemy's details:\n\nCurrent Gold: " + this.goldAmount + "\nCurrent Strength:" + this.strengthAmount + "\nCurrent Agility:" + this.agilityAmount + "\nCurrent power:" + this.powerAmount);
    }

    // get sum of stats to decide who wins
    public int getSumOfStats(){
        return (this.powerAmount + this.strengthAmount + this.agilityAmount);
    }

    // get a reduced amount of gold (0-15%) to determine the gold bonus amount for the winner
    public int getGoldReduced(){
        float value = (float) (this.goldAmount);
        return (int)((value*((float)(RandomInt.get(15))))/100.0f);
    }
}