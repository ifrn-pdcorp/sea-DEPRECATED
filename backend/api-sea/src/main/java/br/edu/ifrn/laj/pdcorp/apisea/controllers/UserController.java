package br.edu.ifrn.laj.pdcorp.apisea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.laj.pdcorp.apisea.dtos.SessionDTO;
import br.edu.ifrn.laj.pdcorp.apisea.dtos.UserDTO;
import br.edu.ifrn.laj.pdcorp.apisea.exceptions.ApiException;
import br.edu.ifrn.laj.pdcorp.apisea.models.User;
import br.edu.ifrn.laj.pdcorp.apisea.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User Endpoint", description = "Control of users", tags = "User Endpoint")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Adicionar ou atualizar usuário")
	@PostMapping
	public ResponseEntity<?> saveOrEditUser(@RequestBody User user) {
		try {
			return new ResponseEntity<UserDTO>(this.userService.save(user), HttpStatus.OK);
		} catch (ApiException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@ApiOperation(value = "Carrega os dados do usuário que está logado na sessão")
	@GetMapping("/loadsession")
	public ResponseEntity<?> loadSession(Authentication principal) {
		try {
			SessionDTO sessionDTO = new SessionDTO();
			sessionDTO.setUser(this.userService.findByUsername(principal.getName()));
			sessionDTO.setToken(principal.getCredentials().toString());

			return new ResponseEntity<SessionDTO>(sessionDTO, HttpStatus.OK);
		} catch (ApiException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Visualizar usuário por email")
	@GetMapping("/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
		try {
			return new ResponseEntity<UserDTO>(this.userService.findByUsername(email), HttpStatus.OK);
		} catch (ApiException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
