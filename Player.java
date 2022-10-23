import java.io.Serializable;
import java.util.ArrayList;

public class Player implements PlayerType, Serializable {
	private ProfileWindow window;
	private final String name;
	private final int birthYear;
	private final int userUniqueId;
	private static int userIdCounter = 1;
	private int maxCharacters;
	private final ArrayList<Character> characters;

	// prompt users to create their profiles by entering their details in console
	// and assign a unique user ID to each Player object
	public Player(){
		this.characters= new ArrayList<Character>();
		this.setMaxCharacters(1);
		this.name = ConsoleScanner.string("Please enter your name: ");
		this.birthYear = ConsoleScanner.integer("Please enter the year of your birth (e.g. 2003):");
		this.userUniqueId = userIdCounter++;
	}

	// creates a string of player details to write in the GUI
	public String getDetails(){
		return ("*****************************\nYour Details:\n\nPlayer Type: Normal\nPlayer Name: " + this.name + "\nPlayer Birth Year: " + this.birthYear + "\nPlayer User ID: " + this.userUniqueId + "\n*****************************");
	}

	public String getName(){
		return this.name;
	}

	public String PlayerType(){
		return "Normal";
	}

	public int getBirthYear(){
		return this.birthYear;
	}

	public int getUserUniqueID(){
		return this.userUniqueId;
	}

	public void setMaxCharacters(int maxNum){
		this.maxCharacters = maxNum;
	}

	public int getMaxCharacters(){
		return this.maxCharacters;
	}

	// create and add a character to characters ArrayList if the players haven't reached their limit yet
	public String addCharacter(Player player,String name, String job, String profession, String interest){
		if(characters.size() < this.getMaxCharacters()){
			characters.add(new Character(player, name, job, profession, interest));
			return "New Character Added Successfully";
		}
		else{
			return("You have reached maximum limit of characters for " + this.PlayerType() + " Players. You cannot create any new Characters.");
		}
	}

	// remove specific character from characters ArrayList and throw the user defined exception
	// NoSuchCharacterBelongingToPlayerException if the character does not exist
	// (which is caught with a try and catch during the method call)
	public void removeCharacter(Character character) throws NoSuchCharacterBelongingToPlayerException{
		if (this.characters.contains(character)){
			for (int i = 0 ; i<this.characters.size() ; i++){
				if (this.characters.get(i).equals(character)){
					this.characters.remove(i);
					i--;
				}
			}
		}
		else{
			throw new NoSuchCharacterBelongingToPlayerException("Such character does not exist in that player's characters list");
		}
	}


	public ArrayList<Character> getCharacters(){
		return this.characters;
	}

	public void setWindow(ProfileWindow window ){
		this.window = window;
	}

	public ProfileWindow getWindow(){
		return this.window;
	}


}	