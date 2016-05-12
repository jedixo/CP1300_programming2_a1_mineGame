import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameDisplay;
import view.MainFrame;
import view.SizeDialog;
import model.Field;

/**
 * CP1300 assignment, the true/false game converted to minesweeper. Written by Jake Dixon (12828309)
 * 
 * This is the CP1300 assignment converted to minesweeper.
 * 
 * @author Jake Dixon (12828309)
 * @see Field
 * @see Cell
 * @see SizeDialog
 * @see GameDisplay
 * @see MainFrame

 */

public class App {

	// game variables
	private static boolean gameActive;
	private static int numberCellsRevealed;
	private static int falseFlags;
	private static String dificulty = "Easy";
	private static int rows;
	private static int colums;

	// the games objects
	private static MainFrame mainFrame;
	private static GameDisplay gamedisplay;
	private static SizeDialog sizedialog = new SizeDialog();
	private static Field field;

	public static void main(String[] string) {

		// setting up the games objects
		rows = sizedialog.rows;
		colums = sizedialog.colums;
		field = new Field(rows, colums, dificulty);
		gamedisplay = new GameDisplay(field);
		mainFrame = new MainFrame(gamedisplay);

		// set up game
		reset();

		// menu listener
		mainFrame.addMenuHandler(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {
				case "Size":
					sizedialog.setVisible(true);
					if (rows != sizedialog.rows || colums != sizedialog.colums) {
						rows = sizedialog.rows;
						colums = sizedialog.colums;
						reset();
					}
					break;

				case "Easy":
					dificulty = "Easy";
					reset();
					break;

				case "Medium":
					dificulty = "Medium";
					reset();
					break;

				case "Hard":
					dificulty = "Hard";

				case "New Game":
					reset();

				}
			}
		});
	}

	// resets the game
	private static void reset() {

		mainFrame.remove(gamedisplay);
		field = new Field(rows, colums, dificulty);
		gamedisplay = new GameDisplay(field);
		mainFrame.add(gamedisplay);
		mainFrame.setVisible(true);

		gameActive = true;
		numberCellsRevealed = 0;
		falseFlags = field.getFalseFlags();

		gamedisplay.addButtonHandler(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (gameActive == true) {

					// gets location of button and sets it's text
					int rowLocation = getCordinates(e.getActionCommand())[0];
					int columLocation = getCordinates(e.getActionCommand())[1];
					String typeString = field.cells[rowLocation][columLocation]
							.toString();

					// checks if button has been pressed before
					if (gamedisplay.setTileText(e.getActionCommand())) {
						numberCellsRevealed++;

						// reveals empty adjacent cells
						if (typeString == "0") {
							numberCellsRevealed -= 1;
							numberCellsRevealed += gamedisplay
									.revealEmptyCells(rowLocation,
											columLocation);
						}
					}

					// checks to see if the game is lost or won after a move
					if (typeString.startsWith("X")) {

						MainFrame.gameOver();
						gameFinished();
					} else if (numberCellsRevealed + falseFlags == rows
							* colums) {
						gameActive = false;
						MainFrame.Win();

					}
				} else {
					MainFrame.notPlaying();
				}
			}
		});

	}

	// reveals tiles
	static void gameFinished() {
		gameActive = false;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < colums; ++j) {
				gamedisplay.setTileText(i + "/" + j);
			}
		}
	}
	
	// grabs the Cordinates out of a string. works for integer from 1 - 99
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
		char[] out = { (char) x, (char) y };
		return out;
	}
}
