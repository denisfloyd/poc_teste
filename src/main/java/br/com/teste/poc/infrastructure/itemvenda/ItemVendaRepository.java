package br.com.teste.poc.infrastructure.itemvenda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.itemvenda.ItemVenda;
import br.com.teste.poc.domain.itemvenda.repository.IItemVendaRepository;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class ItemVendaRepository implements IItemVendaRepository {

	@Autowired
	private ItemVendaJPARepository repository;

	@Autowired
	private RequisitionUtil<ItemVenda> requisition;

	@Override
	public void salvar(ItemVenda iv) {

		log.debug(" ItemVendaRepository.salvar() ");
		log.debug(" ItemVenda: " + iv);

		repository.save(iv);
	}

	@Override
	public void atualizar(ItemVenda iv) {

		log.debug(" ");
		log.debug(" ItemVendaRepository.atualizar() ");
		log.debug(" ItemVenda: " + iv);
		log.debug(" ");

		repository.save(iv);
	}

	@Override
	public void excluirItemVenda(String id) {

		log.debug(" ItemVendaRepository.excluirItemVenda() ");
		log.debug(" ID: " + id);

		repository.deleteById(id);
	}

	@Override
	public List<ItemVenda> selecionarTodos() {

		log.debug(" ItemVendaRepository.selecionarTodos() ");

		Page<ItemVenda> page = null;

		if (requisition.getWhere() != null) {

			page = repository.findAll(requisition.getWhere(), requisition.getPage());

		} else {

			page = repository.findAll(requisition.getPage());

		}

		requisition.setHasNext(page.hasNext());

		return page.getContent();
	}

	@Override
	public ItemVenda buscarPorID(String id) {

		log.debug(" ItemVendaRepository.buscarPorID() ");
		log.debug(" ID: " + id);

		return repository.findById(id).orElse(null);
	}

	/*
	@Override
	public Venda validarCodigo(Venda v) {

		log.debug(" VendaRepository.validarCodigo() ");
		log.debug(" Venda: " + v);

		return repository.findByCodigoAndIdNot(v.getCodigo(), v.getId() != null ? v.getId() : "");
	}
	*/

}
