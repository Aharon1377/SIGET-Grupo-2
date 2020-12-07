package com.example.demo;

import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.Usuario;
import com.persistence.UsuarioRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasosRegistro {

	@Autowired
	private UsuarioRepository usuarioRepository;
	private Usuario usuario;
	private Usuario usuarioAlmacenado;

	@Given("se registra al usuario con {string}, {string} {string}, {string}, {string}, {string} y {string}")
	public void se_registra_al_usuario_con_y(String username, String password, String roleID, String nombre,
			String apellidos, String email, String telefono) {

		this.usuario = new Usuario(username, encriptarMD5(password), "3", nombre, apellidos, email,
				Integer.parseInt(telefono));
	}

	@When("se recupera el usuario registrado de la base de datos")
	public void se_recupera_el_usuario_registrado_de_la_base_de_datos() {
		this.usuarioAlmacenado = this.usuarioRepository.insert(this.usuario);
	}

	@Then("los datos introducidos y los recuperados del registro coinciden")
	public void los_datos_introducidos_y_los_recuperados_del_registro_coinciden() {
		if (!(this.usuario.getUsername().equals(this.usuarioAlmacenado.getUsername())
				&& this.usuario.getPassword().equals(this.usuarioAlmacenado.getPassword())

				&& this.usuario.getNombre().equals(this.usuarioAlmacenado.getNombre())
				&& this.usuario.getApellidos().equals(this.usuarioAlmacenado.getApellidos())
				&& this.usuario.getEmail().equals(this.usuarioAlmacenado.getEmail())
				&& this.usuario.getTelefono() == this.usuarioAlmacenado.getTelefono())) {
			this.usuarioRepository.deleteByUsername(this.usuarioAlmacenado.getUsername());
			fail("El usuario introducido y recuperado no coinciden");
		}
		this.usuarioRepository.deleteByUsername(this.usuarioAlmacenado.getUsername());
	}

	private static String encriptarMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			int diff = 32 - hashtext.length();
			StringBuilder bld = new StringBuilder();

			while (diff > 1) {
				bld.append("0");
				diff--;
			}

			return bld.toString() + hashtext;

		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
