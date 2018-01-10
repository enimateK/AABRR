package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



public class AABRR extends Arbre {
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
	/**
	 * @param args
	 */
	public AABRR(int m, int M, int racine){
		super();
		this.min = m;
		this.Max = M;
		this.AA = new ABRR(racine);

	}

	public AABRR() {
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
		valeur = valeur + "_";
		if (this.SAG != null)
		{
		valeur = this.SAG.Parcours(valeur);
		}
		if (this.SAD != null) 
		{
		valeur = this.SAD.Parcours(valeur);
		}
		//super.Parcours(valeur);
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

	public void RandomCreationG(int nbNoeuds, int max, int min) {
		System.out.println(this.AA.getRacine());
		if (nbNoeuds > 0) {
			int nombreAleatoire = min + (int) (Math.random() * ((max - min) + 1));
			this.CreerSAG(min, max, nombreAleatoire);
			this.SAG.RandomCreationG(nbNoeuds - 1, max, nombreAleatoire);
			int nombreAleatoire2 = min + (int) (Math.random() * ((max - min) + 1));
			this.CreerSAD(min, max, nombreAleatoire2);
			this.SAD.RandomCreationD(nbNoeuds - 1, nombreAleatoire2, nombreAleatoire);
		}
	}

	public void RandomCreationD(int nbNoeuds, int max, int min){
		System.out.println(this.AA.getRacine());
		if (nbNoeuds > 0) {
			int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));
			this.CreerSAG(min, max, nombreAleatoire);
			this.SAG.RandomCreationG(nbNoeuds - 1, max, nombreAleatoire);
			int nombreAleatoire2 = min + (int)(Math.random() * ((max - min) + 1));
			this.CreerSAD(min, max, nombreAleatoire2);
			this.SAD.RandomCreationD(nbNoeuds - 1, nombreAleatoire2, 1);
		}
	}

	private boolean checkAABRR(AABRR abr) {
		//Teste si l'arbre est vide
		if (abr == null)
			return true;

		//Teste si l'arbre ABR' de a est valide
		if(!abr.AA.checkABRR(abr.min, abr.max))
			return false;

		//Teste si les fils droit et gauche de a sont à la bonne place
		if ((abr.SAG != null) && (abr.sag.getMin() > abr.getMin()))
			return false;
		if ((abr.SAD != null) && (abr.sad.getMax() < abr.getMax()))
			return false;

		//Teste si les intervalles des fils sont disjoints
		if ((abr.sag != null) && (abr.sag.getMax() >= abr.getMin()))
			return false;
		if ((abr.sad != null) && (abr.sad.getMin() <= abr.getMax()))
			return false;

		return (checkAABRR(abr.sag) && checkAABRR(abr.sad));
	}
	
//	public void Save(String nomFichier)
//	{
//		String Parcours = "";
//		File f = new File("Arbre AABRR.txt");
//		try
//		{
//			java.io.File fichier = new java.io.File(nomFichier);
//			fichier.createNewFile();
//		}
//		catch (IOException err1)
//		{
//			System.out.println("Impossible de créer le fichier");
//			System.out.println(err1);
//		}
//		try
//		{
//		java.io.FileOutputStream Flux = new java.io.FileOutputStream(nomFichier); // Doit être utilisé dans un bloc TRY
//		}
//		catch (FileNotFoundException err2)
//		{
//		System.out.println("Impossible de trouver le fichier");
//		System.out.println(err2);
//		}
//
//		Parcours = this.Parcours(Parcours);
//		Parcours.replace("_", System.getProperty("line.separator"));
//		Flux.close(); // Toujours dans un TRY
//		catch (IOException err3)
//		{
//		}
//
//
//	}
	
	public static void main(String[] args) {
		AABRR GrandArbre = new AABRR(50 ,75, 60);
		String val = "";

		GrandArbre.CreerSAD(78, 80, 80);
		GrandArbre.CreerSAG(9, 22, 9);
		
		//val = GrandArbre.Save();
		System.out.println(val);

	}

}
