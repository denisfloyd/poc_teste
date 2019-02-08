package br.com.teste.poc.infrastructure.pessoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.pessoa.Pessoa;
import br.com.teste.poc.domain.pessoa.repository.IPessoaRepository;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class PessoaRepository implements IPessoaRepository {

	@Autowired
	private PessoaJPARepository repository;

	@Autowired
	private RequisitionUtil<Pessoa> requisition;

	@Override
	public void salvar(Pessoa p) {

		log.debug(" PessoaRepository.salvar() ");
		log.debug(" Pessoa: " + p);

		repository.save(p);
	}

	@Override
	public void atualizar(Pessoa p) {

		log.debug(" ");
		log.debug(" PessoaRepository.atualizar() ");
		log.debug(" Pessoa: " + p);
		log.debug(" ");

		repository.save(p);
	}

	@Override
	public void excluirPessoa(String id) {

		log.debug(" PessoaRepository.excluirPessoa() ");
		log.debug(" ID: " + id);

		repository.deleteById(id);
	}

	@Override
	public List<Pessoa> selecionarTodos() {

		log.debug(" PessoaRepository.selecionarTodos() ");

		Page<Pessoa> page = null;

		if (requisition.getWhere() != null) {

			page = repository.findAll(requisition.getWhere(), requisition.getPage());

		} else {

			page = repository.findAll(requisition.getPage());

		}

		requisition.setHasNext(page.hasNext());

		return page.getContent();
	}

	@Override
	public Pessoa buscarPorID(String id) {

		log.debug(" PessoaRepository.buscarPorID() ");
		log.debug(" ID: " + id);

		return repository.findById(id).orElse(null);
	}

	@Override
	public Pessoa validarCodigo(Pessoa p) {

		log.debug(" PessoaRepository.validarCodigo() ");
		log.debug(" Pessoa: " + p);

		return repository.findByCodigoAndIdNot(p.getCodigo(), p.getId() != null ? p.getId() : "");
	}

}
