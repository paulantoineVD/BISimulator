import java.util.ArrayList;

public class Gare 
{
	public int numero;
	public ArrayList<String> ref_produits = new ArrayList<String>();
		
	public Gare(int i)
	{
		this.numero = i;		
	}
	
	//Dit si oui ou non la reference est dans la gare
	public boolean referenceEstDansLaGare(String reference)
	{
		for(String ref : ref_produits)
		{
			if(ref.compareToIgnoreCase(reference) == 0)
			{
				return true;
			}
		}
		
		return false;
	}
}
