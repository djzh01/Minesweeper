import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinesweeperDriver {

	/**
	 * Private variable representing the board; GUI
	 */
	private JPanel board;
	
	/**
	 * 2D array of JButtons to act as each individual spot in the board
	 */
	private JButton[][] buttons;
	
	/**
	 * 2D array of ints representing the number of mines touching each spot
	 */
	private static int[][] values;
	
	/**
	 * Final int acting as the length of the board
	 * Area = Board_size^2
	 */
	private static final int BOARD_SIZE = 20;
	
	/**
	 * Int defining the size of each individual button
	 */
	private final int BUTTON_SIZE = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 100;
	
	/**
	 * Int defining the size of the whole board
	 */
	private final int PANEL_SIZE = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int difficulty;

	public static void setValue() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {

				// TOP LEFT CORNER
				if (row == 0 && col == 0 && values[row][col] != -1) {
					for (int x = 0; x <= 1; x++)
						for (int y = 0; y <= 1; y++)
							// if (values[row][col] != -1)
							if (values[x][y] == -1)
								values[row][col]++;

				}

				// TOP RIGHT CORNER
				else if (row == 0 && col == BOARD_SIZE - 1 && values[row][col] != -1) {
					for (int x = 0; x <= 1; x++)
						for (int y = BOARD_SIZE - 1; y >= BOARD_SIZE - 2; y--)
							// if (values[row][col] != -1)
							if (values[x][y] == -1)
								values[row][col]++;
				}

				// BOT LEFT CORNER
				else if (row == BOARD_SIZE - 1 && col == 0 && values[row][col] != -1) {
					for (int x = BOARD_SIZE - 1; x >= BOARD_SIZE - 2; x--)
						for (int y = 0; y <= 1; y++)
							// if (values[row][col] != -1)
							if (values[x][y] == -1)
								values[row][col]++;
				}

				// BOT RIGHT CORNER
				else if (row == BOARD_SIZE - 1 && col == BOARD_SIZE - 1 && values[row][col] != -1) {
					for (int x = BOARD_SIZE - 1; x >= BOARD_SIZE - 2; x--)
						for (int y = BOARD_SIZE - 1; y >= BOARD_SIZE - 2; y--)
							// if (values[row][col] != -1)
							if (values[x][y] == -1)
								values[row][col]++;
				}

				// TOP
				else if (row == 0 && values[row][col] != -1) {
					for (int x = 0; x <= 1; x++)
						for (int y = -1; y <= 1; y++)
							// if (values[row][col] != -1)
							if (values[row + x][col + y] == -1)
								values[row][col]++;
				}

				// BOT
				else if (row == BOARD_SIZE - 1 && values[row][col] != -1) {
					for (int x = -1; x <= 0; x++)
						for (int y = -1; y <= 1; y++)
							// if (values[row][col] != -1)
							if (values[row + x][col + y] == -1)
								values[row][col]++;
				}
				// LEFT
				else if (col == 0 && values[row][col] != -1) {
					for (int x = -1; x <= 1; x++)
						for (int y = 0; y <= 1; y++)
							// if (values[row][col] != -1)
							if (values[row + x][col + y] == -1)
								values[row][col]++;
				}
				// RIGHT
				else if (col == BOARD_SIZE - 1 && values[row][col] != -1) {
					for (int x = -1; x <= 1; x++)
						for (int y = -1; y <= 0; y++)
							// if (values[row][col] != -1)
							if (values[row + x][col + y] == -1)
								values[row][col]++;
				} else {
					for (int x = -1; x <= 1; x++)
						for (int y = -1; y <= 1; y++)
							if (values[row][col] != -1)
								if (values[row + x][col + y] == -1)
									values[row][col]++;
				}
			}
		}
	}

	public void addComponentsToPanel(Container panel) {
		difficulty = 10;
		board = new JPanel();
		JPanel rBorder = new JPanel();
		JPanel lBorder = new JPanel();
		// rBorder.setSize((int) (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		// lBorder.setSize((int) (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4, (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		board.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
		values = new int[BOARD_SIZE][BOARD_SIZE];

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setSize(BUTTON_SIZE, BUTTON_SIZE);
				final int x = i;
				final int y = j;
				class SweepButton implements MouseListener {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						if (SwingUtilities.isRightMouseButton(e)) {
							if (buttons[x][y].getBackground() != Color.blue)
								buttons[x][y].setBackground(Color.blue);
							else
								buttons[x][y].setBackground(null);

						} else if (SwingUtilities.isLeftMouseButton(e)) {
							if (values[x][y] == -1) {
								endGame();
								// all squares become red
							} else if (values[x][y] == 0) {
								showBoard(x, y);
							} else {
								buttons[x][y].setText(values[x][y] + "");
								buttons[x][y].setEnabled(false);
								buttons[x][y].setBackground(null);
							}
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				}
				buttons[i][j].addMouseListener(new SweepButton());
				board.add(buttons[i][j]);
				if ((int) (Math.random() * difficulty) == 1) {
					values[i][j] = -1;
				} else
					values[i][j] = 0;

			}
		}
		setValue();

		panel.add(board, BorderLayout.CENTER);

	}

	public void showBoard(int row, int col) {
		buttons[row][col].setEnabled(false);
		buttons[row][col].setBackground(null);
		if (values[row][col] != 0) {
			buttons[row][col].setText("" + values[row][col]);
			// buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 4));
		} else if (values[row][col] == 0) {
			// ADJACENT
			if (!isPastBorder(row + 1, col))
				if (values[row + 1][col] != -1 && buttons[row + 1][col].isEnabled())
					showBoard(row + 1, col);
			if (!isPastBorder(row - 1, col))
				if (values[row - 1][col] != -1 && buttons[row - 1][col].isEnabled())
					showBoard(row - 1, col);
			if (!isPastBorder(row, col + 1))
				if (values[row][col + 1] != -1 && buttons[row][col + 1].isEnabled())
					showBoard(row, col + 1);
			if (!isPastBorder(row, col - 1))
				if (values[row][col - 1] != -1 && buttons[row][col - 1].isEnabled())
					showBoard(row, col - 1);
			// DIAGONALS
			if (!isPastBorder(row + 1, col + 1))
				if (values[row + 1][col + 1] != -1 && buttons[row + 1][col + 1].isEnabled())
					showBoard(row + 1, col + 1);
			if (!isPastBorder(row - 1, col + 1))
				if (values[row - 1][col + 1] != -1 && buttons[row - 1][col + 1].isEnabled())
					showBoard(row - 1, col + 1);
			if (!isPastBorder(row - 1, col - 1))
				if (values[row - 1][col - 1] != -1 && buttons[row - 1][col - 1].isEnabled())
					showBoard(row - 1, col - 1);
			if (!isPastBorder(row + 1, col - 1))
				if (values[row + 1][col - 1] != -1 && buttons[row + 1][col - 1].isEnabled())
					showBoard(row + 1, col - 1);
		}
	}

	private boolean isPastBorder(int row, int col) {
		if (row >= BOARD_SIZE || col >= BOARD_SIZE || row < 0 || col < 0)
			return true;
		return false;
	}

	public void endGame() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (values[i][j] == -1) {
					buttons[i][j].setBackground(Color.red);
				} else {
					if (values[i][j] != 0) {
						buttons[i][j].setText(values[i][j] + "");
						buttons[i][j].setBackground(Color.cyan);
					}
					else {
						buttons[i][j].setBackground(Color.blue);
					}
				}
				buttons[i][j].setEnabled(false);

			}
		}
	}

	public void setupAndRun() {
		JFrame frame = new JFrame();
		frame.setTitle("Minesweeper");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPanel(frame.getContentPane());
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		MinesweeperDriver md = new MinesweeperDriver();
		md.setupAndRun();
	}
}
