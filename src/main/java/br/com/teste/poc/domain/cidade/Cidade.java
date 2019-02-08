package br.com.teste.poc.domain.cidade;

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
@Entity(name = "cidade")
public class Cidade implements Model {

	@Id
	@Column(length = 32)
	@Setter
	private String id;
	
	@Column(name = "code", length = 3)
	private Long codigo;

	@Column(name = "name", length = 60)
	private String nome;

	@Column(name = "uf", length = 2)
	private String uf;
}