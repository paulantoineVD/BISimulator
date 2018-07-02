
public class Preparation {
	
	public Boucle boucle = new Boucle();
	
	public Preparation()
	{
		//Boucle
		for(int i = 0; i < 40; i++)
		{
			boucle.ajouterGare(new Gare(i));
		}
	}
	
	public void printPreparation()
	{
		for (Gare gare : boucle.gares)
		{
			System.out.println("\t Gare n*" + gare.numero);
			
			for(Emplacement emplacement : gare.emplacements)
			{
				System.out.println("\t \t Emplacement N*" + emplacement.numero);
				System.out.println("\t \t \tNombre de cartons : " + emplacement.getNombresCartons());
			}
		}
	}	
}
