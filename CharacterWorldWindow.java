import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// the window for each character to go war and spectate enemies before war
// simple algorithms used for deciding who wins and how much losses and gains there are
public class CharacterWorldWindow extends WindowBase{
    private final Character character;
    private EnemyGenerator enemyGenerator;
    private final Button buttonInProfileWindow;
    private final Panel containerPanel;
    private TextArea textField1;
    private TextArea textField2;
    private final String guidanceInfo;
    public CharacterWorldWindow(Character character,Button button,Panel panel){
        super(character.getName() + "'s World");
        this.buttonInProfileWindow = button;
        this.containerPanel = panel;
        this.character = character;
        this.setLayout(new FlowLayout(FlowLayout.LEFT,5, 10));
        this.setSize(950, 430);
        this.guidanceInfo = "Guidance:\n\n 'by pressing the 'ATTACK!' button your character attacks the enemy.\n" +
                            "A winner will remain based on yours and your opponent's stats.\n" +
                            "So be careful who you choose to fight with.\n"+
                            "If you think you cannot beat this enemy, look for the next\n opponent by pressing 'Find Next Opponent' button\n" +
                            "\nNote: If you press 'Delete Character' button,\nYour character will be removed. This action CANNOT be undone.";
        this.addFeatures();
    }

    public void print1(String text) {
        this.textField1.setText(text);
    }

    public void setTextField1(TextArea textField){
        this.textField1 = textField;
    }

    public TextArea getTextField1(){
        return this.textField1;
    }

    public void print2(String text) {
        this.textField2.setText(text);
    }
    public void setTextField2(TextArea textField){
        this.textField2 = textField;
    }
    public TextArea getTextField2(){
        return this.textField2;
    }
    public void addFeatures(){
        // Top left text field on GUI
        setTextField(new TextArea(this.character.getWarDetails(),10,0,TextArea.SCROLLBARS_NONE));
        this.getTextField().setEditable(false);
        this.print(this.character.getWarDetails() );
        this.add(this.getTextField());

        // Top right text field on GUI
        setTextField1(new TextArea("",10,0,TextArea.SCROLLBARS_NONE));
        this.getTextField1().setEditable(false);
        generateEnemy();
        this.add(this.getTextField1());

        // Bottom text field on GUI
        setTextField2(new TextArea("",10,0,TextArea.SCROLLBARS_NONE));
        this.getTextField2().setEditable(false);
        this.add(this.getTextField2());
        print2(this.guidanceInfo);

        // the button 'ATTACK!' on GUI
        Button attackOpponentButton=new Button("ATTACK!");
        attackOpponentButton.setActionCommand("ATTACK!");
        attackOpponentButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                print(attackOpponent());
            }
        });
        this.add(attackOpponentButton);

        // the button 'Find Next Opponent' on GUI
        Button findNextOpponentButton=new Button("Find Next Opponent");
        findNextOpponentButton.setActionCommand("Find Next Opponent");
        findNextOpponentButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                generateEnemy();
                print(character.getWarDetails());
            }
        });
        this.add(findNextOpponentButton);

        // the button 'Delete Character' on GUI
        Button characterRemovalButton=new Button("Delete Character");
        characterRemovalButton.setActionCommand("Delete Character");
        characterRemovalButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event){
                try{
                    character.getPlayer().removeCharacter(character);
                }
                catch (NoSuchCharacterBelongingToPlayerException e){
                    character.getPlayer().getWindow().print("That character is already deleted");
                }
                containerPanel.remove(buttonInProfileWindow);
                containerPanel.removeAll();
                for (int i = 0 ; i < character.getPlayer().getCharacters().size() ; i++){
                    Button btn = new Button("Character: " + (i+1));
                    btn.setActionCommand(Integer.toString(i+1));
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            System.out.println("'Character: " + event.getActionCommand() + "' button pressed");
                            new CharacterWorldWindow(character.getPlayer().getCharacters().get(Integer.parseInt(event.getActionCommand()) - 1), btn, containerPanel);
                        }
                    });
                    containerPanel.add(btn);
                }
                containerPanel.setVisible(true);
                getFrame().dispose();
                character.getPlayer().getWindow().print("Character deleted successfully");
                character.getPlayer().getWindow().setVisible(true);
            }
        });
        this.add(characterRemovalButton);


        this.setLocationRelativeTo(null);
        this.setVisible(true); // refresh window
    }

    // create a random enemy to spectate and attack
    public void generateEnemy(){
        this.enemyGenerator = new EnemyGenerator();
        this.print1(enemyGenerator.getWarDetails());
    }

    // Attack the currently-visible (on top right text field) enemy and decide who wins with a simple algorithm
    public String attackOpponent(){
        String emoji = "cxxx{}::::::::::::::::::::::::::::>";
        print1(emoji);
        String message;
        for(int i = 0 ; i < 3 ; i++ ){
            message = "Attacking";
            print(message);
            Delayer.delay(500);
            for(int j = 0 ; j < 3 ; j++){
                message = message + ".";
                print(message);
                Delayer.delay(500);
            }
        }

        if (this.character.getSumOfStats() < this.enemyGenerator.getSumOfStats()){
            int goldReduction;
            if (this.character.getScore() > 0){
                this.character.takeScore();
            }
            goldReduction = (this.enemyGenerator.getGoldReduced());
            this.character.addGold(-goldReduction);
            emoji = ":(";
            message = "DEFEAT! Enemy was more powerful than you.\n\n" +
                    "Loss:\n" +
                    "-1 score (current score: " + this.character.getScore() +
                    ")\n-" + goldReduction + "gold (current gold: " + this.character.getGoldAmount() + ")";
        }
        else if (this.character.getSumOfStats() > this.enemyGenerator.getSumOfStats()){
            emoji = ":D";
            this.character.giveScore();
            int goldIncrement = this.enemyGenerator.getGoldReduced();
            this.character.addGold(goldIncrement);
            message = "VICTORY! You beat the enemy.\n\n" + "Gains:\n" +
                    "+1 score (current score: " + this.character.getScore() +
                    ")\n+" + goldIncrement + "gold (current gold: " + this.character.getGoldAmount() + ")";
        }
        else{
            message = "DRAW! No losses or gains.";
        }
        generateEnemy();
        print1(emoji);
        return message;
    }



}