package br.com.teste.poc.domain.produto.service;

import br.com.teste.poc.domain.produto.Produto;
import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;

/**
 * Interface responsável pelos métodos de Negócio do Domínio Pessoa.
 * @author Denis M. Ladeira
 */
public interface IProdutoService {

	/**
	 * Inserir Produto.
	 * 
	 * @param p
	 * @return
	 * @throws NexusException
	 */
	Produto inserir(Produto p) throws NexusException;

	/**
	 * Atualizar Produto.
	 * 
	 * @param p
	 * @param id
	 * 
	 * @return
	 * @throws NexusException
	 */
	Produto atualizar(String id, Produto p) throws NexusException;

	/**
	 * Método responsável pela seleção de produtos.
	 * 
	 * @return
	 * @throws NexusException
	 */
	PageModel<Produto> selecionarTodos() throws NexusException;

	/**
	 * Excluir produto.
	 * 
	 * @param id
	 * @throws NexusException
	 */
	void excluir(String id) throws NexusException;

}
