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

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "TIPO_MENSAJE", nullable = true)
	private TipoMensaje tipoMensaje;

	@Column(name = "FECHA_CREADO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaCreado;

	@Column(name = "FECHA_ENVIADO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaEnviado;

	@Column(name = "FECHA_RECIBIDO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaRecibidoAck;

	@Column(name = "FECHA_LEIDO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaLeidoAck;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_ESTADO", referencedColumnName = "id")
	private Estado estado;

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

	public TipoMensaje getTipoMensaje() {
		return tipoMensaje;
	}

	public Calendar getFechaCreado() {
		return fechaCreado;
	}

	public Calendar getFechaRecibidoAck() {
		return fechaRecibidoAck;
	}

	public Calendar getFechaLeidoAck() {
		return fechaLeidoAck;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Estado getEstado() {
		return estado;
	}

	public Calendar getFechaEnviado() {
		return fechaEnviado;
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

	public void setTipoMensaje(TipoMensaje tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public void setFechaCreado(Calendar fechaCreado) {
		this.fechaCreado = fechaCreado;
	}

	public void setFechaRecibidoAck(Calendar fechaRecibidoAck) {
		this.fechaRecibidoAck = fechaRecibidoAck;
	}

	public void setFechaLeidoAck(Calendar fechaLeidoAck) {
		this.fechaLeidoAck = fechaLeidoAck;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setFechaEnviado(Calendar fechaEnviado) {
		this.fechaEnviado = fechaEnviado;
	}

	@Override
	public void setPK(Object Id) {
		id = (Integer) Id;
	}
}