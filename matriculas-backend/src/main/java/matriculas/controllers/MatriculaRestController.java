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

import matriculas.models.entity.Matricula;
import matriculas.models.entity.Programa;
import matriculas.models.entity.Usuario;
import matriculas.models.services.IMatriculaService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MatriculaRestController {
	
	@Autowired
	private IMatriculaService matriculaService;
	
	@GetMapping("/matriculas")
	public List<Matricula> index(){
		return matriculaService.findAll();
	}
	
	@GetMapping("/matriculas/{id}")
	public Matricula show(@PathVariable Long id){
		return matriculaService.findById(id);
	}

	@PostMapping("/matriculas")
	@ResponseStatus(HttpStatus.CREATED)
	public Matricula create(@RequestBody Matricula matricula){			
		return matriculaService.save(matricula);		
	}
	
	//Actualizar matricula
	@PutMapping("/matriculas/{id}")
	public Matricula update(@RequestBody Matricula matricula, @PathVariable Long id){
		
		Matricula current = matriculaService.findById(id);
		
		current.setId(matricula.getId());
		current.setUsuario(matricula.getUsuario());
		current.setPrograma(matricula.getPrograma());
		current.setFechaMatricula(matricula.getFechaMatricula());
		current.setValor(matricula.getValor());
		current.setEstado(matricula.getEstado());

		return matriculaService.save(current);
	}
	
	//buscar matricula por id
		@PutMapping("/matricula/buscar/{id}")
		public Matricula search(@RequestBody Matricula matricula, @PathVariable Long id){
			
			Matricula current = matriculaService.findById(id);
			if(current!=null) {
				return current;
			}else {
				return null;
			}
			
			
		}
	
	/* 
	//Eliminar Matricula
	@DeleteMapping("/matriculas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		matriculaService.delete(id);
	}
	*/
}
