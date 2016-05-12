package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;

import model.Field;

public class GameDisplay extends JPanel {

	public JButton[][] buttons;
	private Field field;
	private int dif;
	private int colx = 255;
	private int coly = 128;

	public GameDisplay(Field field) {

		this.field = field;
		setLayout(new GridLayout(field.rows, field.colums));
		dif =  (int) Math.ceil(128 / (field.rows));
		// creates buttons
		buttons = new JButton[field.rows][field.colums];
		for (int i = 0; i < buttons.length; ++i) {
			for (int j = 0; j < buttons[i].length; ++j) {
				JButton btn;
				btn = new JButton();
				btn.setActionCommand(i + "/" + j);
				btn.setForeground(Color.BLACK);
				btn.setBackground(new Color(colx, 64, 64));
				btn.setBorder(new BorderUIResource.LineBorderUIResource(new Color(124, 58, 58)));
				buttons[i][j] = btn;
				colx = colx-dif;
				
			}
			colx = 255;
			
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

			if (typeString.startsWith("X")) {
				buttons[rowLocation][columLocation].setBackground(new Color(128, 0, 0));
				buttons[rowLocation][columLocation]
						.setForeground(new Color(255, 255, 255));
				buttons[rowLocation][columLocation].setText(typeString);
				buttons[rowLocation][columLocation].setBorder(new BorderUIResource.LineBorderUIResource(Color.GRAY));;

			} else if (typeString == "0") { // not redundant
				buttons[rowLocation][columLocation].setBackground(new Color(79, 154, 82));
				buttons[rowLocation][columLocation].setText(" ");
				buttons[rowLocation][columLocation].setBorder(new BorderUIResource.LineBorderUIResource(Color.GRAY));;
				//buttons[rowLocation][columLocation].setBackground(new Color(
				//		192, 192, 192));
			} else {
				buttons[rowLocation][columLocation].setBorder(new BorderUIResource.LineBorderUIResource(Color.GRAY));;

				buttons[rowLocation][columLocation].setBackground(new Color(
						192, 192, 192));
				buttons[rowLocation][columLocation].setText(typeString);
				if (typeString.contains("1")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(1, 0, 254));
				} else if (typeString.contains("2")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(1, 127, 1));
				} else if (typeString.contains("3")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(254, 0, 0));
				} else if (typeString.contains("4")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(1, 0, 128));
				} else if (typeString.contains("5")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(129, 1, 2));
				} else if (typeString.contains("6")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(0, 127, 126));
				} else if (typeString.contains("8")) {
					buttons[rowLocation][columLocation]
							.setForeground(new Color(128, 128, 128));
				} else {
				}
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
						&& field.cells[x - 1][y].toString() == "0") {
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
						&& field.cells[x][y - 1].toString() == "0") {
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
						&& field.cells[x + 1][y].toString() == "0") {
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
						&& field.cells[x][y + 1].toString() == "0") {
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
						&& field.cells[x - 1][y - 1].toString() == "0") {
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
						&& field.cells[x + 1][y + 1].toString() == "0") {
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
						&& field.cells[x + 1][y - 1].toString() == "0") {
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
						&& field.cells[x - 1][y + 1].toString() == "0") {
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

	public char[] getCordinates(String str) {
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
		char[] out = { (char) x, (char) y };
		return out;
	}
}
