package br.com.teste.poc.domain.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "produto")
public class Produto {
	
	@Id
	@Column(length = 32)
	@Setter
	private String id;

	@Column(name = "code", length = 3)
	private Long codigo;

	@Column(name = "description", length = 50)
	private String desc;

	@Column(name = "unit_value")
	private BigDecimal valor_unitario;
	
}
