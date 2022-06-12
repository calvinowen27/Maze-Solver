import java.util.ArrayList;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Cell {
	private int row;
	private int col;
	private MazeCanvas mazeCanvas;
	private ArrayList<Side> listOfWalls = new ArrayList<Side>();
	private boolean visited = false;
	
	public Cell(MazeCanvas mazeCanvas, int row, int col) {
		this.row = row;
		this.col = col;
		this.mazeCanvas = mazeCanvas;
		listOfWalls.add(Side.Top);
		listOfWalls.add(Side.Left);
		listOfWalls.add(Side.Right);
		listOfWalls.add(Side.Bottom);
		this.mazeCanvas.drawCell(row, col);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public ArrayList<Side> getWalls() {
		ArrayList<Side> listOfWallsTemp = new ArrayList<Side>();
		listOfWallsTemp.addAll(listOfWalls);
		return listOfWallsTemp;
	}
	
	public void removeWall(Side side) {
		listOfWalls.remove(side);
		mazeCanvas.eraseWall(row, col, side);
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public ArrayList<Side> getPaths() {
		ArrayList<Side> listOfPaths = new ArrayList<Side>();
		
		listOfPaths.add(Side.Top);
		listOfPaths.add(Side.Left);
		listOfPaths.add(Side.Right);
		listOfPaths.add(Side.Bottom);
		listOfPaths.removeAll(listOfWalls);
		
		return listOfPaths;
	}
}
