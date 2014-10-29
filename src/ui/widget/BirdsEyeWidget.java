package WaterPhysics.ui.widget;

import WaterPhysics.ui.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class BirdsEyeWidget extends UIWidget {

	double displayScale;
	int displayX;
	int displayY;
	int displayWidth;
	int displayHeight;

	public BirdsEyeWidget(int x, int y, int width, int height,
	                      UI parent) {
		super(x, y, width, height, parent);

		// find aspect ratios for simulation, display
		double simAR = 1d * sim.getHeight() / sim.getWidth();
		double dispAR = 1d * height / width;

		// set the scale for the display to fit in the widget
		if (simAR > dispAR) {
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
	}

	// transform a point in the simulation into the display
	public int[] simToDisplay(int x, int y) {
		int[] out = new int[2];
		out[0] = displayX + (int) (displayScale * x);
		out[1] = displayY + (int) (displayScale * y);

		return out;
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("BirdsEyeWidget clicked!");
		// TODO implement BirdsEyeWidget.mouseClicked()
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(displayX, displayY, displayWidth, displayHeight);
	}
}
