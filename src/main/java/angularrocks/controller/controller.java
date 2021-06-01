package angularrocks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import angularrocks.model.Grupo;
import angularrocks.repository.GrupoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class controller {
	
	@Autowired
	GrupoRepository grupoRepository;

	@GetMapping("/grupos")
	public ResponseEntity<List<Grupo>> getAllGrupos(@RequestParam(required = false) String name) {
		try {
			List<Grupo> grupos = new ArrayList<Grupo>();

			if (name == null)
				grupoRepository.findAll().forEach(grupos::add);
			else
				grupoRepository.findByNameContaining(name).forEach(grupos::add);

			if (grupos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(grupos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/grupos/{id}")
	public ResponseEntity<Grupo> getGrupoById(@PathVariable("id") long id) {
		Optional<Grupo> grupoData = grupoRepository.findById(id);

		if (grupoData.isPresent()) {
			return new ResponseEntity<>(grupoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/grupos")
	public ResponseEntity<Grupo> createGrupo(@RequestBody Grupo grupo) {
		try {
			Grupo _grupo = grupoRepository
					.save(new Grupo(grupo.getName(), grupo.getDescripcion(), grupo.getVideo(), false));
			return new ResponseEntity<>(_grupo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/grupos/{id}")
	public ResponseEntity<Grupo> updateGrupo(@PathVariable("id") long id, @RequestBody Grupo grupo) {
		Optional<Grupo> grupoData = grupoRepository.findById(id);

		if (grupoData.isPresent()) {
			Grupo _grupo = grupoData.get();
			_grupo.setName(grupo.getName());
			_grupo.setDescripcion(grupo.getDescripcion());
			_grupo.setPublished(grupo.isPublished());
			return new ResponseEntity<>(grupoRepository.save(_grupo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/grupos/{id}")
	public ResponseEntity<HttpStatus> deleteGrupo(@PathVariable("id") long id) {
		try {
			grupoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/grupos")
	public ResponseEntity<HttpStatus> deleteAllGrupos() {
		try {
			grupoRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/grupos/published")
	public ResponseEntity<List<Grupo>> findByPublished() {
		try {
			List<Grupo> grupos = grupoRepository.findByPublished(true);

			if (grupos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(grupos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
