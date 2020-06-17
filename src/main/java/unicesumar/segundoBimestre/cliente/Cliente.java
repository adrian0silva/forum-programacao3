package unicesumar.segundoBimestre.cliente;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	private String id;
	
	private String nomeCompleto;
	
	private String senha;

	public Cliente() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Cliente(String id, String nomeCompleto, String senha) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
	}
	public String getId() {
		return id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public String getSenha() {
		return senha;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
