package br.com.tarefas.modelo;

public class TarefaEvent {
	 
	private Long eventId;
	private String eventType;
	
	public TarefaEvent(Long eventId, String eventType) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
	}
	
	public TarefaEvent() {
		
	}
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarefaEvent other = (TarefaEvent) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TarefaEvent [eventId=" + eventId + ", eventType=" + eventType + "]";
	}
	
}
