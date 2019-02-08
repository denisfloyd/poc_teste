package br.com.teste.poc.app.cidade.dto;

import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import lombok.ToString;

@ToString
public class CidadeRequestDTO extends CollectionRequestDTO {

	private static final long serialVersionUID = 148650972869113742L;

	private String id;
	private Long code;
	private String name;
	private String uf;

	public CidadeRequestDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	
	
	
}
