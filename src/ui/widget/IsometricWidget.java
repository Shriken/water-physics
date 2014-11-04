package WaterPhysics.ui.widget;

import WaterPhysics.Simulation;
import WaterPhysics.ui.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class IsometricWidget extends UIWidget {

	// the height over width of a rhombus in the iso-grid
	private static final double ASPECT_RATIO = 0.5;
	double displayScale;

	// the location and dimensions of the display within the widget
	int displayX;
	int displayY;
	int displayWidth;
	int displayHeight;

	public IsometricWidget(int x, int y, int width, int height,
	                       UI parent) {
		super(x, y, width, height, parent);

		// find aspect ratios for simulation, widget
		double simAR = 1d * sim.getHeight() / sim.getWidth();
		double widgAR = 1d * height / width;

		// set the scale for the display to fit in the widget
		if (simAR > widgAR) {
			// simulation is skinny
			displayScale = 1d * height / sim.getHeight();

			displayX = (int) (0.5 * (width - sim.getWidth() *
			                  displayScale));
			displayY = 0;
		} else {
			// simulation is wide
			displayScale = 1d * width / sim.getWidth();

			displayX = 0;
			displayY = (int) (0.5 * (height - sim.getHeight() *
			                  displayScale));
		}
		displayWidth = (int) (sim.getWidth() * displayScale);
		displayHeight = (int) (sim.getHeight() * displayScale);

		System.out.println(width + " " + height);
		System.out.println(displayX + " " + displayY);
		System.out.println(displayWidth + " " + displayHeight);
		System.out.println(displayScale);
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.BLACK);

		int[][][] points = new int[sim.getHeight()][sim.getWidth()][2];

		for (int i = 0; i < sim.getHeight(); i++) {
			for (int j = 0; j < sim.getWidth(); j++) {
				points[i][j] = isoPoint(j, i, (int) sim.getZ(i, j));
			}
		}

		for (int i = 0; i < sim.getHeight(); i++) {
			for (int j = 0; j < sim.getWidth(); j++) {
				if (i < sim.getHeight() - 1) {
					g.drawLine(points[i][j][0], points[i][j][1],
					           points[i+1][j][0], points[i+1][j][1]);
				}
				if (j < sim.getWidth() - 1) {
					g.drawLine(points[i][j][0], points[i][j][1],
					           points[i][j+1][0], points[i][j+1][1]);
				}
			}
		}

		g.drawRect(displayX, displayY, displayWidth, displayHeight);
	}

	// convert a point from the sim to an isometric point in the widget
	private int[] isoPoint(int x, int y, int z) {
		int[] newPoint = new int[2];

		x -= sim.getWidth() / 2;
		y -= sim.getHeight() / 2;

		newPoint[0] = (int) (displayScale * (y - x) / 2);
		newPoint[1] = (int) (displayScale * ((x + y) * ASPECT_RATIO / 2) - z);

		newPoint[0] += displayX + displayWidth  / 2;
		newPoint[1] += displayY + displayHeight / 2;

		return newPoint;
	}

	private int[] isoPoint(int[] coords) {
		return isoPoint(coords[0], coords[1], coords[2]);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO write IsometricWidget.mouseClicked()
	}
}
