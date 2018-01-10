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
		if (this.SAG != null || this.SAD != null)
		{
			valeur = valeur + ':';
		}
		if (this.SAG != null)
		{
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

	public boolean checkABRR(ABRR a, Integer min, Integer max) {
		if (a == null) {
			return true;
		}
		if (a.racine < min || a.racine > max) {
			return false;
		}
		if ((a.SAG != null) && (a.SAG.racine < a.racine)) {
			return false;
		}
		if ((a.SAD != null) && (a.SAD.racine > a.racine)) {
			return false;
		}
		return (checkABRR(a.SAG, min, max) && checkABRR(a.SAD, min, max));
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
