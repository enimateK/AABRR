
public class AABRR extends Arbre {
	private int min; //minimum
	private int Max; // Maximum
	private ABRR AA;
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
	
	public ABRR 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
