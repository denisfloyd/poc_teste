package br.com.teste.poc.infrastructure.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.produto.Produto;

@Repository
public interface ProdutoJPARepository extends JpaRepository<Produto, String> {
	
	Produto findByCodigoAndIdNot(Long codigo, String id);
}
