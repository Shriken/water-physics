import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class UI {

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;

	private Simulation sim;
	private LinkedList<UIEvent> events;
	private ArrayList<UIWidget> widgets;

	private JFrame frame;
	private Canvas screen;

	public UI(Simulation sim) {
		this.sim = sim;
		events = new LinkedList<UIEvent>();
		widgets = new ArrayList<UIWidget>();

		// initialize the screen canvas
		screen = new Canvas();
		Dimension size = new Dimension(WIDTH, HEIGHT);
		screen.setMinimumSize(size);
		screen.setMaximumSize(size);
		screen.setPreferredSize(size);

		// initialize the frame
		frame = new JFrame("Water Physics Simulator");
		frame.add(screen);
		frame.pack();
		frame.setVisible(true);
	}

	public UIEvent getEvent() {
		return events.poll();
	}

	public void render() {
		// TODO write UI.render()
	}
}
