package p5;

import java.io.*;
import java.util.ArrayList;

public class PatternMatching
{
	private static String[][] leerFichero(String fich)
	{	
		String[][] res = null;
		int cont = 0;
		
		String texto = null;
		ArrayList<String> patrones = new ArrayList<>();
		ArrayList<String> resultados = new ArrayList<>();
		
		try
		{
			// f=open(fich,"r");
			FileReader f = new FileReader(new File(fich));
			BufferedReader br = new BufferedReader(f);
			
			String linea = br.readLine();
			
			while(linea != null)
			{
				//System.out.println(linea);
				if(cont == 0)
				{
					texto = linea;
					cont++;
				} else
				{
					String[] argumentos = linea.split(" ");
					//System.out.println(argumentos[0] + "|" + argumentos[1]);
					patrones.add(argumentos[0]);
					resultados.add(argumentos[1]);
				}
				linea = br.readLine();
			}
			
			res = new String[3][patrones.size()];
			
			res[0][0] = texto;
			for(int i = 0; i < res[0].length; i++)
			{
				res[1][i] = patrones.get(i);
				res[2][i] = resultados.get(i);
			}
			
			br.close();
			f.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	private static boolean progDin(String texto, String patron)
	{
		String textoFormateado = " " + texto;
		String patronFormateado = " " + patron;
		
		int n = textoFormateado.length();
		int m = patronFormateado.length();
		
		char[] arrayTexto = textoFormateado.toCharArray();
		char[] arrayPatron = patronFormateado.toCharArray();
		
		boolean[][] mat = new boolean[n][m];
		mat[0][0] = true;
		
		for(int i = 1; i < n; i++)
		{
			for(int j = 1; j < m; j++)
			{
				//System.out.println("(i= "+i+", j= "+j+")");
				// caracteres iguales en posicion i y posicion j
				if(arrayTexto[i] == arrayPatron[j])
				{
					if(mat[i-1][j-1])
					{
						mat[i][j] = true;
					}
				}
				// estamos ante un "?"
				if(arrayPatron[j] == '?')
				{
					if(mat[i][j-1] || mat[i-1][j-1])
					{
						mat[i][j] = true;
					}
				}
				// estamos ante un *
				if(arrayPatron[j] == '*')
				{
					if(mat[i][j-1] || mat[i-1][j-1] || mat[i-1][j])
					{
						mat[i][j] = true;
					}
				}
			}
		}
		return mat[n-1][m-1];
	}

	public static void main(String[] args)
	{
		//System.out.println(args[0]);
		String[][] res = leerFichero("./p5/"+args[0]);
		//String[][] res = leerFichero("test3.txt");
		String texto = res[0][0];
		String[] patrones = res[1];
		String[] resultados = res[2];
		
		System.out.println("Resultados obtenidos del algoritmo de patrones:");
		for(int i = 0; i < patrones.length; i++)
		{
			System.out.println(progDin(texto,patrones[i]) + "\tSolucion: " + resultados[i]);
		}
	}
}
