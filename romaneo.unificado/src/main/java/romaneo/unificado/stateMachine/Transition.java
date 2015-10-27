package romaneo.unificado.stateMachine;

public class Transition {
	private State sourceState;
	private State targetState;

	public Transition(State sourceState, State targetState) {
		this.sourceState = sourceState;
		this.targetState = targetState;
	}

	public Transition() {
	}

	public State getSourceState() {
		return sourceState;
	}

	public void setSourceState(State sourceState) {
		this.sourceState = sourceState;
	}

	public State getTargetState() {
		return targetState;
	}

	public void setTargetState(State targetState) {
		this.targetState = targetState;
	}

}