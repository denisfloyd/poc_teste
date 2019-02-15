package br.com.teste.poc.infrastructure.itemvenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.itemvenda.ItemVenda;

@Repository
public interface ItemVendaJPARepository extends JpaRepository<ItemVenda, String> {
	
	//Venda findByCodigoAndIdNot(Long codigo, String id);
}
