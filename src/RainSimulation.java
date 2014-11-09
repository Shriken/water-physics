package WaterPhysics;

public class RainSimulation extends Simulation {

	private final int MAX_TICKS_SINCE_RAIN;
	private int ticksSinceRain;

	public RainSimulation(int maxRainTicks) {
		super();

		MAX_TICKS_SINCE_RAIN = maxRainTicks;
		ticksSinceRain = 0;
	}

	public void update() {
		if (ticksSinceRain > Math.random() * MAX_TICKS_SINCE_RAIN) {
			setZ((int) (Math.random() * width),
			     (int) (Math.random() * height),
			     (int) (Math.random() * 60) + 40);
			ticksSinceRain = 0;
		} else ticksSinceRain++;

		super.update();
	}
}
