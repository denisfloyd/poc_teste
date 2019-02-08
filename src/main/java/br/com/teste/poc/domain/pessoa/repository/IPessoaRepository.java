package br.com.teste.poc.domain.pessoa.repository;

import java.util.List;

import br.com.teste.poc.domain.pessoa.Pessoa;;

/**
 * Interface responsável pelos métodos de acesso ao banco de dados de Pessoa.
 * 
 * @author Patrick Francis Gomes Rocha - patrick.gomes@nexus.eti.br
 *
 */
public interface IPessoaRepository {

	/**
	 * Salvar Pessoa.
	 * 
	 * @param p
	 */
	void salvar(Pessoa p);

	/**
	 * Autalizar Pessoa.
	 * 
	 * @param p
	 */
	void atualizar(Pessoa p);

	/**
	 * Excluír Pessoa.
	 * 
	 * @param p
	 */
	void excluirPessoa(String id);

	/**
	 * Buscar por ID.
	 * 
	 * @param id
	 * @return
	 */
	Pessoa buscarPorID(String id);

	/**
	 * Método responsável pela validação de Pessoa por código.
	 * 
	 * @param p
	 * @return
	 */
	Pessoa validarCodigo(Pessoa p);

	/**
	 * Selecionar Todos.
	 * 
	 * @return
	 */
	List<Pessoa> selecionarTodos();

}
