package br.com.cnmp.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cnmp.cursomc.domain.Cliente;
import br.com.cnmp.cursomc.repositories.ClienteRepository;
import br.com.cnmp.cursomc.services.exeception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado. Id:"+id+" Tipo:"+Cliente.class.getName()));
	}
}
