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
@Table(name = "ad_user")
public class AdUser extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ad_user_id", nullable = false)
	private Integer adUserId;

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

	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 60)
	private String name;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "password", length = 40)
	private String password;

	@Column(name = "email", length = 60)
	private String email;

	@Column(name = "phone", length = 40)
	private String phone;

	@Column(name = "phone2", length = 40)
	private String phone2;

	@Column(name = "phone3", length = 40)
	private String phone3;

	@Column(name = "birthday")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	@Basic(optional = false)
	@Column(name = "issystemaccess", nullable = false)
	private Character issystemaccess;

	@OneToMany(mappedBy = "supervisorId")
	private List<AdRole> adRoleList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adUser")
	private List<AdUserRoles> adUserRolesList;

	@OneToMany(mappedBy = "supervisorId")
	private List<AdUser> adUserList;

	@JoinColumn(name = "supervisor_id", referencedColumnName = "ad_user_id")
	@ManyToOne
	private AdUser supervisorId;

	public AdUser() {
	}

	public AdUser(Integer adUserId) {
		this.adUserId = adUserId;
	}

	public AdUser(Integer adUserId, Character isactive, Date created, int createdby, Date updated, int updatedby,
			String name, Character isldapauthorized, Character notificationtype, Character issystemaccess) {
		this.adUserId = adUserId;
		this.isactive = isactive;
		this.created = created;
		this.createdby = createdby;
		this.updated = updated;
		this.updatedby = updatedby;
		this.name = name;
		this.issystemaccess = issystemaccess;
	}

	public Integer getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(Integer adUserId) {
		this.adUserId = adUserId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Character getIssystemaccess() {
		return issystemaccess;
	}

	public void setIssystemaccess(Character issystemaccess) {
		this.issystemaccess = issystemaccess;
	}

	public List<AdRole> getAdRoleList() {
		return adRoleList;
	}

	public void setAdRoleList(List<AdRole> adRoleList) {
		this.adRoleList = adRoleList;
	}

	public List<AdUserRoles> getAdUserRolesList() {
		return adUserRolesList;
	}

	public void setAdUserRolesList(List<AdUserRoles> adUserRolesList) {
		this.adUserRolesList = adUserRolesList;
	}

	public List<AdUser> getAdUserList() {
		return adUserList;
	}

	public void setAdUserList(List<AdUser> adUserList) {
		this.adUserList = adUserList;
	}

	public AdUser getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(AdUser supervisorId) {
		this.supervisorId = supervisorId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (adUserId != null ? adUserId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AdUser)) {
			return false;
		}
		AdUser other = (AdUser) object;
		if ((this.adUserId == null && other.adUserId != null)
				|| (this.adUserId != null && !this.adUserId.equals(other.adUserId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.AdUser[ adUserId=" + adUserId + " Name=" + name + "]";
	}

	@Override
	public Object getPK() {
		return adUserId;
	}

	@Override
	public void setPK(Object Id) {
		this.adUserId = (Integer) Id;
	}

}