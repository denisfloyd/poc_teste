package br.com.teste.poc.domain.itemvenda.repository;

import java.util.List;

import br.com.teste.poc.domain.itemvenda.ItemVenda;
/**
 * Interface responsável pelos métodos de acesso ao banco de dados de item da venda.
 * 
 * @author Denis M. Ladeira
 *
 */
public interface IItemVendaRepository {

	/**
	 * Salvar Item Venda.
	 * 
	 * @param p
	 */
	void salvar(ItemVenda iv);

	/**
	 * Atualizar Item Venda
	 * 
	 * @param p
	 */
	void atualizar(ItemVenda iv);

	/**
	 * Excluír ITem Venda.
	 * 
	 * @param p
	 */
	void excluirItemVenda(String id);

	/**
	 * Buscar por ID.
	 * 
	 * @param id
	 * @return
	 */
	ItemVenda buscarPorID(String id);

	/**
	 * Método responsável pela validação de Venda por código.
	 * 
	 * @param p
	 * @return
	 */
	//ItemVenda validarCodigo(ItemVenda v);

	/**
	 * Selecionar Todos.
	 * 
	 * @return
	 */
	List<ItemVenda> selecionarTodos();

}
