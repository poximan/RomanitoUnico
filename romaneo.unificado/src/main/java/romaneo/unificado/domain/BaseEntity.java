package romaneo.unificado.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

/** @author Juan Manuel Cortez */
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Version
	@Column(name = "OBJ_VERSION")
	protected Timestamp version;

	public BaseEntity() {
		this(true);
	}

	public BaseEntity(Boolean setPK) {
		if (setPK) {
			setPK(null);
		}
	}

	public abstract Object getPK();

	public abstract void setPK(Object Id);

	public Timestamp getVersion() {
		return version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}

}