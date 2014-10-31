package WaterPhysics;

public class Simulation {

	private final int width, height;
	private double[][][] flows;
	private double[][] heights;

	public Simulation() {
		this(20, 20);
	}

	public Simulation(int width, int height) {
		this.width = width;
		this.height = height;

		flows   = new double[height][width][4];
		heights = new double[height][width];
	}

	public void update() {
		// TODO write Simulation.update()
	}

	public int getWidth()  { return width; }
	public int getHeight() { return height; }

	public double getZ(int x, int y) {
		if (x < 0 || width  <= x) return 0;
		if (y < 0 || height <= y) return 0;
		return heights[y][x];
	}

	public double[] getFlowsAt(int x, int y) {
		return flows[y][x];
	}
}
