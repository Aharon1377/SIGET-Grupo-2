package com.example.demo;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PasosLogin extends SigetGrupo2ApplicationTest {
	private OkHttpClient client;
	private Request request;

	@Given("el usuario intenta logearse")
	public void el_usuario_intenta_logearse() {
		System.out.println("Usuario intentado logearse...");
	}

	@When("el cliente hace la llamada POST \\/login con los parámetros username {string} y password {string}")
	public void el_cliente_hace_la_llamada_post_login_con_los_parámetros_username_y_password(String username,
			String password) throws IOException {

		client = new OkHttpClient();
		client.setConnectTimeout(60, TimeUnit.SECONDS);
		client.setReadTimeout(60, TimeUnit.SECONDS);
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");

		request = new Request.Builder()
				.url("https://siget-equipo2.herokuapp.com/api/usuarios/login?username=" + username + "&password="
						+ password)
				.post(body).addHeader("Content-Type", "application/json")
				.addHeader("User-Agent", "PostmanRuntime/7.19.0").addHeader("Accept", "*/*")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Postman-Token", "026c8d66-5ccb-453f-b1b4-c6f351f126ee,ca3db196-6148-4d81-a889-94d79002afe4")
				.addHeader("Accept-Encoding", "gzip, deflate").addHeader("Content-Length", "84")
				.addHeader("Connection", "keep-alive").addHeader("cache-control", "no-cache").build();
	}

	@Then("el cliente recibe la respuesta de que el login es {string}")
	public void el_cliente_recibe_la_respuesta_de_que_el_login_es_correcto(String correcto)
			throws IOException, JSONException {
		try {
			Response response = client.newCall(request).execute();
			if (correcto.equals("True")) {
				if (!response.isSuccessful()) {
					fail("La respuesta da error pero debería ser afirmativa");
				}
			} else {
				if (response.isSuccessful()) {
					fail("La respuesta es afirmativa pero debería dar error");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error en la llamada http");
		}
	}
}
