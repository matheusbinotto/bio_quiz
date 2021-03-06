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
package br.org.quiz.database.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the questao database table.
 * 
 */
@Entity
@Table(name="questao",schema="quiz")
@NamedQueries({
	@NamedQuery(name="Question.search",query="SELECT q FROM Question q WHERE LOWER(q.descricao) LIKE ?1")
})
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_questao")
	private Integer idQuestao;

	private String descricao;

	//bi-directional many-to-one association to Choice
	@OneToMany(mappedBy="question",cascade={CascadeType.PERSIST,CascadeType.REMOVE},fetch=FetchType.EAGER)
	private List<Choice> choices;

    public Question() {
    	choices = new ArrayList<Choice>();
    }

	public Integer getIdQuestao() {
		return this.idQuestao;
	}

	public void setIdQuestao(Integer idQuestao) {
		this.idQuestao = idQuestao;
		distributeIdOverChoices();
	}

	private void distributeIdOverChoices() {
		if(choices != null) {
			for(Choice c : choices) 
				c.setRefQuestao(idQuestao);
		}
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Choice> getChoices() {
		return this.choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
	public void addChoice(Choice c) {
		this.choices.add(c);
	}
	
	public boolean hasAnswer() {
		
		if(choices == null)
			return false;
		
		for(Choice c : choices) {
			if(c.isValidAnswer())
				return true;
		}
		
		return false;
	}

	public Choice getAnswer() {
		
		if(choices == null || choices.isEmpty())
			return null;
		
		for(Choice c : choices) {
			if(c.isValidAnswer())
				return c;
		}
		
		return null;
	}
	
	public List<Choice> getAnswers() {
		
		if(choices == null || choices.isEmpty())
			return null;
		
		List<Choice> validAnswers = new ArrayList<Choice>();
		for(Choice c : choices) {
			if(c.isValidAnswer())
				validAnswers.add(c);
		}
		
		return validAnswers;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Question [idQuestao=%s, descricao=%s, \nchoices=%s]\n", idQuestao,
				descricao, choices);
	}

	public void removeChoice(Choice choiceToRemove) {
		this.choices.remove(choiceToRemove);
	}
	
	
}