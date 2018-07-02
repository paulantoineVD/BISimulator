import java.util.ArrayList;

public class Emplacement 
{
	int numero;
	
	ArrayList<Carton> cartons; 
	public int MAX_CARTONS = 7;
	
	public Emplacement(int i)
	{
		numero = i;
		cartons = new ArrayList<Carton>();
	}
	
	public boolean ajouterCarton(Carton carton)
	{
		if (cartons.size() == MAX_CARTONS)
		{
			System.out.println("Nombre max de cartons dans l'emplacement " + numero);
			return false;
		}
		else 
		{
			System.out.println("Carton ajouter dans l'emplacement " + numero);
			cartons.add(carton);
			return true;
		}
	}
	
	public void retirerCarton()
	{
		cartons.remove(0);
	}
	
	//Retourne le nombre de carton dans l'emplacement
	public int getNombresCartons() 
	{
		return cartons.size();
	}
	
}
