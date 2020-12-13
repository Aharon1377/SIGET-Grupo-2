package com.example.demo;

import static org.junit.Assert.fail;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import com.model.Usuario;
import com.persistence.UsuarioRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasosGetID {

	@Autowired
	private UsuarioRepository usuarioRepository;
	String nivel;
	Optional<Usuario> user;

	@Given("el usuario intenta logearse con {string} y comprobamos su nivel de acceso")
	public void el_usuario_intenta_logearse_con_y_comprobamos_su_nivel_de_acceso(String username) {
		user = this.usuarioRepository.findOneByUsername(username);
	}

	@When("el cliente hace la llamada GET \\/getID con los parámetros username {string}")
	public void el_cliente_hace_la_llamada_get_get_id_con_los_parámetros_username(String username) {
	}

	@Then("el cliente recibe el {string} de acceso almacenado del usuario")
	public void el_cliente_recibe_el_de_acceso_almacenado_del_usuario(String nivel) {
		if (!(nivel.equals(user.get().getRoleID()))) {
			fail("El nivel de acceso no coincide");
		}
	}

}
