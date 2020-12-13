package com.example.demo;

import static org.junit.Assert.fail;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.Reunion;
import com.persistence.ReunionRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasosCrearReunion {
	@Autowired
	private ReunionRepository reunionRepository;
	private Reunion reunion;
	private Reunion reunionAlmacenada;

	@Given("se crea la reunion con {string}, {string}, {string}, {string}, {string}, {string} y {string}")
	public void se_crea_la_reunion_con_y(String tema, String descripcion, String horaInicio, String horaFin,
			String asistente, String convocante, String url) {
		String[] asistentes = new String[1];
		asistentes[0] = asistente;
		this.reunion = new Reunion(tema, descripcion, horaInicio, horaFin, asistentes, convocante, url);

	}

	@When("se recupera la reunion de la base de datos")
	public void se_recupera_la_reunion_de_la_base_de_datos() {
		this.reunionAlmacenada = this.reunionRepository.insert(this.reunion);
	}

	@Then("los datos introducidos y los recuperados de la base de datos coinciden")
	public void los_datos_introducidos_y_los_recuperados_de_la_base_de_datos_coinciden() {
		if (!(this.reunion.getTemas().equals(this.reunionAlmacenada.getTemas())
				&& this.reunion.getHoraInicio().equals(this.reunionAlmacenada.getHoraInicio())
				&& this.reunion.getHoraFin().equals(this.reunionAlmacenada.getHoraFin())
				&& this.reunion.getHoraInicio().equals(this.reunionAlmacenada.getHoraInicio())
				&& this.reunion.getAsistentes().equals(this.reunionAlmacenada.getAsistentes())
				&& this.reunion.getConvocante().equals(this.reunionAlmacenada.getConvocante())
				&& this.reunion.getURL().equals(this.reunionAlmacenada.getURL()))) {
			this.reunionRepository.deleteByConvocante(this.reunionAlmacenada.getConvocante());
			fail("La reunion introducida y recuperada no concide");
		}
		this.reunionRepository.deleteByConvocante(this.reunionAlmacenada.getConvocante());
	}
}
