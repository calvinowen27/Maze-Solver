import graphics.MazeCanvas;

public class Program {
	public static int rows = 10;
	public static int columns = 10;
	public static int size = 16;
	
	public static void main(String []args) {
		MazeCanvas canvas = new MazeCanvas(rows, columns, size);
		Maze maze = new Maze(canvas);
		canvas.open();
		maze.initialize();
		Generator generator = new Generator(canvas, maze);
		generator.run();
		Solver solver = new Solver(canvas, maze);
		solver.run();
		canvas.pause();
		canvas.close();
	}
}
