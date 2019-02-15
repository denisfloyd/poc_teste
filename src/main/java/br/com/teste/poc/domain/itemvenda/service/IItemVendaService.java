package br.com.teste.poc.domain.itemvenda.service;

import br.com.teste.poc.domain.itemvenda.ItemVenda;
import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;

/**
 * Interface responsável pelos métodos de Negócio do Domínio Item de Venda.
 * @author Denis M. Ladeira
 */
public interface IItemVendaService {

	/**
	 * Inserir Item de Venda.
	 * 
	 * @param v
	 * @return
	 * @throws NexusException
	 */
	ItemVenda inserir(ItemVenda iv) throws NexusException;

	/**
	 * Atualizar Item de Venda.
	 * 
	 * @param p
	 * @param id
	 * 
	 * @return
	 * @throws NexusException
	 */
	ItemVenda atualizar(String id, ItemVenda iv) throws NexusException;

	/**
	 * Método responsável pela seleção de Items de Venda.
	 * 
	 * @return
	 * @throws NexusException
	 */
	PageModel<ItemVenda> selecionarTodos() throws NexusException;

	/**
	 * Excluir Item de Venda.
	 * 
	 * @param id
	 * @throws NexusException
	 */
	void excluir(String id) throws NexusException;

}
