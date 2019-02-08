package br.com.teste.poc.infrastructure.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.pessoa.Pessoa;

@Repository
public interface PessoaJPARepository extends JpaRepository<Pessoa, String> {
	
	Pessoa findByCodigoAndIdNot(Long codigo, String id);
}
