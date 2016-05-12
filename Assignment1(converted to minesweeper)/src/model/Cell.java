package model;

public class Cell {
	
	// Cell variables
	public char type;
	private String txt;
	private int falseFlagsNear;

	Cell() {
		
		// Cell defaults; 
		type = 'N';
		txt = "0";
		falseFlagsNear = 0;
	}

	Cell(char type) {
		
		this.type = type;
		this.txt = "" + type;
		falseFlagsNear = 0;
	}

	public void incrementFalseFlagsNear() {
		
		if (type == 'N') {
			this.falseFlagsNear++;
			updateText();
		}
	}

	private void updateText() {
		
		this.txt = "" + falseFlagsNear;
	}

	@Override
	public String toString() {
		
		return txt;
	}

}
