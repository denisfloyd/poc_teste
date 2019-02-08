package br.com.teste.poc.domain.cidade.service;

import br.com.teste.poc.domain.cidade.Cidade;
import br.eti.nexus.kernel.exception.NexusException;
import br.eti.nexus.kernel.infrastructure.dynamic.jpa.model.PageModel;

/**
 * Interface responsável pelos métodos de Negócio do Domínio Pessoa.
 * @author Denis M. Ladeira
 * 
 */
public interface ICidadeService {

	/**
	 * Inserir Cidade.
	 * 
	 * @param p
	 * @return
	 * @throws NexusException
	 */
	Cidade inserir(Cidade c) throws NexusException;

	/**
	 * Atualizar cidade
	 * 
	 * @param p
	 * @param id
	 * 
	 * @return
	 * @throws NexusException
	 */
	Cidade atualizar(String id, Cidade c) throws NexusException;

	/**
	 * Método responsável pela seleção de cidades.
	 * 
	 * @return
	 * @throws NexusException
	 */
	PageModel<Cidade> selecionarTodos() throws NexusException;

	/**
	 * Excluir cidade.
	 * 
	 * @param id
	 * @throws NexusException
	 */
	void excluir(String id) throws NexusException;

}
