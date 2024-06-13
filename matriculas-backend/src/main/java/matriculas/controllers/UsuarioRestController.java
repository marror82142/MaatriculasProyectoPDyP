package matriculas.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import matriculas.models.entity.Usuario;
import matriculas.models.services.IUsuarioService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/login")
	public Usuario login(	@RequestParam String nombreUsuario,
							@RequestParam String contrasena){	
		Usuario usr = null;
		List<Usuario> usuarios = usuarioService.findAll();
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getNombreUsuario().equals(nombreUsuario)
					&& usuarios.get(i).getContrasena().equals(contrasena)) {
					usr = usuarios.get(i);
				}
		}
		if(usr == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
		}
		return usr;
	}
	
	@GetMapping("/usuarios/{cedula}")
	public Usuario show(@PathVariable Long cedula){
		return usuarioService.findById(cedula);
	}
	
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario){			
		return usuarioService.save(usuario);		
	}
		
	@PutMapping("/usuarios/{cedula}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long cedula){
		Usuario current = usuarioService.findById(cedula);
		
		current.setCedula(usuario.getCedula());
		current.setNombre(usuario.getNombre());
		current.setRol(usuario.getRol());
		current.setProfesion(usuario.getProfesion());
		current.setEmpresaTrabajo(usuario.getEmpresaTrabajo());
		current.setNombreUsuario(usuario.getNombreUsuario());
		current.setContrasena(usuario.getContrasena());
		current.setFechaNacimiento(usuario.getFechaNacimiento());
		
		return usuarioService.save(current);
	}
	
	@DeleteMapping("/usuarios/{cedula}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cedula){
		usuarioService.delete(cedula);
	}
}
