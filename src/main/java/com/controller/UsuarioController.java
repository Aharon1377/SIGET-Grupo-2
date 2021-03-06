
package com.controller;

import java.math.BigInteger;
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

import exceptions.AccessNotGrantedException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("login")
	public Usuario login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) throws AccessNotGrantedException {
		String pwdEncrypted = encriptarMD5(password);

		Optional<Usuario> value = this.usuarioRepository.findOneByUsername(username);
		if (value.isPresent()) {
			Usuario user = value.get();
			if (pwdEncrypted.equals(user.getPassword())) {
				return user;
			} else {
				throw new AccessNotGrantedException();
			}
		} else {
			throw new AccessNotGrantedException();
		}

	}

	@GetMapping("getAll")
	public List<Usuario> getAll() {

		return this.usuarioRepository.findAll();
	}

	@GetMapping("getID")
	public Optional<Usuario> getID(@RequestParam(name = "username") String username) {
		return this.usuarioRepository.findOneByUsername(username);
	}

	@PostMapping("createUsuario")
	public Usuario createUsuario(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "roleID") String roleID,
			@RequestParam(name = "nombre") String nombre, @RequestParam(name = "apellidos") String apellidos,
			@RequestParam(name = "email") String email, @RequestParam(name = "telefono") int telefono){

		return this.usuarioRepository
				.insert(new Usuario(username, encriptarMD5(password), roleID, nombre, apellidos, email, telefono));
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
