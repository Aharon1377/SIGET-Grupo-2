package com.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuario")
public class Usuario {
	
	
	private String username;
	private byte salt[];
	private byte[] hash;
	private String roleID;
	private String nombre;
	private String apellidos;
	private String email;
	private int telefono;
	
	
	public Usuario(String username, byte[] salt, byte[] hash, String roleID, String nombre, String apellidos,
			String email, int telefono) {
		super();
		this.username = username;
		this.salt = salt;
		this.hash = hash;
		this.roleID = roleID;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public byte[] getSalt() {
		return salt;
	}


	public void setSalt(byte[] salt) {
		this.salt = salt;
	}


	public byte[] getHash() {
		return hash;
	}


	public void setHash(byte[] hash) {
		this.hash = hash;
	}


	public String getRoleID() {
		return roleID;
	}


	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
}
