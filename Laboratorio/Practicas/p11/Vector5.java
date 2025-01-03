package p11;

public class Vector5
{
	static int []v;

	public static void main (String arg [] )
	{
		int repeticiones = Integer.parseInt (arg[0]);	// veces que se repite la operación

		long t1,t2;

		System.out.println("repeticiones = "+ repeticiones);
		System.out.println ("Tamaño\tTiempo");   
		for ( int n= 10000; n<= 81920000; n*=2) // n se va duplicando   
		{
			v = new int [n] ;
			Vector1.rellena (v);

			t1=System.currentTimeMillis();

			int [] m = new int [2];
			// hay que repetir todo el proceso a medir (lo que que estaba entre t1 y t2) 
			for (int r= 1; r<=repeticiones; r++)
			{  	
				Vector1.maximo (v,m);
			}

			t2=System.currentTimeMillis();
			System.out.println (n+"\t"+(t2-t1));   

		} // fin de for
			
		System.out.println("\nFin de la medición de tiempos *****");

	} // fin de main
}
