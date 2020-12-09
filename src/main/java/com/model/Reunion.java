package com.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reuniones")
public class Reunion {
	

	private String temas;
	private String descripcion;
	private String horaInicio;
	private String horaFin;
	private String[] asistentes;
	private String convocante;
	private String url;
	
	public Reunion(String temas, String descripcion, String horaInicio, String horaFin,
			String[] asistentes, String convocante, String url) {
		super();
		this.temas = temas;
		this.descripcion = descripcion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.asistentes = asistentes;
		this.convocante = convocante;
		this.url = url;
	}

	public String getTemas() {
		return temas;
	}

	public void setTemas(String temas) {
		this.temas = temas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String[] getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(String[] asistentes) {
		this.asistentes = asistentes;
	}

	public String getConvocante() {
		return convocante;
	}

	public void setConvocante(String convocante) {
		this.convocante = convocante;
	}
	
	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}
	
}
