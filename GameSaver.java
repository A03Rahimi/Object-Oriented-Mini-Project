import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// a class to save and load games with exception handling
public class GameSaver{
    private static Player player;
    public static void save(Player player){
        // safe object to a file in directory of program\saves\playerUserId.dat
        try (FileOutputStream fos = new FileOutputStream("saves/"+player.getUserUniqueID() + ".dat"); ObjectOutputStream oos = new ObjectOutputStream(fos)){

            // write object to the file
            oos.writeObject(player);
            oos.close();
            player.getWindow().print("Your game has been saved successfully.\nPlease note you need to know your 'Player User ID' to load your game.\nTo view this ID click on 'My Details' before you exit.");
        }
        // print any possible exception in console
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void load(){
        Prompt userIdPrompt = new Prompt();
        userIdPrompt.setTitle("Load");
        Label label = new Label("Enter your 'Player User ID' to load your game");
        userIdPrompt.add(label);
        TextField userResponse = new TextField();
        userIdPrompt.add(userResponse);
        userIdPrompt.addSubmitListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // load objects from files from the directory of program\saves\playerUserId.dat
                try (FileInputStream fis = new FileInputStream("saves/"+userResponse.getText()+".dat");
                     ObjectInputStream ois = new ObjectInputStream(fis)) {

                    // read object from the file
                    player = (Player) ois.readObject();
                    ois.close();
                    new GameApp(player);
                    player.getWindow().refreshCharacterButtons();
                }
                catch (IOException | ClassNotFoundException ex) {
                    // print any possible error in console
                    ex.printStackTrace();

                    // a pop-up window to inform user they have entered incorrect playerUserId
                    Frame frame = new Frame();
                    TextArea textField = new TextArea("\nThe 'Player User ID' you entered does not exist. Please try again entering a valid 'Player User ID'.",3,85,TextArea.SCROLLBARS_NONE);
                    textField.setEditable(false);
                    textField.setVisible(true);
                    frame.setTitle("Error!");
                    frame.add(textField);
                    WindowCloser xButton = new WindowCloser();
                    frame.addWindowListener(xButton);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                }
            }
        });
        userIdPrompt.activate();
    }






}