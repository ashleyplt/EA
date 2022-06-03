package vistas;

public class VW_Archivos {

	

int id_expediente;
String Nombre_asignatura;
int id_archivo;
String  Archivo;
String Url;
public int getId_expediente() {
	return id_expediente;
}
public void setId_expediente(int id_expediente) {
	this.id_expediente = id_expediente;
}
public String getNombre_asignatura() {
	return Nombre_asignatura;
}
public void setNombre_asignatura(String nombre_asignatura) {
	Nombre_asignatura = nombre_asignatura;
}
public int getId_archivo() {
	return id_archivo;
}
public void setId_archivo(int id_archivo) {
	this.id_archivo = id_archivo;
}
public String getArchivo() {
	return Archivo;
}
public void setArchivo(String archivo) {
	Archivo = archivo;
}
public String getUrl() {
	return Url;
}
public void setUrl(String url) {
	Url = url;
}



}
