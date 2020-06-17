package unicesumar.segundoBimestre.cliente;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public void deleteById(String id) {
		repo.deleteById(id);
	}

	public Cliente getById(String id) {
		return repo.findById(id).orElseThrow(NotFoundException::new);
	}

	public List<Cliente> getAll() {
		return repo.findAll();
	}

	public String save(Cliente cliente) {
		return this.repo.save(cliente).getId();
	}

	public Cliente put(Cliente cliente) {
		return repo.save(cliente);
	}
}