package br.com.teste.poc.app.venda.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.eti.nexus.kernel.application.dto.request.CollectionRequestDTO;
import lombok.ToString;

@ToString
public class VendaRequestDTO extends CollectionRequestDTO {

	private static final long serialVersionUID = 148650972869113742L;

	private String id;
	private Long code;
	private Date data_sale;
	private BigDecimal unit_value;

	public VendaRequestDTO() {
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


	public BigDecimal getUnit_value() {
		return unit_value;
	}

	public void setUnit_value(BigDecimal unit_value) {
		this.unit_value = unit_value;
	}

	public Date getData_sale() {
		return data_sale;
	}

	public void setData_sale(Date data_sale) {
		this.data_sale = data_sale;
	}
	
}
