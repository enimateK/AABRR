package src;

import java.io.*;
import java.util.*;



public class AABRR {
	private Integer min; //minimum
	private Integer Max; // Maximum
	private ABRR AA;
	private AABRR SAG;

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

	private AABRR SAD;

	public AABRR(Integer m, Integer M, Integer racine){
		super();
		this.min = m;
		this.Max = M;
		this.AA = new ABRR(racine);
	}

	public AABRR() {
	}

	public AABRR(String chemin)
	{
		AABRR Arbre1 = new AABRR (null, null, null);
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
					this.insertAABRR(Arbre1);
				}
			}
			br.close();
		}

		catch (Exception e){
			System.out.println(e.toString());
		}
	}


	public void CreerSAG(int m, int M, int racine){
		this.SAG = new AABRR(m,M,racine);
	}
	
	public void CreerSAD(int m, int M, int racine){
		this.SAD = new AABRR(m,M,racine);
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
	public AABRR InsererAABRR(AABRR arbre, AABRR sousArbre) {
		if (arbre == null) {
			arbre = sousArbre;
		} else {
			if (sousArbre.getMax() <= arbre.getMin()){
				arbre.InsererAABRR(arbre.getSAG(), sousArbre);
			} else {
				arbre.InsererAABRR(arbre.getSAD(), sousArbre);
			}
		}

		return arbre;
	}

	private boolean checkAABRR(AABRR abr) {
		//Teste si l'arbre est vide
		if (abr == null)
			return true;

		//Teste si l'arbre ABR' de a est valide
		if(!abr.AA.checkABRR(abr.AA, abr.min, abr.Max))
			return false;

		//Teste si les fils droit et gauche de a sont à la bonne place
		if ((abr.SAG != null) && (abr.SAG.getMin() > abr.getMin()))
			return false;
		if ((abr.SAD != null) && (abr.SAD.getMax() < abr.getMax()))
			return false;

		//Teste si les intervalles des fils sont disjoints
		if ((abr.SAG != null) && (abr.SAG.getMax() >= abr.getMin()))
			return false;
		if ((abr.SAD != null) && (abr.SAD.getMin() <= abr.getMax()))
			return false;

		return (checkAABRR(abr.SAG) && checkAABRR(abr.SAD));
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

	public void insertAABRR(AABRR ArbreTest)
	{
		if (ArbreTest.getMax() <= this.getMin())
		{
			if (this.SAG == null)
			{
				CreerSAG(ArbreTest.getMin(), ArbreTest.getMax(), ArbreTest.getAA().getRacine());

			}else {
				this.getSAG().insertAABRR(ArbreTest);
			}
		}else
		{
			if (this.SAD == null)
			{
				CreerSAD(ArbreTest.getMin(), ArbreTest.getMax(), ArbreTest.getAA().getRacine());
			}else {
				this.getSAD().insertAABRR(ArbreTest);
			}
		}
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
					this.AA = new ABRR( Integer.parseInt(premVal));
				}
			}
		}
	}


	public static void main(String[] args) {
		AABRR GrandArbre = new AABRR(50 ,75, 60);
		String val = "";

		GrandArbre.CreerSAD(78, 80, 80);
		GrandArbre.CreerSAG(9, 22, 9);
		
		//val = GrandArbre.Save();
		System.out.println(val);

	}

}
