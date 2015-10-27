package romaneo.unificado.stateMachine;

import java.util.LinkedHashMap;

import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;

/**
 * Estado
 * 
 * @author ehidalgo
 *
 */
public class State {

	private LinkedHashMap<String, State> transitions;
	private String name;

	public State() {
	}

	public State(String name, LinkedHashMap<String, State> transitions) {
		this.name = name;
		this.transitions = transitions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTransitions(LinkedHashMap<String, State> transitions) {
		this.transitions = transitions;
	}

	public State fire(StateEvent event, Object object) {
		return fire(event, object, Labels.getLabel("statesMachine.fireBy") + event.getName());
	}

	public State fire(StateEvent event, Object object, String observation) {

		if (transitions == null)
			return this;

		State newState = transitions.get(event.getName());

		if (newState == null) {
			return this;
		}

		return newState;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof State))
			return false;
		State state = (State) object;
		return this.name.equalsIgnoreCase(state.name);
	}

	public Boolean isFinalState() {
		StateMachine documentFinalStates = (StateMachine) SpringUtil.getBean("DocumentFinalStates");
		for (State state : documentFinalStates.getStates()) {
			if (state.equals(this)) {
				return true;
			}
		}
		return false;
	}

}