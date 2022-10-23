import java.awt.*;
import java.awt.event.*;

// a class to prompt user to submit some details (such as new character info and enter userId to load game)
public class Prompt extends Frame{

    private final Button submit;

    public Prompt(){
        this.setLayout(new GridLayout(0,2));
        submit = new Button("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt) {
                ((Frame)(evt.getSource())).dispose();
            }
        });
    }

    public void addSubmitListener(ActionListener listener){
        submit.addActionListener(listener);
    }

    // sizing, positioning, and making prompt visible
    public void activate(){
        this.add(submit);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
