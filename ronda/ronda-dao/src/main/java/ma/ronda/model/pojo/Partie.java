package ma.ronda.model.pojo;

import java.util.List;

/**
 * 
 * @author achraf
 * 
 */
public class Partie {
	private List<Equipe> equipeList;
	private Tas tasTapis;
	private Tas tasTalon;

	public List<Equipe> getEquipeList() {
		return equipeList;
	}

	public void setEquipeList(List<Equipe> equipeList) {
		this.equipeList = equipeList;
	}

	public Tas getTasTapis() {
		return tasTapis;
	}

	public void setTasTapis(Tas tasTapis) {
		this.tasTapis = tasTapis;
	}

	public Tas getTasTalon() {
		return tasTalon;
	}

	public void setTasTalon(Tas tasTalon) {
		this.tasTalon = tasTalon;
	}

}
