package com.example.demo;

import static org.junit.Assert.fail;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.Reunion;
import com.persistence.ReunionRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasosEliminarReunion {
	@Autowired
	private ReunionRepository reunionRepository;
	private Reunion reunion;
	private Reunion reunionAlmacenada;
	private java.util.Optional<Reunion> resultado;
	
	@Given("existe una reunion con {string}, {string}, {string}, {string}, {string}, {string} y {string}")
	public void existe_una_reunion_con_y(String tema, String descripcion, String horaInicio, String horaFin, String asistente, String convocante, String url) {
		String [] asistentes = new String[1];
		asistentes[0] = asistente;
		this.reunion = new Reunion(tema,descripcion,horaInicio,horaFin,asistentes,convocante,url);
		this.reunionAlmacenada = this.reunionRepository.insert(this.reunion);
	}
	@When("se elimina de la base de datos.")
	public void se_elimina_de_la_base_de_datos() {
		this.reunionRepository.deleteByConvocante(this.reunionAlmacenada.getConvocante());
		this.resultado = this.reunionRepository.findOneByConvocante(this.reunionAlmacenada.getConvocante());
	}
	@Then("al intentar recuperarla no existe.")
	public void al_intentar_recuperarla_no_existe() {
	    if(!resultado.toString().equals("Optional.empty")) {
	    	fail("La reunion no se ha borrado con exito");
	    }
	}
	
}
