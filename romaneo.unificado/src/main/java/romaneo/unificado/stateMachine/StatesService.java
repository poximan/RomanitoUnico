package romaneo.unificado.stateMachine;

import java.util.LinkedHashMap;
import java.util.List;

public interface StatesService {

	public void setResponseEvents(LinkedHashMap<String, StateEvent> responseEvents);

	public List<State> getStates();

	public void setStates(List<State> states);

	public State getStateByName(String stateName);

	public StateEvent getResponseEvent(StateEvent event);

}