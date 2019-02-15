package br.com.teste.poc.domain.itemvenda;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.eti.nexus.kernel.domain.Model;
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
@Entity(name = "venda")
public class ItemVenda implements Model {
	
	@Id
	@Column(length = 32)
	@Setter
	private String id;
	
	@Column(name = "quantity", length = 4)
	private Integer quantidade;
	
	@Column(name = "item_value")
	private BigDecimal valor_item;
}
