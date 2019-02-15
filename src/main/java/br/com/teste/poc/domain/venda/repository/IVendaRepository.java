package br.com.teste.poc.domain.venda.repository;

import java.util.List;

import br.com.teste.poc.domain.venda.Venda;

/**
 * Interface responsável pelos métodos de acesso ao banco de dados de venda.
 * 
 * @author Denis M. Ladeira
 *
 */
public interface IVendaRepository {

	/**
	 * Salvar Venda.
	 * 
	 * @param p
	 */
	void salvar(Venda v);

	/**
	 * Atualizar Venda
	 * 
	 * @param p
	 */
	void atualizar(Venda v);

	/**
	 * Excluír Venda.
	 * 
	 * @param p
	 */
	void excluirVenda(String id);

	/**
	 * Buscar por ID.
	 * 
	 * @param id
	 * @return
	 */
	Venda buscarPorID(String id);

	/**
	 * Método responsável pela validação de Venda por código.
	 * 
	 * @param p
	 * @return
	 */
	Venda validarCodigo(Venda v);

	/**
	 * Selecionar Todos.
	 * 
	 * @return
	 */
	List<Venda> selecionarTodos();

}
