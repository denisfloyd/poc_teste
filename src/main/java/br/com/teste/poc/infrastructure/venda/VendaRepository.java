package br.com.teste.poc.infrastructure.venda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.venda.Venda;
import br.com.teste.poc.domain.venda.repository.IVendaRepository;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class VendaRepository implements IVendaRepository {

	@Autowired
	private VendaJPARepository repository;

	@Autowired
	private RequisitionUtil<Venda> requisition;

	@Override
	public void salvar(Venda v) {

		log.debug(" VendaRepository.salvar() ");
		log.debug(" Venda: " + v);

		repository.save(v);
	}

	@Override
	public void atualizar(Venda v) {

		log.debug(" ");
		log.debug(" PessoaRepository.atualizar() ");
		log.debug(" Pessoa: " + v);
		log.debug(" ");

		repository.save(v);
	}

	@Override
	public void excluirVenda(String id) {

		log.debug(" VendaRepository.excluirVenda() ");
		log.debug(" ID: " + id);

		repository.deleteById(id);
	}

	@Override
	public List<Venda> selecionarTodos() {

		log.debug(" VendaRepository.selecionarTodos() ");

		Page<Venda> page = null;

		if (requisition.getWhere() != null) {

			page = repository.findAll(requisition.getWhere(), requisition.getPage());

		} else {

			page = repository.findAll(requisition.getPage());

		}

		requisition.setHasNext(page.hasNext());

		return page.getContent();
	}

	@Override
	public Venda buscarPorID(String id) {

		log.debug(" VendaRepository.buscarPorID() ");
		log.debug(" ID: " + id);

		return repository.findById(id).orElse(null);
	}

	@Override
	public Venda validarCodigo(Venda v) {

		log.debug(" VendaRepository.validarCodigo() ");
		log.debug(" Venda: " + v);

		return repository.findByCodigoAndIdNot(v.getCodigo(), v.getId() != null ? v.getId() : "");
	}

}
