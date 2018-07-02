import java.util.ArrayList;

public class Gare 
{
	public int numero;
	
	ArrayList<Emplacement> emplacements;
	public int MAX_EMPLACEMENT = 100;
	
	public Gare(int i)
	{
		this.numero = i;
		emplacements = new ArrayList<Emplacement>();
		
		for (i = 0; i < 100; i++)
		{
			emplacements.add(new Emplacement(i));
		}
	}
}
