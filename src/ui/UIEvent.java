package WaterPhysics.ui;

public class UIEvent {

	String eventType;
	int[]    iargs;
	double[] dargs;

	public UIEvent(String eventType, int[] iargs, double[] dargs) {
		this.eventType = eventType;
		this.iargs = iargs;
		this.dargs = dargs;
	}

	public UIEvent(String eventType, double[] dargs) {
		this.eventType = eventType;
		this.dargs = dargs;
	}

	public UIEvent(String eventType, int[] iargs) {
		this.eventType = eventType;
		this.iargs = iargs;
	}

	public UIEvent(String eventType) {
		this.eventType = eventType;
	}
}
