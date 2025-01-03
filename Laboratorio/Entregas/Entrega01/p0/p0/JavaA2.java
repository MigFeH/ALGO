package p0;

import java.util.ArrayList;

public class JavaA2
{
	public static void main (String arg [] )
	{
		System.out.println("TIEMPOS DEL ALGORITMO A2");
		int n = 10000;
		long t1,t2;                  //obligatoriamente de tipo long para no desbordar
		// la toma de tiempos en Java se ver� con m�s profundidad en la sesi�n siguiente
		
		for(int i = 0; i < 8; i++)
		{
			t1=System.currentTimeMillis();	// milisegundos (sin decimales) 

			listadoPrimos(n);

			t2=System.currentTimeMillis();	

			System.out.println(t1+"///"+t2);

			System.out.println ("n = " + n + " *** tiempo = " + (t2-t1) + " milisegundos");
			
			n = n * 2;
		}

	} // fin de main
	
	/**
	 * Calcula y devuelve todos los primos hasta n
	 */
	private static ArrayList<Integer> listadoPrimos(int n)
	{
		ArrayList<Integer> lSal = new ArrayList<>();
		for(int i = 2; i <= n; i++)
		{
			if(primoA2(i))
				lSal.add(i);
		}
		return lSal;
	}
	
	/**
	 * Devuelve si m es primo o no, mediante otro algoritmo que llamamos A2
	 */
	private static boolean primoA2(int m)
	{
		for(int i = 2; i < m; i++)
		{
			if(m%i == 0)
				return false;
		}
		return true;
	}
}
