import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// this is the main window (frame) containing user details and characters controls
public class ProfileWindow extends WindowBase{
    private Panel charactersButtonPanel;
    private final Player player;

    public ProfileWindow(Player player){
        super(player.getName() + "'s Profile");
        this.player = player;
        player.setWindow(this);
        this.addFeatures();
        this.setVisible(true);
    }

    public Player getPlayer(){
        return this.player;
    }

    //
    private void addFeatures(){

        // Text field on the GUI
        setTextField(new TextArea(player.getDetails(),10,45,0));
        this.getTextField().setEditable(false);
        this.add(this.getTextField());

        // Your Details button on the GUI
        Button showPlayerDetailsButton=new Button("Your Details");
        showPlayerDetailsButton.setActionCommand("Your Details");
        showPlayerDetailsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("'" + event.getActionCommand() + "' button pressed");
                print(player.getDetails());
            }
        });
        this.add(showPlayerDetailsButton);

        // Your Characters button on the GUI
        Button showCharactersButton=new Button("Your Characters");
        showCharactersButton.setActionCommand("Your Characters");
        showCharactersButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String text = "Characters will appear below after you create them.\n\nName, Job, Profession, Interest\n\n";
                System.out.println("'" + event.getActionCommand() + "' button pressed");
                for(int i = 0; i < player.getCharacters().size() ; i++){
                    text = text + (i+1) + player.getCharacters().get(i).getDetails();
                }
                print(text);
            }
        });
        this.add(showCharactersButton);

        // Characters button (1 to up to 5) container panel
        charactersButtonPanel = new Panel();
        charactersButtonPanel.setLayout(new GridLayout(0,1));
        this.add(charactersButtonPanel);

        // New Character button on the GUI
        Button newCharacterButton=new Button("New Character");
        newCharacterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("'" + event.getActionCommand() + "' button pressed");

                // Prompt users to create and personalize a new character by entering their preferences
                Prompt personalizationPrompt = new Prompt();
                personalizationPrompt.setTitle("New Character");

                TextField name = new TextField("Enter Character Name");
                personalizationPrompt.add(name);

                TextField job = new TextField("Enter Character Job");
                personalizationPrompt.add(job);

                TextField profession = new TextField("Enter Character Profession");
                personalizationPrompt.add(profession);

                TextField interest = new TextField("Enter Character Interest(s)");
                personalizationPrompt.add(interest);

                personalizationPrompt.addSubmitListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        System.out.println("'submit' button pressed");
                        String result = getPlayer().addCharacter(player,name.getText(),job.getText(),profession.getText(), interest.getText());
                        print(result); // Print new character details after creation. (addCharacter method call returns the string)

                        // if true, this means the player haven't reached the max limit.
                        // so the character can be created.
                        if (result.equals("New Character Added Successfully")){
                            // create a button corresponding to the new character created
                            Button btn = new Button("Character: " + player.getCharacters().size());
                            btn.setActionCommand(Integer.toString(player.getCharacters().size()));
                            btn.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent event){
                                    System.out.println("'Character: " + event.getActionCommand() + "' button pressed");
                                    new CharacterWorldWindow(player.getCharacters().get(Integer.parseInt(event.getActionCommand())-1),btn,charactersButtonPanel);
                                }
                            });
                            charactersButtonPanel.add(btn);
                            charactersButtonPanel.setVisible(true);
                            getFrame().setVisible(true); // refresh window
                        }
                    }
                });
                personalizationPrompt.activate();
            }
        });
        this.add(newCharacterButton);

        // Save Game button on GUI
        Button saveGameButton=new Button("Save Game");
        saveGameButton.setActionCommand("Save Game");
        saveGameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("'" + event.getActionCommand() + "' button pressed");
                GameSaver.save(getPlayer());
            }
        });
        this.add(saveGameButton);

        // Load Game button on GUI
        Button loadGameButton=new Button("Load Game");
        loadGameButton.setActionCommand("Load Game");
        loadGameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.out.println("'" + event.getActionCommand() + "' button pressed");
                GameSaver.load();
            }
        });
        this.add(loadGameButton);
    }

    // refresh characters buttons after removing one of them for tidiness of GUI
    public void refreshCharacterButtons(){
        for (int i = 0 ; i < getPlayer().getCharacters().size() ; i++){
            Button btn = new Button("Character: " + (i+1));
            btn.setActionCommand(Integer.toString(i+1));
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    System.out.println("'Character: " + event.getActionCommand() + "' button pressed");
                    new CharacterWorldWindow(getPlayer().getCharacters().get(Integer.parseInt(event.getActionCommand()) - 1), btn, charactersButtonPanel);
                }
            });
            charactersButtonPanel.add(btn);
        }
        charactersButtonPanel.setVisible(true);
        getPlayer().getWindow().setVisible(true); // refresh the window
    }
}