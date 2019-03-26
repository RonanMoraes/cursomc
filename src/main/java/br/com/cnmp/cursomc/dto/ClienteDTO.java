package br.com.cnmp.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cnmp.cursomc.domain.Cliente;

public class ClienteDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="Deve ter no mínimo 5 no máximo 120 caracteres")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="E-mail não passou na validação")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente cli) {
		super();
		this.id = cli.getId();
		this.nome = cli.getNome();
		this.email = cli.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
