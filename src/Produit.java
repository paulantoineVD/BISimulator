
public class Produit {
	
	public String ref_produit;
	
	public String nom;
	public String couleur;
	public String variante;
	public String texture;
	public String conditionnement;
	
	public String etat;
	
	public int numeroDeGare;

	
	public double espace;
	
	private String[] refs;

	public Produit(String r, int nG)
	{
		refs = r.split("-");
		numeroDeGare = nG;
		
		setNom();
		setCouleur();
		setVariante();
		setTexture();
		setConditionnement();
		
		ref_produit = r;
		etat = "A ajouter";
		
		switch(conditionnement)
		{
			case "Sachet": 
				espace = 0.5;
				break;
			case "Boite":
				espace = 1;
				break;
			case "Echantillon":
				espace = 0.05;
				break;
			default:
				espace = 0;
		}
		
		
	}
	
	public void Ajouter()
	{
		etat = "Ajoute";
	}
	
	public String getConditionnement()
	{
		return conditionnement;
	}
	
	//Définition du bonbon
	private void setNom()
	{
		String[] noms = {"Acidofilo", "Bouteille cola", "Brazil pik", "Color Schtroummpf pik", "Langues acides", 
				"London pik", "Miami pik", "Pasta Basta", "Pasta frutta", "Sour snup", "Dragibus", "Carensac", 
				"Fraizibus", "Grain de millet", "Starmint", "Florent violette", "Kimono", "Pain Zan", "Rotella", 
				"Zanoïd", "Fraise tagada", "Croco", "Chamallows", "Polka", "Banane", "Ourson", "Filament"};
		
		nom = noms[Integer.parseInt(refs[0])];
	}
	
	private void setCouleur()
	{
		String[] couleurs = {"Rouge", "Orange", "Jaune", "Vert", "Bleu", "Violet", "Noir", "Marron"};
		
		couleur = couleurs[Integer.parseInt(refs[1])];	
	}
	
	private void setVariante()
	{
		String[] variantes = {"Acide", "Sucre", "Gelifie"};

		
		variante = variantes[Integer.parseInt(refs[2])];	
	}
	
	private void setTexture()
	{
		String[] textures = {"Mou", "Dur"};
		
		texture = textures[Integer.parseInt(refs[3])];	
	}
	
	private void setConditionnement()
	{
		String[] conditionnements = {"Sachet", "Boite", "Echantillon"};
		
		conditionnement = conditionnements[Integer.parseInt(refs[4])];	
	}
	
	
}
