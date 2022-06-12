import java.awt.Color;

import graphics.MazeCanvas;

public class EntryCell extends EdgeCell {
	private Color entryShadeColor = new Color(0, 0, 255);
	
	public EntryCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas, row, col);
		mazeCanvas.drawShade(row, col, entryShadeColor);
	}

}
