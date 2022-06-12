import java.awt.Color;
import java.util.ArrayList;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Solver extends Explorer {

	public Solver(MazeCanvas mc, Maze m) {
		super(mc, m, Color.red, Color.yellow);
	}
	
	protected boolean onEnterCell(Cell cell, Side fromSide) {
		super.onEnterCell(cell, fromSide);
		return cell == maze.getExitCell();
	}
	
	protected ArrayList<Side> onGetNextSteps(Cell cell) {
		return shuffle(cell.getPaths());
	}
}
