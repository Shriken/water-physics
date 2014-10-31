package WaterPhysics;

import WaterPhysics.ui.UI;
import WaterPhysics.ui.UIEvent;

public class Simulator {

	static final long TICK_LEN = 1000 / 60; // 60 FPS

	Simulation sim;
	UI ui;

	boolean running;

	public Simulator() {
		sim = new Simulation();
		ui = new UI(sim);
	}

	public void simulate() {
		running = true;

		while (running) {
			long startTime = System.currentTimeMillis();

			// check UI for ui events
			for (UIEvent e; (e = ui.getEvent()) != null;) {
				processEvent(e);
			}

			// update simulation
			sim.update();

			// tell ui to render simulation
			ui.render();

			long endTime = System.currentTimeMillis();

			if (endTime - startTime > TICK_LEN) continue;

			// if the tick took less time than TICK_LEN to run, sleep
			try {
				Thread.sleep(TICK_LEN - (endTime - startTime));
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public void processEvent(UIEvent e) {
		if (e.eventType.equals("height-update")) {
			sim.setZ(e.iargs[0], e.iargs[1], 1000);
		}
	}

	public static void main(String[] args) {
		Simulator sim = new Simulator();
		sim.simulate();
	}
}
