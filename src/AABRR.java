import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AABRR extends Arbre {
	private int min; //minimum
	private int Max; // Maximum
	private ABRR AA;
	private AABRR SAG;
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
	
	public void Save(String nomFichier)
	{
		String Parcours = "";
		File f = new File("Arbre AABRR.txt");
		try
		{
			java.io.File fichier = new java.io.File(nomFichier);
			fichier.createNewFile();
		}
		catch (IOException err1)
		{
			System.out.println("Impossible de créer le fichier");
			System.out.println(err1);
		}
		try
		{
		java.io.FileOutputStream Flux = new java.io.FileOutputStream(nomFichier); // Doit être utilisé dans un bloc TRY
		}
		catch (FileNotFoundException err2)
		{
		System.out.println("Impossible de trouver le fichier");
		System.out.println(err2);
		}

		Parcours = this.Parcours(Parcours);
		Parcours.replace("_", System.getProperty("line.separator"));
		Flux.close(); // Toujours dans un TRY
		catch (IOException err3)
		{
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
