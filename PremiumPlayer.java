// Premium players are like players, but they have an extra field called premiumID
// and their max number of characters is 5
// *all players have unique userIDs over all players and premium players have unique premiumIDs among premium players*
public class PremiumPlayer extends Player {
	private static int premiumIdCounter = 1;
	private final int premiumId;
	
	public PremiumPlayer() {
		super();
		premiumId = premiumIdCounter++;
		this.setMaxCharacters(5);
	}

	// get all player details to print in GUI
	// overrides superclass method based on the decision made in GameApp.java (which used  polymorphism)
	public String getDetails() {
		return ("★★★★★★★★★★★★★★★★★★★★★★\nYour Details:\n\nPlayer Type: "+ this.PlayerType()+" (up to "+ this.getMaxCharacters() +" characters)\nPlayer Name: " + this.getName() + "\nPlayer Birth Year: " + this.getBirthYear() + "\nPlayer User ID: " + this.getUserUniqueID() + "\n\nPremium ID: " + this.premiumId + "\n★★★★★★★★★★★★★★★★★★★★★★");
	}

	// definition of interface method
	public String PlayerType() {
		return "Premium";
	}
}	