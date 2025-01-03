package _2015;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MezclaParalelo extends RecursiveAction
{
	private static final long serialVersionUID = 1L;
	
	private int[] array;
	private int inicio, fin; 
	
	public MezclaParalelo(int[] array, int inicio, int fin)
	{
		this.array = array;
		this.inicio = inicio;
		this.fin = fin;
	}
	
	@Override
	protected void compute()
	{
		if (fin > inicio)
		{
			int m = (inicio + fin) / 2;
			
			/* Creamos instancias con las partes */
			MezclaParalelo left = new MezclaParalelo(this.array, this.inicio, m);
			MezclaParalelo right = new MezclaParalelo(this.array, m + 1, this.fin);
			
			/* Invocacion en paralelo */
			invokeAll(left, right);
			
			combina(this.array, this.inicio, m, m + 1, this.fin);
		}
	}
	
	private void combina(int[] array, int inicio, int medio, int posteriorAlMedio, int fin)
	{
		
	}

	public static void main (String[] args)
	{
		int n = 10;
		int[] v = generarVector(n);
		imprimirVector(v);
		
		/* Creamos instancia inicial, pool e invocamos */
		MezclaParalelo mezcla = new MezclaParalelo(v, 0, v.length-1);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mezcla); // invocamos
		
		imprimirVector(v);
	}
	
}
