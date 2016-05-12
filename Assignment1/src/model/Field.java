package model;

import java.util.Random;

public class Field {

	// Field's main variables
	private int trueFlagCount;
	private int falseFlagCount;
	private int maxFalseFlags;
	public int rows;
	public int colums;
	private int maxTrueFlags;

	// Field's main objects
	public Cell[][] cells;
	private Random random;

	public Field(int rows, int colums, String dificulty) {

		// Field variable setup
		maxFalseFlags = flagCalc(false, dificulty, rows, colums);
		maxTrueFlags = flagCalc(true, dificulty, rows, colums);
		this.rows = rows;
		this.colums = colums;

		// object setup
		cells = new Cell[rows][colums];
		random = new Random();

		// sets cell to a type, adds them to the cells array and call cell
		// string update
		for (int i = 0; i < cells.length; ++i) {

			for (int j = 0; j < cells[i].length; ++j) {

				// 3 cell types (True flag, False flag and Normal cell)
				int type = random.nextInt(3) + 1;
				Cell cell;

				if (type == 3 && falseFlagCount < maxFalseFlags) {
					cell = new Cell('F');
					falseFlagCount++;

				} else if (type == 2 && trueFlagCount < maxTrueFlags) {
					cell = new Cell('T');
					trueFlagCount++;

				} else {
					cell = new Cell();
				}

				// makes sure cells don't get made twice
				int x = random.nextInt(cells.length);
				int y = random.nextInt(cells[i].length);
				while (cells[x][y] != null) {
					x = random.nextInt(cells.length);
					y = random.nextInt(cells[i].length);
				}

				cells[x][y] = cell;
			}
		}

		// set up text for cells here
		modifyText();
	}

	// sets up the text for each cell
	private void modifyText() {

		for (int i = 0; i < cells.length; ++i) {

			for (int j = 0; j < cells[i].length; ++j) {

				if (cells[i][j].type == 'F') {

					try {
						cells[i][j - 1].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i][j + 1].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i + 1][j - 1].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i + 1][j + 1].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i + 1][j].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i - 1][j - 1].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i - 1][j + 1].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i - 1][j].incrementFalseFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}

				} else if (cells[i][j].type == 'T') {
					try {
						cells[i][j - 1].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i][j + 1].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i + 1][j - 1].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i + 1][j + 1].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i + 1][j].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i - 1][j - 1].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i - 1][j + 1].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
					try {
						cells[i - 1][j].incrementTrueFlagsNear();
					} catch (IndexOutOfBoundsException e) {
					}
				}
			}
		}
	}

	// calculates the max number of true or false flags within constraints
	private static int flagCalc(boolean type, String dificulty, int rows,
			int colums) {

		int flags = 0;

		if (type) {

			if (dificulty == "Easy") {
				flags = (int) Math.ceil((rows * colums) * 0.01);
			} else if (dificulty == "Medium") {
				flags = (int) Math.ceil((rows * colums) * 0.05);
			} else {
				flags = (int) Math.ceil((rows * colums) * 0.10);
			}
		} else {

			if (dificulty == "Easy") {
				flags = (int) Math.ceil((rows * colums) * 0.05);
			} else if (dificulty == "Medium") {
				flags = (int) Math.ceil((rows * colums) * 0.10);
			} else {
				flags = (int) Math.ceil((rows * colums) * 0.20);
			}
		}
		// rounds up to the mearest whole number
		return flags;
	}

	@Override
	public String toString() {
		
		String outputText = "";
		
		for (int i = 0; i < cells.length; ++i) {
			for (int j = 0; j < cells[i].length; ++j) {
				outputText = outputText + cells[i][j] + " ";
			}
			outputText = outputText + "\n";
		}

		return outputText;
	}

	// false Flags getter
	public int getFalseFlags() {
		return maxFalseFlags;
	}

}
