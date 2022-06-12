import java.awt.Color;

import graphics.MazeCanvas;

public class BlockCell extends ShadedCell {
	private static Color blockColor = new Color(211, 211, 211);;
	
	public BlockCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas, row, col, blockColor);
	}
	
	public boolean getVisited() {
		return true;
	}
}
