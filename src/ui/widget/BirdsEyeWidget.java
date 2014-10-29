package WaterPhysics.ui.widget;

import WaterPhysics.ui.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class BirdsEyeWidget extends UIWidget {

	double displayScale;
	int displayX;
	int displayY;

	public BirdsEyeWidget(int x, int y, int width, int height,
	                      UI parent) {
		super(x, y, width, height, parent);

		// find aspect ratios for simulation, display
		double simAR = 1d * sim.getHeight() / sim.getWidth();
		double dispAR = 1d * height / width;

		// set the scale for the display to fit in the widget
		if (simAR > dispAR) {
			// simulation is skinny
			displayScale = height / sim.getHeight();

			displayX = 0.5 * (width - sim.getWidth());
			displayY = 0;
		} else {
			// simulation is wide
			displayScale = width / sim.getWidth();

			displayX = 0;
			displayY = 0.5 * (height - sim.getHeight());
		}
	}

	// transform a point in the simulation into the display
	public int[] simToDisplay(int x, int y) {
		int[] out = new int[2];
		out[0] = displayX + displayScale * x;
		out[1] = displayY + displayScale * y;

		return out;
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("BirdsEyeWidget clicked!");
		// TODO implement BirdsEyeWidget.mouseClicked()
	}

	public void render(Graphics g) {
		// TODO implement BirdsEyeWidget.render()
	}
}
