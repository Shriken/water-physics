package WaterPhysics.ui.widget;

import WaterPhysics.ui.UI;
import WaterPhysics.ui.UIEvent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class BirdsEyeWidget extends UIWidget {

	double displayScale;

	// the location and dimensions of the display within the widget
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
		int x = (e.getX() - displayX) / ((int) displayScale);
		int y = (e.getY() - displayY) / ((int) displayScale);

		UIEvent uie = new UIEvent("height-update", new int[]{x,y});
		parent.addEvent(uie);
	}

	public void render(Graphics g) {
		int simW = sim.getWidth();
		int simH = sim.getHeight();

		for (int i = 0; i < simH; i++) {
			for (int j = 0; j < simW; j++) {
				// find the tile's color based on the height
				double height = sim.getZ(j, i);
				int brt = (int) (Math.atan(height) * 255 / Math.PI) + 128;

				g.setColor(new Color(brt, brt, brt));
				g.fillRect(displayX + (int) (displayScale * j),
				           displayY + (int) (displayScale * i),
				           (int) displayScale, (int) displayScale);
			}
		}

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
	}
}
