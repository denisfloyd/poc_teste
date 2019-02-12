package br.com.teste.poc.domain.produto.service;

import br.com.teste.poc.domain.pessoa.Pessoa;
import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;

/**
 * Interface responsável pelos métodos de Negócio do Domínio Pessoa.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
public interface IProdutoService {

	/**
	 * Inserir Pessoa.
	 * 
	 * @param p
	 * @return
	 * @throws NexusException
	 */
	Pessoa inserir(Pessoa p) throws NexusException;

	/**
	 * Autualizar Pessoa.
	 * 
	 * @param p
	 * @param id
	 * 
	 * @return
	 * @throws NexusException
	 */
	Pessoa atualizar(String id, Pessoa p) throws NexusException;

	/**
	 * Método responsável pela seleção de pessoas.
	 * 
	 * @return
	 * @throws NexusException
	 */
	PageModel<Pessoa> selecionarTodos() throws NexusException;

	/**
	 * Excluir pessoa.
	 * 
	 * @param id
	 * @throws NexusException
	 */
	void excluir(String id) throws NexusException;

}
