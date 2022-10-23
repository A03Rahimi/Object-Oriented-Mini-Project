
// VIP players are like players, but they have an extra field called vipRegistrationCode
// and their max number of characters is 2
// *all players have unique userIDs over all players and VIP players have unique vipRegistrationCodes among VIP players*
public class VipPlayer extends Player{
	private static int vipRegCodeCounter = 1;
	private final int vipRegistrationCode;
	public VipPlayer(){
		super();
		this.vipRegistrationCode = vipRegCodeCounter++;
		setMaxCharacters(2);
	}

	// get all player details to write in the GUI
	public String getDetails() {
		return ("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆\nYour Details:\n\nPlayer Type: "+ this.PlayerType()+" (up to "+ this.getMaxCharacters() +" characters)\nPlayer Name: " + this.getName() + "\nPlayer Birth Year: " + this.getBirthYear() + "\nPlayer User ID: " + this.getUserUniqueID() + "\n\nVIP Registration Code: " + this.vipRegistrationCode + "\n☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
	}

	// definition of interface method
	public String PlayerType(){
		return "VIP";
	}
}	