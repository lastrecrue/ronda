package ma.ronda.model;

import java.util.List;

/**
 * 
 * @author achraf
 * 
 */
public class Equipe {
	private List<Joueur> joueurList;
	private Integer score;

	public List<Joueur> getJoueurList() {
		return joueurList;
	}

	public void setJoueurList(List<Joueur> joueurList) {
		this.joueurList = joueurList;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
