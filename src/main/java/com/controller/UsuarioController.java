
package com.controller;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Usuario;
import com.persistence.UsuarioRepository;


import io.cucumber.messages.internal.com.google.common.io.Files;

@CrossOrigin()
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("login")
	public Usuario login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) throws GeneralSecurityException {

		Usuario user = this.usuarioRepository.findOneByUsername(username).get();
		Usuario aux = null;
		String pwdEncrypted = encriptarMD5(password);
		if (pwdEncrypted.equals(user.getPassword())) {
			return user;
		} else {
			return aux;
		}

	}

	@GetMapping("getAll")
	public List<Usuario> getAll() {

		return this.usuarioRepository.findAll();
	}
	@GetMapping("getID")
	public Optional<Usuario> getID(@RequestParam(name = "username") String username) {
		Optional<Usuario> pepe = this.usuarioRepository.findOneByUsername(username);
		return pepe;
	}

	@PostMapping("createUsuario")
	public Usuario createUsuario(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "roleID") String roleID, 
			@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "apellidos") String apellidos, @RequestParam(name = "email") String email,
			@RequestParam(name = "telefono") int telefono) throws GeneralSecurityException {

		return this.usuarioRepository
				.insert(new Usuario(username, encriptarMD5(password), roleID , nombre, apellidos, email, telefono));
	}

	/*
	 * @PostMapping("createAdmin") public Usuario createAdmin(@RequestParam(name =
	 * "username") String username,
	 * 
	 * @RequestParam(name = "password") String password) { return
	 * this.usuarioRepository.insert(new Usuario(username, password,"1")); }
	 */

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
