package entidades;
import java.sql.Date;

public class DetalleSyllabus {
	private int id_det_syllabus; 
	private int id_syllabus;
	private int num_agenda;
	private Date fecha_inicio;
	private Date fecha_final;
	private String objetivos_unidad;
	private String tema_subtema;
	private int estado;
	public int getId_det_syllabus() {
		return id_det_syllabus;
	}
	public void setId_det_syllabus(int id_det_syllabus) {
		this.id_det_syllabus = id_det_syllabus;
	}
	public int getId_syllabus() {
		return id_syllabus;
	}
	public void setId_syllabus(int id_syllabus) {
		this.id_syllabus = id_syllabus;
	}
	public int getNum_agenda() {
		return num_agenda;
	}
	public void setNum_agenda(int num_agenda) {
		this.num_agenda = num_agenda;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_final() {
		return fecha_final;
	}
	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}
	public String getObjetivos_unidad() {
		return objetivos_unidad;
	}
	public void setObjetivos_unidad(String objetivos_unidad) {
		this.objetivos_unidad = objetivos_unidad;
	}
	public String getTema_subtema() {
		return tema_subtema;
	}
	public void setTema_subtema(String tema_subtema) {
		this.tema_subtema = tema_subtema;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

}
