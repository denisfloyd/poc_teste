package br.com.teste.poc.app.itemvenda.dto;

import java.math.BigDecimal;

import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import lombok.ToString;

@ToString
public class ItemVendaRequestDTO extends CollectionRequestDTO {

	private static final long serialVersionUID = 148650972869113742L;

	private String id;
	private Integer quantidade;
	private BigDecimal item_value;

	public ItemVendaRequestDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getItem_value() {
		return item_value;
	}

	public void setItem_value(BigDecimal item_value) {
		this.item_value = item_value;
	}
	
}
