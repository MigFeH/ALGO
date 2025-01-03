package p3;

public class Mezcla
{
	static int[] v;
	
	public static void mezcla(int[] v)
	{
		int[] aux = new int[v.length];
		mezclarec(v,aux,0,v.length-1);
	}
	
	private static void mezclarec(int[] a, int[] b, int iz, int de)
	{
		int m;
		if(iz<de)
		{
			m = (iz+de)/2;
			mezclarec(a,b,iz,m);
			mezclarec(a,b,m+1,de);
			combinar(a,b,iz,m,m+1,de);
		}
	}
	
	private static void combinar(int[] a, int[] b, int p1, int u1, int p2, int u2)
	{
		int i1,i2,k;
		
		if((p1>u1) || (p2>u2))
		{
			return;
		}
		
		for(k = p1; k <= u2; k++)
		{
			b[k] = a[k]; // (* volcamos a en b *)
		}
		i1 = p1;
		i2 = p2; // (* cada indice se encarga de un subvector *)
		
		for(k = p1; k <= u2; k++)
		{
			if(b[i1] <= b[i2])
			{
				a[k] = b[i1];
				if(i1 < u1)
				{
					i1++;
				} else
				{
					b[i1] = Integer.MAX_VALUE;
				}
			} else
			{
				a[k] = b[i2];
				if(i2 < u2)
				{
					i2++; 
				} else
				{
					b[i2] = Integer.MAX_VALUE;
				}
			}
		}
	}
	
	public static void main (String arg [])
	{
		int n=Integer.parseInt(arg[0]);  //tamanno del problema  
		v = new int[n];

		Vector.ordenDirecto(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		mezcla(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);

		Vector.ordenInverso(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		mezcla(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);

		Vector.ordenAleatorio(v);
		System.out.println ("VECTOR A ORDENAR ES");
		Vector.escribe(v);	
		mezcla(v);
		System.out.println ("VECTOR ORDENADO ES");
		Vector.escribe(v);
	} // fin de main
}
