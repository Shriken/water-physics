package WaterPhysics;

public class Simulation {
	
	// multiplier for flow determination by height
	private static double FLOW_H_CONST = 0.1;
	// multiplier for flow determination by time
	private static double FLOW_M_CONST = 0.5;
	private static double FLOW_T_CONST = 0.5;
	// multiplier for height determination by flow
	private static double HEIGHT_F_CONST = 1;

	protected final int width, height;
	private double[][][] flows;
	private double[][] heights;

	public Simulation() {
		this(100, 100);
	}

	public Simulation(int width, int height) {
		this.width = width;
		this.height = height;

		heights = new double[height][width];
		flows   = new double[height][width][4];
	}

	public void update() {
		double[][] newHeights = new double[height][width];
		double[][][] newFlows = new double[height][width][4];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// move water based on current flow rates
				double newHeight = 0;
				if (0 < i)
					newHeight += flows[i-1][j][2] - flows[i][j][0];
				if (j < width - 2)
					newHeight += flows[i][j+1][3] - flows[i][j][1];
				if (i < height - 2)
					newHeight += flows[i+1][j][0] - flows[i][j][2];
				if (0 < j)
					newHeight += flows[i][j-1][1] - flows[i][j][3];
				newHeight *= HEIGHT_F_CONST;
				newHeight += heights[i][j];
				newHeights[i][j] = newHeight;

				// update flow rates based on other flow rates
				if (0 < i) {
					double flowDiff = flows[i][j][0] - flows[i-1][j][2];
					if (flowDiff > 0) {
						newFlows[i-1][j][0] += flowDiff / 2 *FLOW_M_CONST;
						newFlows[i-1][j][1] += flowDiff / 4 *FLOW_M_CONST;
						newFlows[i-1][j][3] += flowDiff / 4 *FLOW_M_CONST;
					}
				}
				if (j < width - 2) {
					double flowDiff = flows[i][j][1] - flows[i][j+1][3];
					if (flowDiff > 0) {
						newFlows[i][j+1][1] += flowDiff / 2 *FLOW_M_CONST;
						newFlows[i][j+1][2] += flowDiff / 4 *FLOW_M_CONST;
						newFlows[i][j+1][0] += flowDiff / 4 *FLOW_M_CONST;
					}
				}
				if (i < height - 2) {
					double flowDiff = flows[i][j][2] - flows[i+1][j][0];
					if (flowDiff > 0) {
						newFlows[i+1][j][2] += flowDiff / 2 *FLOW_M_CONST;
						newFlows[i+1][j][1] += flowDiff / 4 *FLOW_M_CONST;
						newFlows[i+1][j][3] += flowDiff / 4 *FLOW_M_CONST;
					}
				}
				if (0 < j) {
					double flowDiff = flows[i][j][3] - flows[i][j-1][1];
					if (flowDiff > 0) {
						newFlows[i][j-1][3] += flowDiff / 2 *FLOW_M_CONST;
						newFlows[i][j-1][2] += flowDiff / 4 *FLOW_M_CONST;
						newFlows[i][j-1][0] += flowDiff / 4 *FLOW_M_CONST;
					}
				}

				// update flow rates based on height differentials
				if (0 < i) {
					double heightDiff = heights[i][j] - heights[i-1][j];
					if (heightDiff > 0)
						newFlows[i][j][0] += heightDiff * FLOW_H_CONST;
				}
				if (j < width - 2) {
					double heightDiff = heights[i][j] - heights[i][j+1];
					if (heightDiff > 0)
						newFlows[i][j][1] += heightDiff * FLOW_H_CONST;
				}
				if (i < height - 2) {
					double heightDiff = heights[i][j] - heights[i+1][j];
					if (heightDiff > 0)
						newFlows[i][j][2] += heightDiff * FLOW_H_CONST;
				}
				if (0 < j) {
					double heightDiff = heights[i][j] - heights[i][j-1];
					if (heightDiff > 0)
						newFlows[i][j][3] += heightDiff * FLOW_H_CONST;
				}

				for (int k = 0; k < 4; k++)
					newFlows[i][j][k] += flows[i][j][k] * FLOW_T_CONST;
			}
		}

		heights = newHeights;
		flows = newFlows;
	}

	public int getWidth()  { return width; }
	public int getHeight() { return height; }

	public double getZ(int x, int y) {
		if (x < 0 || width  <= x) return 0;
		if (y < 0 || height <= y) return 0;
		return heights[y][x];
	}

	public void setZ(int x, int y, int z) {
		heights[y][x] = z;
	}
}
