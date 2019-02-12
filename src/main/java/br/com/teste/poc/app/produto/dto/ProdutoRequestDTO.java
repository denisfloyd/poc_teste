package br.com.teste.poc.app.produto.dto;

import java.math.BigDecimal;

import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import lombok.ToString;

@ToString
public class ProdutoRequestDTO extends CollectionRequestDTO {

	private static final long serialVersionUID = 148650972869113742L;

	private String id;
	private Long code;
	private String description;
	private BigDecimal unit_value;

	public ProdutoRequestDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnit_value() {
		return unit_value;
	}

	public void setUnit_value(BigDecimal unit_value) {
		this.unit_value = unit_value;
	}
}
