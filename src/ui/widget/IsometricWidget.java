package WaterPhysics.ui.widget;

import WaterPhysics.Simulation;
import WaterPhysics.ui.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class IsometricWidget extends UIWidget {

	// the height over width of a rhombus in the iso-grid
	double aspectRatio;
	double displayScale;

	public IsometricWidget(int x, int y, int width, int height,
	                       UI parent) {
		super(x, y, width, height, parent);
	}

	public void render(Graphics g) {
		// TODO write IsometricWidget.render()
	}

	// convert a point from the sim to an isometric point in the widget
	private int[] isometrizePoint(int x, int y, int z) {
		int[] newPoint = new int[2];
		newPoint[0] = (int) (displayScale * (x + y) / 2);
		newPoint[1] = (int) (displayScale * (x + y) * aspectRatio / 2);

		return newPoint;
	}

	private int[] isometrizePoint(int[] coords) {
		return isometrizePoint(coords[0], coords[1], coords[2]);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO write IsometricWidget.mouseClicked()
	}
}
