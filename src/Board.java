import javax.swing.*;


public class Board {
	private int size;
	private JPanel gameboard;
	private Buttons[][] buttonList;
	
	public Board(int size) {
		buttonList = new Buttons[size][size];
		this.size = size;
		gameboard = new JPanel();
		gameboard.setSize(size, size);
		for(int i = 0; i<this.size; i++) {
			for(int j = 0; j<this.size; j++) {
				if((int) (Math.random() * 10) == 1) {
					buttonList[i][j] = new Buttons(-1);
					gameboard.add(buttonList[i][j].getButton());
				}
				else {
					buttonList[i][j] = new Buttons();
					gameboard.add(buttonList[i][j].getButton());
				}
			}
		}
		
	}
	
	public JPanel getGameboard() {
		return gameboard;
	}
}
