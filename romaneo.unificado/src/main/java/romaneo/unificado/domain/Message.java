package romaneo.unificado.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(nullable = false)
	private Timestamp created;

	@Column(length = 60)
	private String data;

	private Timestamp processed;

	@ManyToOne
	@JoinColumn(name = "cod_message", nullable = false)
	private MessageType messageType;

	public Message() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Timestamp getProcessed() {
		return this.processed;
	}

	public void setProcessed(Timestamp processed) {
		this.processed = processed;
	}

	public MessageType getMessageType() {
		return this.messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@Override
	public Object getPK() {
		return getId();
	}

	@Override
	public void setPK(Object Id) {
		setId((Integer) Id);
	}

	public enum Filters {

		BY_FROM_DATE("fromDate"), BY_TO_DATE("toDate"), BY_MESSAGE_TYPE("messageTyper"), BY_UNPROCESSED("unprocessed");

		private String value;

		public String getValue() {
			return value;
		}

		Filters(String value) {
			this.value = value;
		}

	}

}