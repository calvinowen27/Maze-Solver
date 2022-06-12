import java.awt.Color;
import java.util.ArrayList;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class EdgeCell extends ShadedCell {
	private ArrayList<Side> listOfEdges = new ArrayList<Side>();
	private static Color edgeColor = new Color(255, 204, 203);;
	
	public EdgeCell(MazeCanvas mazeCanvas, int row, int col) {
		super(mazeCanvas, row, col, edgeColor);
		if(row == 0) 
			listOfEdges.add(Side.Top);
		if(col == 0) 
			listOfEdges.add(Side.Left);
		if(col == mazeCanvas.getCols() - 1)  
			listOfEdges.add(Side.Right);
		if(row == mazeCanvas.getRows() - 1)
			listOfEdges.add(Side.Bottom);
	}
	
	public ArrayList<Side> getWalls() {
		ArrayList<Side> listOfWalls = super.getWalls();
		for(int i = 0; i < listOfEdges.size(); i ++) {
			listOfWalls.remove(listOfEdges.get(i));
		}
		return listOfWalls;
	}
	
	public ArrayList<Side> getPaths() {
		ArrayList<Side> listOfPaths = super.getPaths();
		listOfPaths.removeAll(listOfEdges);
		return listOfPaths;
	}
}
