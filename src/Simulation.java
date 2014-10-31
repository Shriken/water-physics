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
		double[][] newHeights = new double[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double corners = (getZ(i-1, j-1) + getZ(i+1, j-1) +
				                  getZ(i+1, j+1) + getZ(i-1, j+1)) / 16;
				double sides   = (getZ(i, j-1) + getZ(i+1, j) +
				                  getZ(i, j+1) + getZ(i-1, j)) / 8;
				newHeights[i][j] = corners + sides + heights[i][j] / 4;
			}
		}

		heights = newHeights;
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
