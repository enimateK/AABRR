public class ABRR{
	private Integer racine;
	private ABRR SAG;
	private ABRR SAD;
	/**
	 * @param args
	 */
	public ABRR(Integer racine){
		this.racine = racine;
		this.SAG = null;
		this.SAD = null;		
	}
	public void CreerSAG(Integer rac)
	{
		this.SAG = new ABRR(rac);
	}
	
	public void CreerSAD(Integer rac)
	{
		this.SAD = new ABRR(rac);
	}
	
	public ABRR getSAG()
	{
		return this.SAG;
	}
	
	public ABRR getSAD()
	{
		return this.SAD;
	}
	
	public Integer getRacine()
	{
		return this.racine;
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
	
	
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
