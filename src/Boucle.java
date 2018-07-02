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
	
	
}
