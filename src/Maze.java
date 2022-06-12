import java.awt.Color;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class Maze {
	private MazeCanvas canvas;
	Cell[][] gridOfCells;
	
	//colors
	private Color red = new Color(255, 0, 0);
	private Color darkred = new Color(150, 0, 0);
	private int[] entryCell = new int[2]; //entry and exit cell arrays store the row in index 0 and the column in index 1
	private int[] exitCell = new int[2];
	
	public Maze(MazeCanvas mazeCanvas) {
		canvas = mazeCanvas;
		gridOfCells = new Cell[canvas.getRows()][canvas.getCols()];
	}
	
	public void genSnake() {
		for(int row = 0; row < canvas.getRows(); row ++) {
			for(int col = 0; col < canvas.getCols(); col ++) {
				if(row == 0 && (col % 2) == 0) {
					canvas.drawWall(row, col, Side.Top);			//5 different types of cells
					canvas.drawWall(row, col, Side.Right);			//2 types on each top and bottom
					canvas.drawPath(row, col, Side.Left, red);		//and cells along the center
					canvas.drawPath(row, col, Side.Bottom, red);
					canvas.drawCenter(row, col, darkred);
				} else if(row == 0 && (col % 2) != 0) {
					canvas.drawWall(row, col, Side.Top);
					canvas.drawWall(row, col, Side.Left);
					canvas.drawPath(row, col, Side.Right, red);
					canvas.drawPath(row, col, Side.Bottom, red);
					canvas.drawCenter(row, col, darkred);
				} else if(row > 0 && row < (canvas.getRows() - 1)) {
					canvas.drawWall(row, col, Side.Right);
					canvas.drawWall(row, col, Side.Left);
					canvas.drawPath(row, col, Side.Top, red);
					canvas.drawPath(row, col, Side.Bottom, red);
					canvas.drawCenter(row, col, red);
				} else if(row == (canvas.getRows() - 1) && (col % 2) == 0) {
					canvas.drawWall(row, col, Side.Bottom);
					canvas.drawWall(row, col, Side.Left);
					canvas.drawPath(row, col, Side.Right, red);
					canvas.drawPath(row, col, Side.Top, red);
					canvas.drawCenter(row, col, darkred);
				} else if(row == (canvas.getRows() - 1) && (col % 2) != 0) {
					canvas.drawWall(row, col, Side.Bottom);
					canvas.drawWall(row, col, Side.Right);
					canvas.drawPath(row, col, Side.Left, red);
					canvas.drawPath(row, col, Side.Top, red);
					canvas.drawCenter(row, col, darkred);
				}
			}
		}
	}
	
	public void initialize() {
		//normal, edge, entry, and exit cell spawning
		int perimeter = 2 * canvas.getRows() + 2 * canvas.getCols();
		int entry = (int)(Math.random() * perimeter);
		int exit = (int)(Math.random() * perimeter);
		while(Math.abs(entry - exit) < perimeter / 3) {	//while loop is to make sure entry and exit are decently far away from each other
			entry = (int)(Math.random() * perimeter);
			exit = (int)(Math.random() * perimeter);
		}
		int edgeCount = 0;
		for(int row = 0; row < canvas.getRows(); row ++) {
			for(int col = 0; col < canvas.getCols(); col ++) {
				if(row == 0 || col == 0 || col == canvas.getCols() - 1 || row == canvas.getRows() - 1) {
					if(edgeCount == entry) {
						gridOfCells[row][col] = new EntryCell(canvas, row, col);
						entryCell[0] = row;
						entryCell[1] = col;
					} else if(edgeCount == exit) {
						gridOfCells[row][col] = new ExitCell(canvas, row, col);
						exitCell[0] = row;
						exitCell[1] = col;
					} else {
						gridOfCells[row][col] = new EdgeCell(canvas, row, col);
					}
					edgeCount ++;
				} else {
					gridOfCells[row][col] = new Cell(canvas, row, col);
				}
			}
		}
		
		//block cell spawning
		int blockCellCount = canvas.getRows() * canvas.getCols() / 20 - 1;
		while(blockCellCount > 0) {
			for(int row = 0; row < canvas.getRows(); row ++) {
				for(int col = 0; col < canvas.getCols(); col ++) {
					if(!(gridOfCells[row][col] instanceof ShadedCell)) {
						if(Math.random() < 0.05 && blockCellCount > 0 && !((gridOfCells[row - 1][col] instanceof BlockCell) || (gridOfCells[row][col - 1] instanceof BlockCell) || (gridOfCells[row + 1][col] instanceof BlockCell) || (gridOfCells[row][col + 1] instanceof BlockCell))) {
							gridOfCells[row][col] = new BlockCell(canvas, row, col);
							blockCellCount --;
						}
					}
				}
			}
		}
	}
	
	public Cell getCell(int row, int col) {
		return gridOfCells[row][col];
	}
	
	public Cell getEntryCell() {
		return gridOfCells[entryCell[0]][entryCell[1]];
	}
	
	public Cell getExitCell() {
		return gridOfCells[exitCell[0]][exitCell[1]];
	}
	
	public Cell getNeighbor(Cell cell, Side side) {
		Cell neighbor = null;
		if(side == Side.Top && cell.getRow() > 0)
			neighbor = gridOfCells[cell.getRow() - 1][cell.getCol()];
		else if(side == Side.Bottom && cell.getRow() < gridOfCells.length - 1)
			neighbor = gridOfCells[cell.getRow() + 1][cell.getCol()];
		else if(side == Side.Left && cell.getCol() > 0)
			neighbor = gridOfCells[cell.getRow()][cell.getCol() - 1];
		else if(side == Side.Right && cell.getCol() < gridOfCells[0].length - 1)
			neighbor = gridOfCells[cell.getRow()][cell.getCol() + 1];
		return neighbor;
	}
}
