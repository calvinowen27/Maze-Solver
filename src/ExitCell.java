import java.awt.Color;

import graphics.MazeCanvas;

public class ExitCell extends EdgeCell {
	private Color exitShadeColor = new Color(0, 255, 0);
	
	public ExitCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas, row, col);
		mazeCanvas.drawShade(row, col, exitShadeColor);
	}

}
