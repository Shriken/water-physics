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
			for (UIEvent e = ui.getEvent(); e != null; e = ui.getEvent()) {
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
