import java.util.ArrayList;

public class Boucle {
	
	ArrayList<Gare> gares;
	
	public Boucle()
	{
		gares = new ArrayList<Gare>();
	}
	
	public void ajouterGare(Gare gare)
	{
		if(this.gares.size() < 40)
		{
			this.gares.add(gare);
		}
	}
	
	public Gare getGareNumero(int numero)
	{
		for(Gare g : gares)
		{
			if (g.numero == numero)
			{
				return g;
			}
		}
		
		return null;
	}
	
}
