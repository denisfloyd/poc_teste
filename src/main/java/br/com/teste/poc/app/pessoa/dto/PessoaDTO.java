package br.com.teste.poc.app.pessoa.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.teste.poc.domain.cidade.Cidade;
import br.com.teste.poc.domain.pessoa.Pessoa;
import br.eti.nexus.kernel.application.dto.annotation.Attribute;
import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import br.eti.nexus.kernel.application.dto.response.SingleResponseDTO;
import br.eti.nexus.kernel.infrastructure.util.DateUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PessoaDTO extends SingleResponseDTO  {

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 3238541579718107040L;

	@Attribute(field="id")
	private String id;
	
	@Attribute(field="codigo")
	private Long code;
	
	@Attribute(field="nome")
	private String name;
	
	@Attribute(field="nascimento")
	private String birth;

	@Attribute(field="id_cidade")
	private Cidade city;
	

	public PessoaDTO(Pessoa p, CollectionRequestDTO request) {
		super();
		
		List<String> attributes = request != null ? request.getAttributes() : new ArrayList<>();
		
		if (attributes.isEmpty() || attributes.contains("id")) {
        	this.id = p.getId();
        }   
		
		if (attributes.isEmpty() || attributes.contains("code")) {
        	this.code = p.getCodigo();
        }
		
		if (attributes.isEmpty() || attributes.contains("name")) {
        	this.name = p.getNome();
        }
		
		if (attributes.isEmpty() || attributes.contains("birth")) {
        	this.birth = p.getNascimento() != null ? new DateUtil().formatDateTime(p.getNascimento()) : null;
        }
		
		if (attributes.isEmpty() || attributes.contains("cidade")) {
        	this.city = p.getCidade();
        }
		
	}
}
