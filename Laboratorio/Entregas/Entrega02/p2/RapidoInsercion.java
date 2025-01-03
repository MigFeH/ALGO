package p2;

/* Este programa sirve para ordenar n elementos con un algoritmo cuadratico
excepto si el vector inicialmente esta ordenado o casi ordenado,
que es de complejidad lineal  (INSERCION ) */

public class RapidoInsercion
{
	static int []v;

	public static void rapidoInsercion(int[] v, int k) 
	{
		rapirec(v,0,v.length-1,k);
	}

	private static void rapirec (int[] v, int iz, int de, int k)
	{
		int medio;
		if (de>iz) 
		{
			medio = particion(v,iz,de);
			int medio_anterior = medio - 1;
			int medio_posterior = medio + 1;
			
			if(medio_anterior - iz <= k) // tamanio del subvector menor o igual que k
			{
				insercion(v,iz,medio_anterior);
			} else
			{
				rapirec(v,iz,medio_anterior,k);
			}
			
			if(de - medio_posterior <= k) // tamanio del subvector menor o igual que k
			{
				insercion(v,medio_posterior,de);
			} else
			{
				rapirec(v,medio_posterior,de,k);
			}
		}
	}


	/* Intercambia los elementos de las posiciones i, j en el array a
 es O(1)	 */
	private static void intercambiar (int[] v, int i, int j)
	{
		int t;
		t=v[i];v[i]=v[j];v[j]=t;
	}	

	/** Deja el pivote en una posicion tal que a su izquierda no 
    hay ningun mayor, ni a la derecha ningun menor.
    Es un proceso lineal O(n).  */

	private static int particion(int[]v, int iz, int de) 
	{
		int i, pivote;
		int medio = (iz+de)/2;
		intercambiar (v, medio,iz);
		//el pivote es el de centro y se cambia con el primero
		pivote= v[iz]; 
		i= iz;
		for (int s= iz+1; s <= de; s++) 
			if (v[s] <= pivote) 
			{
				i++;
				intercambiar(v,i,s);
			}
		intercambiar(v,iz,i);//se restituye el pivote donde debe estar
		return i; // retorna la posicion en que queda el pivote 
	}

	/* Ordenacion por el metodo de Insercion */
	private static void insercion(int[] a, int inf, int sup) 
	{
		for (int i=inf+1;i<=sup;i++) 
		{
			int x=a[i];
			int j=i-1;
			while (j>=0 && x<a[j])
			{ 
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=x;
		}  // for
	}

	public static void main (String arg [] )
	{
		int n=Integer.parseInt(arg[0]);  //tamanno del problema  
		v = new int[n];
		
		int k=Integer.parseInt(arg[1]); // constante k

		Vector.ordenDirecto(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		rapidoInsercion(v,k);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe (v);

		Vector.ordenInverso(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		rapidoInsercion(v,k);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);

		Vector.ordenAleatorio(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		rapidoInsercion(v,k);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);
	} // fin de main

} 

