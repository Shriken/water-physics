import java.util.ArrayList;
import java.util.LinkedList;

public class UI {

	private Simulation sim;
	private LinkedList<UIEvent> events;
	private ArrayList<UIWidget> widgets;

	public UI(Simulation sim) {
		this.sim = sim;
		events = new LinkedList<UIEvent>();
		widgets = new ArrayList<UIWidget>();
	}

	public UIEvent getEvent() {
		return events.poll();
	}

	public void render() {
		// TODO write UI.render()
	}
}
