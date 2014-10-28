package WaterPhysics.ui.widget;

import WaterPhysics.Simulation;
import WaterPhysics.ui.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class UIWidget {

	private final int x, y;
	private final int width, height;

	private Simulation sim;

	public UIWidget(int x, int y, int width, int height, Simulation sim) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sim = sim;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth()  { return width;  }
	public int getHeight() { return height; }

	public abstract void render(Graphics g);
	public abstract void mouseClicked(MouseEvent e);
}
