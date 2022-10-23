// This is a helper method to create the game window using different methods
// (method overloading and polymorphism used in this class)
public final class GameApp {
	private final Player player;
	private ProfileWindow profileWindow;

	// when prompting users to choose their player type this method is used
	public GameApp() {
		this.player = choosePlayerType();
		profileWindow = new ProfileWindow(this.player);
	}
	// when loading a game this method is used (method overloading)
	public GameApp(Player player) {
		this.player = player;
		profileWindow = new ProfileWindow(player);
	}

	// a new Player object is created but based on user decision, it will point to different objects.
	// and because of this, several methods in VipPlayer and PremiumPlayer can override methods in their superclass Player
	// (polymorphism applied here)
	public Player choosePlayerType() {
		System.out.println("\nWelcome to this game I hope you enjoy it :)\nYou are now a normal player. Normal players can only have 1 character while vip players can have 2 characters and premium players can have up to 5 characters.");
		Player player = null;
		boolean looping = true;
		while(looping) {
			int choice = ConsoleScanner.integer("\n1) continue as a normal player\n2) become a VIP player\n3) become a premium player\nPlease choose the player type you wish by entering its number: ");
			switch(choice) {
				case 1:
					player = new Player();
					looping = false;
				break;

				case 2:
					player = new VipPlayer();
					looping = false;
				break;

				case 3:
					player = new PremiumPlayer();
					looping = false;
				break;

				default:
					System.out.println("The value entered is invalid, please try again entering either 1, 2, or 3.");
			}
		}
		return player;
	}
}
	