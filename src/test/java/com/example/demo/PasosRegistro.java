package com.example.demo;

import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Optional;

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
	String username;
	Optional<Usuario> user;

	@Given("se registra al usuario con {string}, {string} {string}, {string}, {string}, {string} y {string}")
	public void se_registra_al_usuario_con_y(String username, String password, String roleID, String nombre,
			String apellidos, String email, String telefono) {

		this.usuario = new Usuario(username, encriptarMD5(password), roleID, nombre, apellidos, email,
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
			fail("El usuario introducido y recuperado no coinciden");
		}
	}

	@Given("se elimina al usuario con {string}")
	public void se_elimina_al_usuario_con(String username) {
		this.usuarioRepository.deleteByUsername(username);
		this.username = username;
	}

	@When("se busca el usuario registrado de la base de datos")
	public void se_busca_el_usuario_registrado_de_la_base_de_datos() {
		user = this.usuarioRepository.findOneByUsername(this.username);
	}

	@Then("el usuario ya no existe")
	public void el_usuario_ya_no_existe() {
		try {
			this.user.get();
			fail("El usuario no ha sido eliminado");
		} catch (NoSuchElementException e) {
		}
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
