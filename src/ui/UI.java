import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class UI {

	private static final int CANVAS_WIDTH = 640;
	private static final int CANVAS_HEIGHT = 480;

	private Simulation sim;
	private LinkedList<UIEvent> events;
	private ArrayList<UIWidget> widgets;

	private JFrame frame;
	private Canvas screen;
	private byte[][] widgetIDs;

	public UI(Simulation sim) {
		this.sim = sim;
		events = new LinkedList<UIEvent>();
		widgets = new ArrayList<UIWidget>();

		// initialize the screen canvas
		screen = new Canvas();
		Dimension size = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
		screen.setMinimumSize(size);
		screen.setMaximumSize(size);
		screen.setPreferredSize(size);

		// initialize the frame
		frame = new JFrame("Water Physics Simulator");
		frame.add(screen);
		frame.pack();
		frame.setVisible(true);

		// create buffer strategy (after showing frame)
		screen.createBufferStrategy(2);

		// initialize the widgets
		initWidgets();
	}

	private void initWidgets() {
		// TODO add widgets to UI
	}

	private void addWidget(UIWidget widget) {
		widgets.add(widget);
		int id = widgets.length; // leave 0 for unassigned

		// fill widget rect with widget id
		for (int i = widget.y; i < widget.y + widget.height; i++) {
			for (int j = widget.x; j < widget.x + widget.width; j++) {
				widgetIDs[i][j] = id;
			}
		}
	}

	public UIEvent getEvent() {
		return events.poll();
	}

	public void render() {
		// get draw graphics
		BufferStrategy bs = screen.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		// draw each widget
		for (UIWidget widget : widgets) {
			widget.render(g);
		}

		// clean up
		bs.show();
		g.dispose();
	}
}
