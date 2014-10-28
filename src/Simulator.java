package WaterPhysics;

import WaterPhysics.ui.UI;
import WaterPhysics.ui.UIEvent;

public class Simulator {

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
			// check UI for ui events
			for (UIEvent e; (e = ui.getEvent()) != null;) {
				processEvent(e);
			}

			// update simulation
			sim.update();

			// tell ui to render simulation
			ui.render();
		}
	}

	public void processEvent(UIEvent e) {
		// TODO write Simulator.processEvent
	}

	public static void main(String[] args) {
		Simulator sim = new Simulator();
		sim.simulate();
	}
}
