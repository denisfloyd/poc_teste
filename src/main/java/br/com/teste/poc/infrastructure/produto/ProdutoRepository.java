package br.com.teste.poc.infrastructure.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.teste.poc.domain.produto.Produto;
import br.com.teste.poc.domain.produto.repository.IProdutoRepository;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.util.RequisitionUtil;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class ProdutoRepository implements IProdutoRepository {

	@Autowired
	private ProdutoJPARepository repository;

	@Autowired
	private RequisitionUtil<Produto> requisition;

	@Override
	public void salvar(Produto p) {

		log.debug(" ProdutoRepository.salvar() ");
		log.debug(" Produto: " + p);

		repository.save(p);
	}

	@Override
	public void atualizar(Produto p) {

		log.debug(" ");
		log.debug(" ProdutoRepository.atualizar() ");
		log.debug(" Produto: " + p);
		log.debug(" ");

		repository.save(p);
	}

	@Override
	public void excluirProduto(String id) {

		log.debug(" ProdutoRepository.excluirProduto() ");
		log.debug(" ID: " + id);

		repository.deleteById(id);
	}

	@Override
	public List<Produto> selecionarTodos() {

		log.debug(" ProdutoRepository.selecionarTodos() ");

		Page<Produto> page = null;

		if (requisition.getWhere() != null) {

			page = repository.findAll(requisition.getWhere(), requisition.getPage());

		} else {

			page = repository.findAll(requisition.getPage());

		}

		requisition.setHasNext(page.hasNext());

		return page.getContent();
	}

	@Override
	public Produto buscarPorID(String id) {

		log.debug(" ProdutoRepository.buscarPorID() ");
		log.debug(" ID: " + id);

		return repository.findById(id).orElse(null);
	}

	@Override
	public Produto validarCodigo(Produto p) {

		log.debug(" ProdutoRepository.validarCodigo() ");
		log.debug(" Produto: " + p);

		return repository.findByCodigoAndIdNot(p.getCodigo(), p.getId() != null ? p.getId() : "");
	}

}
