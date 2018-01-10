import java.io.*;
public class AABRR {
	private Integer min; //minimum
	private Integer Max; // Maximum
	private ABRR AA;
	private AABRR SAG;
	private AABRR SAD;
	/**
	 * @param args
	 */
	public AABRR(Integer m, Integer M, Integer racine){
		super();
		this.min = m;
		this.Max = M;
		this.AA = new ABRR(racine);
	}
	
	public ABRR getAA(){
		return this.AA;
	}
	
	public Integer getmin() {
		return this.min;
	}
	
	public Integer getMax() {
		return this.Max;
	}
	
	public void CreerSAG(Integer min, Integer max, Integer rac)
	{
		this.SAG = new AABRR(min, max, rac);
	}
	
	public void CreerSAD(Integer min, Integer max, Integer rac)
	{
		this.SAD = new AABRR(min, max, rac);
	}
	
	public AABRR getSAG()
	{
		return this.SAG;
	}
	
	public AABRR getSAD()
	{
		return this.SAD;
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
		if (ArbreTest.getMax() <= this.getmin())
		{
			if (this.SAG == null)
			{
				CreerSAG(ArbreTest.getmin(), ArbreTest.getMax(), ArbreTest.getAA().getRacine());
				
			}else {
				this.getSAG().insertAABRR(ArbreTest);
			}
		}else 
		{
			if (this.SAD == null)
			{
				CreerSAD(ArbreTest.getmin(), ArbreTest.getMax(), ArbreTest.getAA().getRacine());
			}else {
				this.getSAD().insertAABRR(ArbreTest);
			}
		}
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
		AABRR GrandArbre = new AABRR("/home/bastien/eclipse-workspace/Arbre/src/coucou.txt");
		String val = "";
		GrandArbre.Save("Parcours.txt", "/home/bastien/eclipse-workspace/Arbre/src");
		val = GrandArbre.Parcours(val);
		System.out.println(val);
	}

}
