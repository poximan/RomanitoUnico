package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** @author ehidalgo */
@Entity
@Table(name = "ad_user_roles")
public class AdUserRoles extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected AdUserRolesPK adUserRolesPK;

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
	@Column(name = "updated", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@Basic(optional = false)
	@Column(name = "updatedby", nullable = false)
	private int updatedby;

	@JoinColumn(name = "ad_user_id", referencedColumnName = "ad_user_id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private AdUser adUser;

	@JoinColumn(name = "ad_role_id", referencedColumnName = "ad_role_id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private AdRole adRole;

	public AdUserRoles() {
	}

	public AdUserRolesPK getAdUserRolesPK() {
		return adUserRolesPK;
	}

	public void setAdUserRolesPK(AdUserRolesPK adUserRolesPK) {
		this.adUserRolesPK = adUserRolesPK;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public AdUser getAdUser() {
		return adUser;
	}

	public void setAdUser(AdUser adUser) {
		this.adUser = adUser;
	}

	public AdRole getAdRole() {
		return adRole;
	}

	public void setAdRole(AdRole adRole) {
		this.adRole = adRole;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (adUserRolesPK != null ? adUserRolesPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdUserRoles)) {
			return false;
		}
		AdUserRoles other = (AdUserRoles) object;
		if ((this.adUserRolesPK == null && other.adUserRolesPK != null)
				|| (this.adUserRolesPK != null && !this.adUserRolesPK.equals(other.adUserRolesPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.AdUserRoles[ adUserRolesPK=" + adUserRolesPK + " ]";
	}

	@Override
	public Object getPK() {
		return adUserRolesPK;
	}

	@Override
	public void setPK(Object Id) {
		this.adUserRolesPK = (AdUserRolesPK) Id;
	}

}