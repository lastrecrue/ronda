package ma.ronda.model;

/**
 * 
 * @author achraf
 * 
 */
public class Joueur extends Utilisateur {

	private Tas tasMain;
	private Tas tasGain;
	private String etat;

	public Tas getTasMain() {
		return tasMain;
	}

	public void setTasMain(Tas tasMain) {
		this.tasMain = tasMain;
	}

	public Tas getTasGain() {
		return tasGain;
	}

	public void setTasGain(Tas tasGain) {
		this.tasGain = tasGain;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
