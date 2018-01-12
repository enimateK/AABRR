import java.io.*;
import java.util.ArrayList;
import java.util.Collections;



public class AABRR {
	private Integer min; //minimum
	private Integer Max; // Maximum
	private ABRR AA;
	private AABRR SAG;
	private AABRR SAD;
	
	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return Max;
	}

	public void setMax(Integer max) {
		Max = max;
	}

	public void setAA(ABRR AA) {
		this.AA = AA;
	}

	public AABRR getSAG() {
		return SAG;
	}

	public void setSAG(AABRR SAG) {
		this.SAG = SAG;
	}

	public AABRR getSAD() {
		return SAD;
	}

	public void setSAD(AABRR SAD) {
		this.SAD = SAD;
	}

	public AABRR(Integer m, Integer M, Integer racine, ABRR arbre){
		this.min = m;
		this.Max = M;
		if (arbre == null)
		{
			this.AA = new ABRR(racine);
		}else
			this.AA = arbre;
	}
	
		public boolean InsererAABRR(AABRR n){
			//test si le noeud peut être ajouté
				boolean result = false;
				if (n.getMin() > this.getMax()) {
					if (this.SAD == null) {
						this.SAD = new AABRR(n.getMin(), n.getMax(), n.AA.getRacine(), null);
						this.SAD.AA = n.AA;
						return true;
					} else {
						return this.SAD.InsererAABRR(n);
					}
				} else if (n.getMax() < this.getMin()) {
					if (this.SAG == null) {
						this.SAG = new AABRR(n.getMin(), n.getMax(), n.AA.getRacine(), null);
						this.SAG.AA = n.AA;
						return true;
					} else {
						return this.SAG.InsererAABRR(n);
					}
				}
				return result;
		}

	public AABRR (String chemin)
	{
		AABRR Arbre1 = new AABRR (null, null, null, null);
		try
		{
			InputStream ips=new FileInputStream(chemin);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne, val="";
			while((ligne=br.readLine()) != null)
			{
				if (this.min == null && this.Max == null && this.getAA() == null)
				{
					this.CreaLigne(ligne);
				}
				else {
					Arbre1.CreaLigne(ligne);
					this.InsererAABRR(Arbre1);
				}
				
			}
			br.close();
		}

		catch (Exception e){
			System.out.println(e.toString());
		}
	}


	public void CreerSAG(int m, int M, int racine, ABRR arbre){
		this.SAG = new AABRR(m,M, racine, arbre);
	}
	
	public void CreerSAD(int m, int M, int racine, ABRR arbre){
		this.SAD = new AABRR(m,M,racine, arbre);
	}
	
	public ABRR getAA(){
		return this.AA;
	}

	public String Parcours(String valeur){
		valeur = valeur + this.min + ':' +  this.Max + ';' ;
		valeur = AA.Parcours(valeur);
		valeur = valeur + System.getProperty("line.separator");
		if (this.SAG != null)
		{
			valeur = this.SAG.Parcours(valeur);
		}
		if (this.SAD != null)
		{
			valeur = this.SAD.Parcours(valeur);
		}
		return valeur;
	}

	public boolean verif(AABRR arbre) {
		boolean miniarbreOk = true, SAGOK = true, SADOK = true;
		if (arbre.getMin() == null || arbre == null) {
			return true;
		}else {	
			if (arbre.AA != null) {
				
				miniarbreOk = arbre.AA.verif(arbre.AA, arbre.min, arbre.Max);
				if (miniarbreOk == false) {
					return false;
				}
			}
			if ((arbre.SAG != null) && (arbre.SAG.getMin() > arbre.getMin()))
				return false;
			if ((arbre.SAD != null) && (arbre.SAD.getMax() < arbre.getMax()))
				return false;
	
			if ((arbre.SAG != null) && (arbre.SAG.getMax() >= arbre.getMin()))
				return false;
			if ((arbre.SAD != null) && (arbre.SAD.getMin() <= arbre.getMax()))
				return false;
			if (arbre.SAG != null) {
				SAGOK = verif(arbre.SAG);
			}
			
			if (arbre.SAD != null) {
				SADOK = verif(arbre.SAD);
			}
			
			return (miniarbreOk && SAGOK && SADOK);
			
			}
		}

	public void Save(String nomFichier, String chemin)
	{
		String Parcours = "";
		try
		{
			File fichier = new File(nomFichier);
			fichier.createNewFile();
		}
		catch (IOException err1)
		{
			System.out.println("Impossible de créer le fichier");
			System.out.println(err1);
		}
		try
		{
			java.io.FileOutputStream Flux = new java.io.FileOutputStream(nomFichier);
			FileWriter fw = new FileWriter(nomFichier);
			Parcours = this.Parcours(Parcours);
			fw.write(Parcours);
			fw.close();
		}
		catch (FileNotFoundException err2)
		{
			System.out.println("Impossible de trouver le fichier");
			System.out.println(err2);
		}
		catch (IOException err3)
		{
			System.out.println("Impossible d'écrire dans le fichier");
			System.out.println(err3);
		}
		File source = new File(nomFichier);
		chemin = chemin + "/" + nomFichier;
		File destination = new File(chemin);
		source.renameTo(destination);
	}

	public void CreaLigne(String ligne)
	{
		boolean sepmM = false, sepAa = false, finpremVal = false;
		String RacMin = "", RacMax = "", premVal = "", valSuivante = "", val = "";
		if (ligne != null)
		{
			for (Integer ii = 0; ii < ligne.length(); ++ii)
			{
				if (sepmM == false && ligne.charAt(ii) != ':' && sepAa == false)
				{
					RacMin = RacMin + ligne.charAt(ii);
				}
				if (sepmM == true && ligne.charAt(ii) != ';' && sepAa == false)
				{
					RacMax = RacMax + ligne.charAt(ii);
				}
				if (sepmM == true && sepAa == true && finpremVal == false && ligne.charAt(ii) != ':')
				{
					premVal = premVal + ligne.charAt(ii);
				}
				if(finpremVal == true && ligne.charAt(ii) != ':' )
				{
					valSuivante = valSuivante + ligne.charAt(ii);
				}
				if (sepmM == false && ligne.charAt(ii) == ':' && sepAa == false)
				{
					sepmM = true;
					this.min =  Integer.parseInt(RacMin);
				}
				if (sepmM == true && ligne.charAt(ii) == ';' && sepAa == false)
				{
					sepAa = true;
					this.Max =  Integer.parseInt(RacMax);
				}
				if(finpremVal == true && (ligne.charAt(ii) == ':' || ligne.charAt(ii) == '\0' || ii == ligne.length()-1 ))
				{
					
					this.getAA().insertABRR((Integer.parseInt(valSuivante)));
					valSuivante = "";
				}
				if (sepmM == true && sepAa == true && finpremVal == false && (ligne.charAt(ii) == ':' || ligne.charAt(ii) == '\0' || ii == ligne.length()-1 ) )
				{
					finpremVal = true;
					this.AA = new ABRR(Integer.parseInt(premVal));
				}
			}
		}
	}

	public void insererEntier(int valeur, AABRR arbre) {
		if (arbre != null && arbre.getMax() != null && arbre.getMin() != null) {
			AABRR sousArbre = getSousArbre(arbre, valeur);
			if(sousArbre == null) {
				System.out.println("Pas d'ABRR correspondant");
			} else {
				ABRR a = sousArbre.getAA();
				if(a == null) {
					sousArbre.setAA(new ABRR(valeur));
				}
				else {
					sousArbre.getAA().insererEntier(sousArbre.getAA(), valeur);
				}
				System.out.println("Entier inséré");
			}
		} else {
			System.out.println("Cet AABRR est vide");
		}
	}

	private AABRR getSousArbre(AABRR arbre, int valeur) {
		if(arbre == null) {
			return null;
		}else {
			if(arbre.getMin() <= valeur && arbre.getMax() >= valeur) {
				return arbre;
			}
			else if( arbre.getMin() > valeur) {
				return getSousArbre(arbre.SAG, valeur);
			}else {
				return getSousArbre(arbre.SAD, valeur);
			}
		}
	}

    public AABRR supprimerEntier(Integer valeur, AABRR arbre) {
		if (arbre != null && arbre.getMin() != null && arbre.getMax() != null) {
			if(valeur >= arbre.min && valeur <= arbre.Max) {
				if(arbre.getAA().getRacine() == valeur && arbre.getAA().getSAD() == null && arbre.getAA().getSAG() == null) {
					arbre.AA = null;
				}
				else {
					ABRR sousArbre = new ABRR(null);
					arbre.AA = sousArbre.supprimerEntier(arbre.getAA(),valeur);					
				}
			}
			else if (arbre.SAG != null && valeur < arbre.min) {
				supprimerEntier(valeur, arbre.SAG);
			}
			else if (arbre.SAD != null && valeur > arbre.Max) {
				supprimerEntier(valeur, arbre.SAD);
			}
			else {
				System.out.println("Cette valeur n'est pas présente dans cet AABRR" );
			}
		}
		return arbre;
    }
    
    
    public AABRR GenererAABRRAleatoire(int nbNoeuds, int max) {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= nbNoeuds ;i++) {
            values.add(this.getRandomInt(1, max));
        }
        Collections.sort(values);
        int arbreMin = 0;
        ArrayList<AABRR> sousArbres = new ArrayList<AABRR>();
        for (Integer arbreMax: values) {
            arbreMin = arbreMin + 1;
            AABRR sousArbre = new AABRR(arbreMin, arbreMax, this.getRandomInt(arbreMin, arbreMax), null );
            sousArbres.add(sousArbre);
            arbreMin = arbreMax + 1;
        }
        AABRR randomAABRR = this.CreerAABRR(sousArbres);
        return randomAABRR;
    }

    private AABRR CreerAABRR(ArrayList<AABRR> sousArbres) {
    	String val = "";
        AABRR arbre = new AABRR(null, null, null, null);
        AABRR arbreComplet = new AABRR(null,null,null,null);
        for (AABRR sousArbre: sousArbres){
           arbre = sousArbre;
           if (arbreComplet.getMin() == null)
           {
        	   arbreComplet = arbre;
           }else {
        	   arbreComplet.InsererAABRR(arbre);
           }
        }
        return arbreComplet;
    }



    private int getRandomInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

	public boolean rechercheEntier(AABRR arbre, int valeur){
		if(arbre != null && arbre.getMin() != null && arbre.getMax() != null){
			if(arbre.getMin() <= valeur && arbre.getMax() >= valeur){
				System.out.println("Entier trouvé dans l'ABRR entre " + arbre.getMin()+ " et "+ arbre.getMax());
				arbre.getAA().rechercheEntier(arbre.getAA(), valeur);
				return true;
			}
			else{
				if(arbre.getMin() > valeur){
					return rechercheEntier(arbre.getSAG(), valeur);
				}
				if(arbre.getMax() < valeur){
					return rechercheEntier(arbre.getSAD(), valeur);
				}
			}

		}
		return false;
	}






	public static void main(String[] args) {
	}
}
