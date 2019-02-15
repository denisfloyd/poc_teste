package br.com.teste.poc.domain.venda.service;

import br.com.teste.poc.domain.venda.Venda;
import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;

/**
 * Interface responsável pelos métodos de Negócio do Domínio Pessoa.
 * @author Denis M. Ladeira
 */
public interface IVendaService {

	/**
	 * Inserir Venda.
	 * 
	 * @param p
	 * @return
	 * @throws NexusException
	 */
	Venda inserir(Venda v) throws NexusException;

	/**
	 * Atualizar Venda.
	 * 
	 * @param p
	 * @param id
	 * 
	 * @return
	 * @throws NexusException
	 */
	Venda atualizar(String id, Venda v) throws NexusException;

	/**
	 * Método responsável pela seleção de Vendas.
	 * 
	 * @return
	 * @throws NexusException
	 */
	PageModel<Venda> selecionarTodos() throws NexusException;

	/**
	 * Excluir Venda.
	 * 
	 * @param id
	 * @throws NexusException
	 */
	void excluir(String id) throws NexusException;

}
