package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="message_types")
public class MessageType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="data_class", nullable=false, length=150)
	private String dataClass;

	@Column(nullable=false, length=45)
	private String name;

	@Column(name="procedure")
	private String procedure;

	private Integer priority;

	@Column(nullable=false)
	private Integer retries;

	@Column(nullable=false, length=1)
	private String sychronised;

	@Column(nullable=false)
	private Integer timeout;

	@OneToMany(mappedBy="messageType")
	private List<Message> messages;

	public MessageType() {}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataClass() {
		return this.dataClass;
	}

	public void setDataClass(String dataClass) {
		this.dataClass = dataClass;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getRetries() {
		return this.retries;
	}

	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	public String getSychronised() {
		return this.sychronised;
	}

	public void setSychronised(String sychronised) {
		this.sychronised = sychronised;
	}

	public Integer getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setMessageType(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setMessageType(null);

		return message;
	}

	@Override
	public Object getPK() {
		return getId();
	}

	@Override
	public void setPK(Object Id) {
		setId((Integer)Id);
	}

	@Override
	public String toString() {
		return name;
	}

}