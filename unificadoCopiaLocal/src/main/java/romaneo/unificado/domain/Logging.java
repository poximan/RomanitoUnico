package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** @author Juan Manuel Cortez */
@Entity
@Table(name = "logging")
public class Logging extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "type_object")
	private String typeObject;

	@Basic(optional = false)
	@Column(name = "name_object")
	private String nameObject;

	@Basic(optional = false)
	@Column(name = "id_object")
	private int idObject;

	@Basic(optional = false)
	@Column(name = "state_from")
	private String stateFrom;

	@Basic(optional = true)
	@Column(name = "event")
	private String event;

	@Basic(optional = false)
	@Column(name = "state_to")
	private String stateTo;

	@Basic(optional = true)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "log_datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)    
	private Date logDateTime;

	@Lob
	@Basic(optional = true)
	@Column(name = "observations")
	private String observations;

	public Logging() {
		this.logDateTime = new Date();
	}

	public Logging(Integer id) {
		this.id = id;
		this.logDateTime = new Date();
	}

	public Logging(Integer id, String typeObject, String nameObject, int idObject, String stateFrom, String stateTo, String action, Integer userId, Integer messageId) {
		this.id = id;
		this.typeObject = typeObject;
		this.nameObject = nameObject;
		this.idObject = idObject;
		this.stateFrom = stateFrom;
		this.stateTo = stateTo;
		this.userId = userId;
		this.logDateTime = new Date();
	}

	public Logging(Integer id, String typeObject, String nameObject, int idObject, String stateFrom, String event, String stateTo, String action, Integer userId, Integer messageId) {
		this.id = id;
		this.typeObject = typeObject;
		this.nameObject = nameObject;
		this.idObject = idObject;
		this.stateFrom = stateFrom;
		this.event = event;
		this.stateTo = stateTo;
		this.userId = userId;
		this.logDateTime = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeObject() {
		return typeObject;
	}

	public void setTypeObject(String typeObject) {
		this.typeObject = typeObject;
	}

	public String getNameObject() {
		return nameObject;
	}

	public void setNameObject(String nameObject) {
		this.nameObject = nameObject;
	}

	public int getIdObject() {
		return idObject;
	}

	public void setIdObject(int idObject) {
		this.idObject = idObject;
	}

	public String getStateFrom() {
		return stateFrom;
	}

	public void setStateFrom(String stateFrom) {
		this.stateFrom = stateFrom;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getStateTo() {
		return stateTo;
	}

	public void setStateTo(String stateTo) {
		this.stateTo = stateTo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Logging)) {
			return false;
		}
		Logging other = (Logging) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "mel.Logging[ id=" + id + " ]";
	}

	@Override
	public Object getPK() {
		return getId();
	}

	@Override
	public void setPK(Object Id) {
		setId(id);
	}

	public enum TypeObject {

		TABLE("table");

		private String value;

		public String getValue() {
			return value;
		}

		TypeObject(String value) {
			this.value = value;
		}

	}

	public enum NameObject {

		DOCUMENT("document"),
		TRAVEL("travel");

		private String value;

		public String getValue() {
			return value;
		}

		NameObject(String value) {
			this.value = value;
		}

	}

}