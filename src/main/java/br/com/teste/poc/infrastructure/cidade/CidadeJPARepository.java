package br.com.teste.poc.infrastructure.cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.cidade.Cidade;

@Repository
public interface CidadeJPARepository extends JpaRepository<Cidade, String> {
	
	Cidade findByCodigoAndIdNot(Long codigo, String id);
}
