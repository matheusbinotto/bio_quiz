/*
 * Copyright 2012 Matheus Binotto, Welliton de Souza
 *
 * Este arquivo é parte do programa BioQuiz
 *
 * BioQuiz é um software livre; você pode redistribui-lo e/ou 
 * modifica-lo dentro dos termos da Licença Pública Geral GNU como 
 * publicada pela Fundação do Software Livre (FSF); na versão 2 da 
 * Licença, ou (na sua opnião) qualquer versão.
 *
 * Este programa é distribuido na esperança que possa ser  util, 
 * mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 * Licença Pública Geral GNU para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa, se não, escreva para a Fundação do Software
 * Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.org.quiz.database.jpa;

import java.util.List;


public interface DAOTransactions<T> {

	/**
	 * Persiste o objeto da entidade do banco
	 * de dados.
	 * @param object - a entidade do banco de dados.
	 */
	public void insert(T object);
	
	
	/**
	 * Atualiza a entidade do banco
	 * de dados. este método pesquisa o objeto
	 * a ser atualizado para que o mesmo entre
	 * no contexto de persistencia. A seguir o desacopla
	 * do contexto, causando a sincronização com o banco
	 * de dados.ATENÇÃO: MÉTODO NÃO TRANSACTIONAL
	 * @param object - a entidade a ser atualizada.
	 * @param id - o id do objeto a ser atualizado.
	 */
	public void update(T object);
	
	
	
	/**
	 * Deleta o dado objeto da base 
	 * de dados. Este método deve receber 
	 * o objeto da entidade ja adentrado 
	 * no contexto de persistencia.
	 * @param object - o objeto a ser removido.
	 */
	public void deleteById(Object id);
	
	/**
	 * Deleta a entidade da base de dados.
	 * Este método deve receber 
	 * o objeto da entidade ja adentrado 
	 * no contexto de persistencia.
	 * @param object
	 */
	public void delete(T object);

	
	/**
	 * Encontra o a entidade do banco de dados
	 * identificada pelo seu id.
	 * @param id - a chave primária da entidade
	 * @return uma instancia do objeto que representa a entidade
	 */
	public T find(Object id);

	/**
	 * Encontra uma lista da entidade do banco de 
	 * dados filtrada pela lista de condições configuradas no
	 * no objeto de construtor de query deste objeto de persistência.
	 */
	public List<T> executeNamedQuery(String queryName, Object...params);
	
	/**
	 * Encontra um úunico resultado da entidade do banco de 
	 * dados filtrada pela lista de condições configuradas no
	 * no objeto de construtor de query deste objeto de persistência.
	 */
	public T executeNamedQuerySingleResult(String queryName, Object...params);
	
	
	/**
	 * Encontra um lista representando as ocorrencias
	 * de um dos membros da entidade do banco de dados
	 */
	public <K> List<K> executeNamedQuery(String queryName, Class<K> resultClass, Object...params);

	/**
	 * Encontra um unico membro selecionado em uma select.
	 * @return
	 */
	public <K> K searchSingleMember(String queryName, Class<K> resultClass, Object...params);

	
	/**
	 * Desacopla um objeto do contexto de persistência
	 */
	public void detach(T o); 
	
	/**
	 * Inicia um transação manualmente
	 * no banco de dados através do JPA.
	 */
	public void initTransaction();
	
	/**
	 * Comita um transação manualmente
	 * no banco de dados através do JPA.
	 */
	public void commit();

	
	/**
	 * Volta o contexto de persistência
	 * ao estado original antes do inicio da
	 * transação corrente, manualmente.
	 */
	public void rollback();


	public Integer getMax(String string);
}
