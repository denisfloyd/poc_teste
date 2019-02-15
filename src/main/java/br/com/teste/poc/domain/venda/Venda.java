package br.com.teste.poc.domain.venda;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.eti.nexus.kernel.domain.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Denis M. Ladeira - denis.ladeira@fivesoftware.com.br
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "venda")
public class Venda implements Model {

	@Id
	@Column(length = 32)
	@Setter
	private String id;
	
	@Column(name = "code", length = 3)
	private Long codigo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_sale")
	private Date data_venda;
	
	@Column(name = "total_value")
	private BigDecimal valor_total;
	
	
}
