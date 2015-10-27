package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/** @author ehidalgo */
@Embeddable
public class AdUserRolesPK implements Serializable {

	private static final long serialVersionUID = -5993440814497341273L;

	@Basic(optional = false)
	@Column(name = "ad_user_id", nullable = false)
	private int adUserId;

	@Basic(optional = false)
	@Column(name = "ad_role_id", nullable = false)
	private int adRoleId;

	public AdUserRolesPK() {
	}

	public AdUserRolesPK(int adUserId, int adRoleId) {
		this.adUserId = adUserId;
		this.adRoleId = adRoleId;
	}

	public int getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(int adUserId) {
		this.adUserId = adUserId;
	}

	public int getAdRoleId() {
		return adRoleId;
	}

	public void setAdRoleId(int adRoleId) {
		this.adRoleId = adRoleId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) adUserId;
		hash += (int) adRoleId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdUserRolesPK)) {
			return false;
		}
		AdUserRolesPK other = (AdUserRolesPK) object;
		if (this.adUserId != other.adUserId) {
			return false;
		}
		if (this.adRoleId != other.adRoleId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.AdUserRolesPK[ adUserId=" + adUserId + ", adRoleId=" + adRoleId + " ]";
	}

}
