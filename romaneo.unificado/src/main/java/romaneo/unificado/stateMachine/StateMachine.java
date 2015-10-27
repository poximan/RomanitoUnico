package romaneo.unificado.stateMachine;

import java.util.LinkedHashMap;
import java.util.List;

public class StateMachine implements StatesService {
	private LinkedHashMap<String, StateEvent> responseEvents;
	private List<State> states;

	public StateMachine(List<State> travelStates) {
		super();
		this.states = travelStates;
	}

	public StateMachine() {
	}

	public void setResponseEvents(LinkedHashMap<String, StateEvent> responseEvents) {
		this.responseEvents = responseEvents;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public State getStateByName(String stateName) {
		for (State state : states) {
			if (state.getName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	public StateEvent getResponseEvent(StateEvent event) {
		return responseEvents.get(event.getName());
	}

	public StateEvent getResponseEvent(String eventName) {
		return responseEvents.get(eventName);
	}

}