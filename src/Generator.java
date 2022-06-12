import java.awt.Color;
import java.util.ArrayList;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Generator extends Explorer {
	
	private static final Color pathColor = new Color(255, 255, 0);
	
	public Generator(MazeCanvas mc, Maze m) {
		super(mc, m, pathColor, null);
	}
	
	protected boolean onEnterCell(Cell cell, Side fromSide) {
		return super.onEnterCell(cell, fromSide);
	}
	
	protected ArrayList<Side> onGetNextSteps(Cell cell) {
		return shuffle(cell.getWalls());
	}
	
	protected void onStepForward(Cell cell, Side side) {
		super.onStepForward(cell, side);
		cell.removeWall(side);
	}
}
