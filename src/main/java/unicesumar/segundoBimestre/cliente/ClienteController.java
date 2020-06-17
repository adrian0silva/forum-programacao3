package unicesumar.segundoBimestre.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<Cliente> getAll() {
		return service.getAll();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public Cliente getById(@PathVariable("id") String id) {
		return service.getById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public String post(@RequestBody Cliente cliente) {
		return service.save(cliente);
	}
	
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		service.deleteById(id);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	public void put(@PathVariable("id") String id,@RequestBody Cliente modificado) {
		if(!id.equals(modificado.getId())) {
			throw new RuntimeException("Id do recurso não confere com Id do Body");
		}
		
		service.save(modificado);
	}
}
