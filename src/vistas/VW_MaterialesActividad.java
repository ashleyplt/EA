package vistas;

public class VW_MaterialesActividad {
	
	//Declaraci�n de variables
	private int id_actividad;
	private String actividad;
	private String material;
	private String detalle_material;
	private String enlace;
	
	//Gethers and Setters
	public int getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDetalle_material() {
		return detalle_material;
	}
	public void setDetalle_material(String detalle_material) {
		this.detalle_material = detalle_material;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
}