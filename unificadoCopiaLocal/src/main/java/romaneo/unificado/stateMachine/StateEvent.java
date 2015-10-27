package romaneo.unificado.stateMachine;

public class StateEvent {
	private String name;

	public StateEvent() {
	}

	public StateEvent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}