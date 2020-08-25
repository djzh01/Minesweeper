import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Buttons extends JFrame{
	
	private int value;
	private JButton button;
	
	public Buttons() {
		this.value = 0;
		button = new JButton();
		setUpButtons();
	}
	public Buttons(int num) {
		this.value = num;
		button = new JButton();
		setUpButtons();
	}
	public JButton getButton() {
		return button;
	}
	
	public void setValue(int num) {
		this.value = num;
	}
	
	private void setUpButtons() {
		button.setText(""+value);
		button.setOpaque(true);
	}
}
