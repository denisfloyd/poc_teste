package br.com.teste.poc.domain.produto.repository;

import java.util.List;

import br.com.teste.poc.domain.produto.Produto;

/**
 * Interface responsável pelos métodos de acesso ao banco de dados de Produto.
 * 
 * @author Denis M. Ladeira
 *
 */
public interface IProdutoRepository {

	/**
	 * Salvar Produto.
	 * 
	 * @param p
	 */
	void salvar(Produto p);

	/**
	 * Autalizar Produto.
	 * 
	 * @param p
	 */
	void atualizar(Produto p);

	/**
	 * Excluír Produto.
	 * 
	 * @param p
	 */
	void excluirProduto(String id);

	/**
	 * Buscar por ID.
	 * 
	 * @param id
	 * @return
	 */
	Produto buscarPorID(String id);

	/**
	 * Método responsável pela validação de Produto por código.
	 * 
	 * @param p
	 * @return
	 */
	Produto validarCodigo(Produto p);

	/**
	 * Selecionar Todos.
	 * 
	 * @return
	 */
	List<Produto> selecionarTodos();

}
