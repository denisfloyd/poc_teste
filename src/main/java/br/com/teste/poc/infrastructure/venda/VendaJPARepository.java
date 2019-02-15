package br.com.teste.poc.infrastructure.venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.venda.Venda;

@Repository
public interface VendaJPARepository extends JpaRepository<Venda, String> {
	
	Venda findByCodigoAndIdNot(Long codigo, String id);
}
