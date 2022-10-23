import java.awt.*;

// a template window for 2 windows of GUI (I have factored common properties of them in this superclass)
public abstract class WindowBase extends Frame{
	private TextArea textField;
	public WindowBase(String title){
		this.setTitle(title);
		this.setSize(1050, 250);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
		WindowCloser xButton = new WindowCloser();
		this.addWindowListener(xButton);
		this.setVisible(true);
	}

	public void print(String text){
		this.textField.setText(text);
	}

	public WindowBase getFrame() {
		return this;
	}

	public void setTextField(TextArea textField){
		this.textField = textField;
	}

	public TextArea getTextField(){
		return this.textField;
	}
}