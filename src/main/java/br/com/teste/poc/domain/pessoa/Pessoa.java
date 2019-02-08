package br.com.teste.poc.domain.pessoa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.teste.poc.domain.cidade.Cidade;
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
@Entity(name = "person")
public class Pessoa implements Model {
	
	
	@Id
	@Column(length = 32)
	@Setter
	private String id;

	@Column(name = "code", length = 3)
	private Long codigo;

	@Column(name = "name", length = 50)
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth")
	private Date nascimento;
	
	//@Column(name = "id_cidade")
	
	@OneToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
}
