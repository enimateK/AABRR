package src;

public class ABRR {
	private Integer racine;
	private ABRR SAG;
	private ABRR SAD;

	public void setRacine(Integer racine) {
		this.racine = racine;
	}

	public ABRR getSAG() {
		return SAG;
	}

	public void setSAG(ABRR SAG) {
		this.SAG = SAG;
	}


	public ABRR getSAD() {
		return SAD;
	}

	public void setSAD(ABRR SAD) {
		this.SAD = SAD;
	}

	public Integer getRacine() {
		return racine;
	}

	public ABRR(Integer racine){
		this.racine = racine;
		this.SAG = null;
		this.SAD = null;
	}

	public void CreerSAG(Integer racine){
		this.SAG = new ABRR(racine);
	}

	public void CreerSAD(Integer racine){
		this.SAD = new ABRR(racine);
	}

	public void choixSA(Integer racineSA){
		if (racineSA <= this.racine){
			CreerSAD(racineSA);
		}else{
			CreerSAG(racineSA);
		}
	}

	public String Parcours(String valeur){
		valeur = valeur + this.racine;

		if (this.SAG != null)
		{
			valeur = valeur + ':';
			valeur = this.SAG.Parcours(valeur);
		}

		if (this.SAD != null)
		{
			valeur = valeur + ':';
			valeur = this.SAD.Parcours(valeur);
		}
		return valeur;
	}

	public void insertABRR(Integer valeur)
	{
		if(this.racine == null)
		{
			this.racine = valeur;
		}
		else {
			if (valeur >= this.racine)
			{
				if (this.SAG == null)
				{
					CreerSAG(valeur);
				}else {
					this.getSAG().insertABRR(valeur);
				}
			}
			else{
				if (this.SAD == null)
				{
					CreerSAD(valeur);
				}else {
					this.getSAD().insertABRR(valeur);
				}
			}
		}
	}

	public boolean verif(ABRR arbre, Integer min, Integer max) {
		if (arbre == null) {
			return true;
		}else {
		if (arbre.racine < min || arbre.racine > max) {
			return false;
		}
		if ((arbre.SAG != null) && (arbre.SAG.racine < arbre.racine)) {
			return false;
		}
		if ((arbre.SAD != null) && (arbre.SAD.racine > arbre.racine)) {
			return false;
		}
		}
		return (verif(arbre.SAG, min, max) && verif(arbre.SAD, min, max));
	}

	private ABRR supprimerEntier(int valeur, ABRR abr) {
		if (abr == null) {
			return null;
		}
		if (valeur == abr.racine) {
			return supprimerArbre(abr);
		}
		if (valeur > abr.racine) {
			abr.SAG = supprimerEntier(valeur, abr.SAG);
		}
		else {
			abr.SAD = supprimerEntier(valeur, abr.SAD);
		}
		return abr;
	}

	public ABRR supprimerArbre(ABRR sousArbre) {
		if (sousArbre.SAG == null) {
			return sousArbre.SAD;
		}
		if (sousArbre.SAD == null) {
			return sousArbre.SAG;
		}
		ABRR maxSAG = sousArbre.getMaxSAG(SAG);
		sousArbre.racine = maxSAG.racine;
		sousArbre.SAG = supprimerEntier(maxSAG.racine, sousArbre.SAG);
		return sousArbre;
	}

	private ABRR getMaxSAG(ABRR sousArbre) {
		return sousArbre.SAD == null ? sousArbre : getMaxSAG(sousArbre.SAD);
	}

	public ABRR recherche(int value, ABRR sousArbre) {
		if(sousArbre != null) {
			if(sousArbre.racine == value)
				return sousArbre;
			else if(sousArbre.racine < value )
				return recherche(value, sousArbre.SAG);
			else
				return recherche(value, sousArbre.SAD);
		}
		else
			return null;
	}

	public void rechercheEntier(ABRR arbre, int valeur){
		if(arbre != null){
			if(arbre.getRacine() == valeur){
				System.out.println("L'entier à été trouvé dans l'ABRR de racine " + arbre.getRacine());
			}

			else{
				if(arbre.getRacine()> valeur){
					rechercheEntier(arbre.getSAG(), valeur);
				}
				if(arbre.getRacine()< valeur){
					rechercheEntier(arbre.getSAD(), valeur);
				}
			}

		}
	}

	public void insererEntier(ABRR sousArbre, int valeur) {
		if (sousArbre.getRacine() < valeur ) {
			if(sousArbre.getSAG() == null) {
				sousArbre.setSAG(new ABRR(valeur));
			} else {
				insererEntier(sousArbre.SAG, valeur);
			}
		}
		else {
			if (sousArbre.getSAD() == null) {
				sousArbre.setSAD(new ABRR(valeur));
			} else {
				insererEntier(sousArbre.SAD, valeur);
			}
		}
	}


	public static void main(String[] args) {
	}

}
