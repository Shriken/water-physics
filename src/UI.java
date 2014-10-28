import java.util.ArrayList;
import java.util.LinkedList;

public class UI {

	private Simulation sim;
	private LinkedList<UIEvent> events;

	public UI(Simulation sim) {
		this.sim = sim;
		events = new LinkedList<UIEvent>();
	}

	public UIEvent getEvent() {
		return events.poll();
	}

	public void render() {
		// TODO write UI.render()
	}
}
