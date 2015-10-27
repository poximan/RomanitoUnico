package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** @author ehidalgo */
@Entity
@Table(name = "ad_role")
public class AdRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ad_role_id", nullable = false)
	private Integer adRoleId;

	@Basic(optional = false)
	@Column(name = "isactive", nullable = false)
	private Character isactive;

	@Basic(optional = false)
	@Column(name = "created", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Basic(optional = false)
	@Column(name = "createdby", nullable = false)
	private int createdby;

	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 60)
	private String name;

	@Column(name = "description", length = 255)
	private String description;
	@Basic(optional = false)

	@Column(name = "userlevel", nullable = false, length = 10)
	private String userlevel;

	@JoinColumn(name = "supervisor_id", referencedColumnName = "ad_user_id")
	@ManyToOne
	private AdUser supervisorId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adRole")
	private List<AdUserRoles> adUserRolesList;

	public AdRole() {
	}

	public AdRole(Integer adRoleId) {
		this.adRoleId = adRoleId;
	}

	public Integer getAdRoleId() {
		return adRoleId;
	}

	public void setAdRoleId(Integer adRoleId) {
		this.adRoleId = adRoleId;
	}

	public Character getIsactive() {
		return isactive;
	}

	public void setIsactive(Character isactive) {
		this.isactive = isactive;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	public AdUser getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(AdUser supervisorId) {
		this.supervisorId = supervisorId;
	}

	public List<AdUserRoles> getAdUserRolesList() {
		return adUserRolesList;
	}

	public void setAdUserRolesList(List<AdUserRoles> adUserRolesList) {
		this.adUserRolesList = adUserRolesList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (adRoleId != null ? adRoleId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdRole)) {
			return false;
		}
		AdRole other = (AdRole) object;
		if ((this.adRoleId == null && other.adRoleId != null)
				|| (this.adRoleId != null && !this.adRoleId.equals(other.adRoleId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.AdRole[ adRoleId=" + adRoleId + " ]";
	}

	@Override
	public Object getPK() {
		return adRoleId;
	}

	@Override
	public void setPK(Object Id) {
		this.adRoleId = (Integer) Id;
	}
}