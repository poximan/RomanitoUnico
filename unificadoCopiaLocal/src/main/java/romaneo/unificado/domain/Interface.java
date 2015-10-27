package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/** @author nacho */
@SuppressWarnings("rawtypes")
@Entity
@Table(name = "interfaces")
public class Interface extends BaseEntity implements Serializable, Comparable {

	private static final long serialVersionUID = -5900977172978152670L;

	public static final String SHEETS_TYPE = "sheets";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "filename")
	private String filename;

	@Basic(optional = false)
	@Column(name = "upload_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date uploadDate;

	@Column(name = "process_start_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processStartDate;

	@Column(name = "process_error_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processErrorDate;

	@Column(name = "process_stop_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processStopDate;

	@Column(name = "type")
	private String type;

	@Column(name = "autoupload")
	private Boolean autoupload;

	@Column(name = "html", nullable = true)
	private String html;

	public Interface() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date upload_date) {
		this.uploadDate = upload_date;
	}

	public Date getProcessStartDate() {
		return processStartDate;
	}

	public void setProcessStartDate(Date process_date) {
		this.processStartDate = process_date;
	}

	public void setProcessErrorDate(Date processErrorDate) {
		this.processErrorDate = processErrorDate;
	}

	public Date getProcessErrorDate() {
		return processErrorDate;
	}

	public Date getProcessStopDate() {
		return processStopDate;
	}

	public void setProcessStopDate(Date processStopDate) {
		this.processStopDate = processStopDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getAutoupload() {
		return autoupload;
	}

	public void setAutoupload(Boolean autoupload) {
		this.autoupload = autoupload;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Interface)) {
			return false;
		}
		Interface other = (Interface) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getFilename();
	}

	@Override
	public Object getPK() {
		return this.id;
	}

	@Override
	public void setPK(Object Id) {
		this.id = (Integer) Id;

	}

	@Override
	public int compareTo(Object o) {
		Interface interfaz = (Interface) o;
		return interfaz.getFilename().compareTo(this.filename);
	}

	public enum Filters {
		BY_DISTRIBUTION_CENTER("distributionCenter"), BY_TYPE("type"), BY_FILENAME("filename");

		private String value;

		public String getValue() {
			return value;
		}

		Filters(String value) {
			this.value = value;
		}
	}

}
