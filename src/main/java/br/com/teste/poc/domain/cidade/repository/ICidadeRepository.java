package br.com.teste.poc.domain.cidade.repository;

import java.util.List;

import br.com.teste.poc.domain.cidade.Cidade;

public interface ICidadeRepository {

	/**
	 * Salvar Cidade.
	 * 
	 * @param p
	 */
	void salvar(Cidade c);

	/**
	 * Autalizar Cidade.
	 * 
	 * @param p
	 */
	void atualizar(Cidade c);

	/**
	 * Excluír Cidade.
	 * 
	 * @param p
	 */
	void excluirCidade(String id);

	/**
	 * Buscar por ID.
	 * 
	 * @param id
	 * @return
	 */
	Cidade buscarPorID(String id);

	/**
	 * Selecionar Todos.
	 * 
	 * @return
	 */
	List<Cidade> selecionarTodos();
	
	/**
	 * Validar código unico da Cidade
	 */
	Cidade validarCodigo(Cidade c);

}