import java.awt.Color;
import java.util.ArrayList;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Explorer {
	
	protected Maze maze;
	protected MazeCanvas mazeCanvas;
	protected Color fwdPathColor;
	protected Color bktPathColor;

	public Explorer(MazeCanvas mc, Maze m, Color fwdColor, Color bktColor) {
		this.maze = m;
		this.mazeCanvas = mc;
		this.fwdPathColor = fwdColor;
		this.bktPathColor = bktColor;
	}
	
	protected ArrayList<Side> shuffle(ArrayList<Side> sides) {
		ArrayList<Side> shuffledSides = new ArrayList<Side>();
		while(sides.size() > 0) {
			Side randomSide = sides.remove((int)(Math.random() * sides.size()));
			shuffledSides.add(randomSide);
		}
		return shuffledSides;
	}
	
	protected Side getOpposite(Side side) {
		Side oppositeSide = side;
		if(side == Side.Top)
			oppositeSide = Side.Bottom;
		if(side == Side.Bottom)
			oppositeSide = Side.Top;
		if(side == Side.Left)
			oppositeSide = Side.Right;
		if(side == Side.Right)
			oppositeSide = Side.Left;
		return oppositeSide;
	}
	
	public boolean run(Cell cell, Side fromSide) {
		cell.setVisited(true);
		boolean done = onEnterCell(cell, fromSide);
		ArrayList<Side> listOfPaths = onGetNextSteps(cell);
		for(Side side : listOfPaths) {
			if(!done) {
				Cell neighbor = maze.getNeighbor(cell, side);
				if(!neighbor.getVisited()) {
					onStepForward(cell, side);
					mazeCanvas.step(10);
					done = run(neighbor, getOpposite(side));
					onStepBack(done, cell, side);
				}
			}
		}
		onExitCell(done, cell, fromSide);
		return done;
	}
	
	public boolean run() {
		for(int r = 0; r < maze.gridOfCells.length; r++) {
			for(int c = 0; c < maze.gridOfCells[r].length; c++) {
				maze.gridOfCells[r][c].setVisited(false);
			}
		}
		
		return run(maze.getEntryCell(), Side.Center);
	}
	
	protected boolean onEnterCell(Cell cell, Side fromSide) {
		if(fwdPathColor != null) {
			mazeCanvas.drawPath(cell.getRow() ,cell.getCol(), fromSide, fwdPathColor);
			mazeCanvas.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
		}
		return false;
	}
	
	protected ArrayList<Side> onGetNextSteps(Cell cell) {
		return new ArrayList<Side>();
	}
	
	protected void onStepForward(Cell cell, Side side) {
		mazeCanvas.drawPath(cell.getRow(), cell.getCol(), side, fwdPathColor);
	}
	
	protected void onStepBack(boolean done, Cell cell, Side side) {
		if(!done) {
			if(bktPathColor != null) {
				mazeCanvas.drawPath(cell.getRow(), cell.getCol(), side, bktPathColor);
			} else {
				mazeCanvas.erasePath(cell.getRow(), cell.getCol(), side);
			}
		}
	}
	
	protected void onExitCell(boolean done, Cell cell, Side fromSide) {
		if(!done) {
			if(bktPathColor != null) {
				mazeCanvas.drawCenter(cell.getRow(), cell.getCol(), bktPathColor);
				mazeCanvas.drawPath(cell.getRow(), cell.getCol(), fromSide, bktPathColor);
			} else {
				mazeCanvas.eraseCenter(cell.getRow(), cell.getCol());
				mazeCanvas.erasePath(cell.getRow(), cell.getCol(), fromSide);
			}
		}
	}
}
