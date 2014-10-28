import java.awt.Graphics;

public abstract class UIWidget {

	final int x, y;
	final int width, height;

	private Simulation sim;

	public UIWidget(int x, int y, int width, int height, Simulation sim) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sim = sim;
	}

	public abstract void render(Graphics g);
}
