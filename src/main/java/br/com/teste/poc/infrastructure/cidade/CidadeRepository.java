package br.com.teste.poc.infrastructure.cidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.cidade.Cidade;
import br.com.teste.poc.domain.cidade.repository.ICidadeRepository;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class CidadeRepository implements ICidadeRepository {

	@Autowired
	private CidadeJPARepository repository;

	@Autowired
	private RequisitionUtil<Cidade> requisition;
	
	@Override
	public void salvar(Cidade c) {

		log.debug(" CidadeRepository.salvar() ");
		log.debug(" Cidade: " + c);

		repository.save(c);
	}

	@Override
	public void atualizar(Cidade c) {

		log.debug(" ");
		log.debug(" CidadeRepository.atualizar() ");
		log.debug(" Cidade: " + c);
		log.debug(" ");

		repository.save(c);
	}

	@Override
	public void excluirCidade(String id) {

		log.debug(" CidadeRepository.excluirCidade() ");
		log.debug(" ID: " + id);

		repository.deleteById(id);
	}
	
	@Override
	public Cidade buscarPorID(String id) {

		log.debug(" CidadeRepository.buscarPorID() ");
		log.debug(" ID: " + id);

		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Cidade> selecionarTodos() {

		log.debug(" CidadeRepository.selecionarTodos() ");

		Page<Cidade> page = null;

		if (requisition.getWhere() != null) {

			page = repository.findAll(requisition.getWhere(), requisition.getPage());

		} else {

			page = repository.findAll(requisition.getPage());

		}

		requisition.setHasNext(page.hasNext());

		return page.getContent();
	}
	
	@Override
	public Cidade validarCodigo(Cidade c) {

		log.debug(" CidadeRepository.validarCodigo() ");
		log.debug(" Cidade: " + c);

		return repository.findByCodigoAndIdNot(c.getCodigo(), c.getId() != null ? c.getId() : "");
	}

}
