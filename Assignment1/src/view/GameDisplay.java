package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Field;

public class GameDisplay extends JPanel {

	public JButton[][] buttons;
	private Field field;

	public GameDisplay(Field field) {

		this.field = field;
		setLayout(new GridLayout(field.rows, field.colums));

		// creates buttons
		buttons = new JButton[field.rows][field.colums];
		for (int i = 0; i < buttons.length; ++i) {
			for (int j = 0; j < buttons[i].length; ++j) {
				JButton btn;
				btn = new JButton();
				btn.setActionCommand(i + "/" + j);
				btn.setForeground(Color.BLACK);
				buttons[i][j] = btn;

			}
		}

		// adds buttons
		for (int i = 0; i < buttons.length; ++i) {
			for (int j = 0; j < buttons[i].length; ++j) {
				this.add(buttons[i][j]);
			}
		}
	}

	public void addButtonHandler(ActionListener listener) {
		for (int i = 0; i < buttons.length; ++i) {
			for (int j = 0; j < buttons[i].length; ++j) {
				buttons[i][j].addActionListener(listener);
			}
		}
	}

	// sets button properties once pressed
	public boolean setTileText(String actionCommand) {
		int rowLocation = getCordinates(actionCommand)[0];
		int columLocation = getCordinates(actionCommand)[1];
		String typeString = field.cells[rowLocation][columLocation].toString();
		if (buttons[rowLocation][columLocation].getText() == "") {

			buttons[rowLocation][columLocation].setText(typeString);
			if (typeString.startsWith("T")) {
				buttons[rowLocation][columLocation].setBackground(Color.BLUE);
				buttons[rowLocation][columLocation].setForeground(Color.WHITE);
			} else if (typeString.startsWith("F")) {
				buttons[rowLocation][columLocation].setBackground(Color.RED);
			} else if (typeString == "0/0") { // not redundant
				buttons[rowLocation][columLocation].setBackground(Color.GREEN);
			} else {
				buttons[rowLocation][columLocation].setBackground(Color.GRAY);
			}
			return true;
		} else {
			return false;
		}
	}

	// uses recursion to reveal empty cells
	public int revealEmptyCells(int x, int y) {
		int numberOfCellsRevealed = 1;

		setTileText(x + "/" + y);

		try {
			if (buttons[x - 1][y].getText() == "") {
				if (field.cells[x - 1][y].type == 'N'
						&& field.cells[x - 1][y].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x - 1, y);
				} else if (field.cells[x - 1][y].type == 'N') {
					setTileText(x - 1 + "/" + y);
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (buttons[x][y - 1].getText() == "") {
				if (field.cells[x][y - 1].type == 'N'
						&& field.cells[x][y - 1].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x, y - 1);
				} else if (field.cells[x][y - 1].type == 'N') {
					numberOfCellsRevealed++;
					setTileText(x + "/" + (y - 1));
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (buttons[x + 1][y].getText() == "") {
				if (field.cells[x + 1][y].type == 'N'
						&& field.cells[x + 1][y].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x + 1, y);
				} else if (field.cells[x + 1][y].type == 'N') {
					setTileText(x + 1 + "/" + (y));
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (buttons[x][y + 1].getText() == "") {
				if (field.cells[x][y + 1].type == 'N'
						&& field.cells[x][y + 1].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x, y + 1);
				} else if (field.cells[x][y + 1].type == 'N') {
					setTileText(x + "/" + (y + 1));
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			if (buttons[x - 1][y - 1].getText() == "") {
				if (field.cells[x - 1][y - 1].type == 'N'
						&& field.cells[x - 1][y - 1].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x - 1, y - 1);
				} else if (field.cells[x - 1][y - 1].type == 'N') {
					setTileText(x - 1 + "/" + (y - 1));
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			if (buttons[x + 1][y + 1].getText() == "") {
				if (field.cells[x + 1][y + 1].type == 'N'
						&& field.cells[x + 1][y + 1].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x + 1, y + 1);
				} else if (field.cells[x + 1][y + 1].type == 'N') {
					setTileText(x + 1 + "/" + (y + 1));
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			if (buttons[x + 1][y - 1].getText() == "") {
				if (field.cells[x + 1][y - 1].type == 'N'
						&& field.cells[x + 1][y - 1].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x + 1, y - 1);
				} else if (field.cells[x + 1][y - 1].type == 'N') {
					setTileText(x + 1 + "/" + (y - 1));
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			if (buttons[x - 1][y + 1].getText() == "") {
				if (field.cells[x - 1][y + 1].type == 'N'
						&& field.cells[x - 1][y + 1].toString() == "0/0") {
					numberOfCellsRevealed += revealEmptyCells(x - 1, y + 1);
				} else if (field.cells[x - 1][y + 1].type == 'N') {
					setTileText(x - 1 + "/" + (y + 1));
					numberOfCellsRevealed++;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		
		return numberOfCellsRevealed;
	}
	
	public static char[] getCordinates(String str) {
		int x;
		int y;
		
		char[] list = str.toCharArray();
		if (list[1] - 48 == -1) {
			x = list[0] - 48;
		} else {
			x = (list[0] - 48) * 10;
			x += list[1] - 48;
		}
		if (list[list.length - 2] - 48 == -1) {
			y = list[list.length - 1] - 48;
		} else {
			y = (list[list.length - 2] - 48) * 10;
			y += list[list.length - 1] - 48;
		}
		char[] out = {(char) x,(char) y};
		return out;
	}
}

