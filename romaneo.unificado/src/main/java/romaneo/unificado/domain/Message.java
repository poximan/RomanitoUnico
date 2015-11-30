/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "messages")
public class Message extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(nullable = false)
	private String asunto;

	@Column(nullable = false)
	private String mensaje;


	@Column(nullable = false)
	private transient TipoMensaje tipo_mensaje;

	@Column(name = "FECHA_CREADO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha_creado;

	@Column(name = "FECHA_RECIBIDO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha_recibido_ack;

	@Column(name = "FECHA_LEIDO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha_leido_ack;


	@OneToOne
	@JoinColumn(name = "ID_USUARIO")
	private transient Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "ID_ESTADO", referencedColumnName = "id")
	private transient Estado estado;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Message() {
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	public enum Filters {

		BY_DESDE("desde"), BY_HASTA("hasta"), BY_DESTINATADIO("destinatario"), BY_ASUNTO("asunto"), BY_ESTADO("estado");

		private String value;

		public String getValue() {
			return value;
		}

		Filters(String value) {
			this.value = value;
		}
	}

	public enum TipoMensaje {

		NORMAL("normal"), URGENTE("urgente");

		private String value;

		public String getValue() {
			return value;
		}

		TipoMensaje(String value) {
			this.value = value;
		}
	}

	@Override
	public String toString() {

		return asunto;
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	@Override
	public Object getPK() {
		return id;
	}

	public Integer getId() {
		return id;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public TipoMensaje getTipo_mensaje() {
		return tipo_mensaje;
	}

	public Calendar getFecha_creado() {
		return fecha_creado;
	}

	public Calendar getFecha_recibido_ack() {
		return fecha_recibido_ack;
	}

	public Calendar getFecha_leido_ack() {
		return fecha_leido_ack;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Estado getEstado() {
		return estado;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setTipo_mensaje(TipoMensaje tipo_mensaje) {
		this.tipo_mensaje = tipo_mensaje;
	}

	public void setFecha_creado(Calendar fecha_creado) {
		this.fecha_creado = fecha_creado;
	}

	public void setFecha_recibido_ack(Calendar fecha_recibido_ack) {
		this.fecha_recibido_ack = fecha_recibido_ack;
	}

	public void setFecha_leido_ack(Calendar fecha_leido_ack) {
		this.fecha_leido_ack = fecha_leido_ack;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void setPK(Object Id) {
		id = (Integer) Id;
	}
}