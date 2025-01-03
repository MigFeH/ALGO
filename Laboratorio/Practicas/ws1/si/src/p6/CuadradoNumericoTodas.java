package p6;

import java.io.*;
import java.util.ArrayList;

public class CuadradoNumericoTodas
{
	static final String MARCA_PARA_INDICAR_REEMPLAZO = "R";
	
	static String[][] tablaTotalCargada;
	static String[][] tablaARellenar;
	static String[][] tablaDeOperadores;
	
	static String[] columnaResultados;
	static String[] filaResultados;
	
	static String[][] tablaDeMarcasParaReemplazos;
	
	static int alturaArbol; // tendra el mismo valor que el numero de '?' que haya en el fichero
	
	static ArrayList<String[][]> almacenSolucionesEncontradas = new ArrayList<>(); // almacen en el que guardamos las soluciones que vamos encontrando
	
	// la complejidad depende del numero de '?'
	private static void cuadradoNumerico(int nivel, int indiceColumna)
	{
		if(nivel == tablaARellenar[0].length)
		{
			String[][] aux = new String[tablaARellenar.length][tablaARellenar[0].length];
			
			for(int i = 0; i < tablaARellenar.length; i++)
			{
				for(int j = 0; j < tablaARellenar[0].length; j++)
				{
					aux[i][j] = tablaARellenar[i][j];
				}
			}
			
			if(!isSolucionRepetida(aux))
			{						
				almacenSolucionesEncontradas.add(aux); // hemos encontrado una solucion, la almacenamos
			}
		} else
		{
			// nivel: el indice de la fila en la que hay que operar
			// indiceColumna: el indice de la columna en la que hay que asignar la posibilidad
			
			for(int posibilidad = 0; posibilidad < 10; posibilidad++) // bucle para recorrer posibilidades
			{
				if(tablaARellenar[nivel][indiceColumna].equals("?"))
				{
					tablaARellenar[nivel][indiceColumna] = "" + posibilidad;
				}

				if(comprobaciones(nivel, indiceColumna))
				{
					if(indiceColumna < tablaARellenar[nivel].length-1) // si aun no estamos en la ultima columna de la fila...
					{					
						cuadradoNumerico(nivel, indiceColumna+1); // seguimos procesando la misma fila en la siguiente columna
					} else
					{
						cuadradoNumerico(nivel+1, 0);
					}
				}

				if(tablaDeMarcasParaReemplazos[nivel][indiceColumna] != null)
				{
					tablaARellenar[nivel][indiceColumna] = "?"; // vuelta atras
				} else
				{
					break;
				}
			}
		}
	}
	
	private static boolean isSolucionRepetida(String[][] aux)
	{
		for(int i = 0; i < almacenSolucionesEncontradas.size(); i++)
		{
			if(isMismaSolucion(almacenSolucionesEncontradas.get(i), aux))
			{
				return true;
			}
		}
		return false;
	}
	
	private static boolean isMismaSolucion(String[][] solucion, String[][] aux)
	{
		for(int i = 0; i < solucion.length; i++)
		{
			for(int j = 0; j < solucion[0].length; j++)
			{
				int solucionParseada = Integer.parseInt(solucion[i][j]);
				int resultadoParseado = Integer.parseInt(aux[i][j]);
				
				if(solucionParseada != resultadoParseado)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean comprobaciones(int nivel, int indiceColumna)
	{
		/*
		 * Si estamos en la ultima columna:
		 * 		- Si no estamos en la ultima fila:
		 * 			- retornamos la evaluacion de la fila
		 * 		- else:
		 * 			- Si la evaluacion de la fila y columna es correcta:
		 * 				- retornamos true
		 * 			- else:
		 * 				- retornamos false
		 * 
		 * Si no estamos en la ultima columna:
		 * 		- Si estamos en la ultima fila:
		 * 			- retornamos la evaluacion de la columna
		 * 		- else:
		 * 			- retornamos true
		 */
		
		
		if(indiceColumna == tablaARellenar[nivel].length-1) // si estamos en la ultima columna...
		{
			if(nivel != tablaARellenar.length-1) // si no estamos en la ultima fila...
			{	
				return evaluarFila(nivel);
			} else // si estamos en la ultima fila...
			{
				return (evaluarFila(nivel) && evaluarColumna(indiceColumna));
			}
		} else // si no estamos en la ultima columna...
		{
			if(nivel == tablaARellenar.length-1) // si estamos en la ultima fila...
			{
				return evaluarColumna(indiceColumna);
			} else // si no estamos en la ultima fila...
			{
				return true;
			}
		}
	}

	private static boolean evaluarColumna(int indiceColumna)
	{
		int indexFilaTablaOperadores = 1;
		
		int solucion = Integer.parseInt(filaResultados[indiceColumna]);

		int operando1 = Integer.parseInt(tablaARellenar[0][indiceColumna]);
		String operador = tablaDeOperadores[indexFilaTablaOperadores][indiceColumna];
		indexFilaTablaOperadores+=2;
		int operando2 = Integer.parseInt(tablaARellenar[1][indiceColumna]);

		int res = realizarOperacion(operando1, operador, operando2);

		for(int i = 2; i < tablaARellenar.length; i++)
		{
			if(indexFilaTablaOperadores == tablaDeOperadores.length)
			{
				break;
			}

			operando1 = res;
			operador = tablaDeOperadores[indexFilaTablaOperadores][indiceColumna];
			indexFilaTablaOperadores+=2;
			operando2 = Integer.parseInt(tablaARellenar[i][indiceColumna]);
			res = realizarOperacion(operando1, operador, operando2);
		}

		if(res != solucion)
		{
			return false;
		}

		return true;
	}
	
	private static boolean evaluarFila(int nivel)
	{
		int indexFilaTablaOperadores;

		if(nivel == 0)
		{
			indexFilaTablaOperadores = 0;
		} else if(nivel == tablaARellenar.length-1)
		{
			indexFilaTablaOperadores = tablaDeOperadores.length-1;
		} else
		{
			indexFilaTablaOperadores = 2*nivel;
		}

		int solucion = Integer.parseInt(columnaResultados[nivel]);
		int indexColumnaTablaOperadores = 0;
		
		int operando1 = Integer.parseInt(tablaARellenar[nivel][0]);
		String operador = tablaDeOperadores[indexFilaTablaOperadores][indexColumnaTablaOperadores++];
		int operando2 = Integer.parseInt(tablaARellenar[nivel][1]);

		int res = realizarOperacion(operando1, operador, operando2);

		for(int j = 2; j < tablaARellenar[0].length; j++)
		{	
			if(indexColumnaTablaOperadores == tablaDeOperadores[0].length-1)
			{
				break;
			}

			operando1 = res;
			operador = tablaDeOperadores[indexFilaTablaOperadores][indexColumnaTablaOperadores];
			indexColumnaTablaOperadores++;
			operando2 = Integer.parseInt(tablaARellenar[nivel][j]);
			res = realizarOperacion(operando1, operador, operando2);
		}

		if(res != solucion)
		{
			return false;
		}
		return true;
	}

	private static int realizarOperacion(int operando1, String operador, int operando2)
	{
		switch(operador)
		{
			case "+":
				return operando1 + operando2;
			case "-":
				return operando1 - operando2;
			case "*":
				return operando1 * operando2;
			default:
				if(operando2 == 0)
				{
					return 0;
				} else if(operando1 % operando2 != 0)
				{
					return Integer.MAX_VALUE;
				} else
				{					
					return operando1 / operando2;
				}
		}
	}

	private static void cargarFichero(String fichero)
	{
		int cont = 0;
		int contNivel = 0;
		
		try
		{
			FileReader f = new FileReader(new File(fichero));
			BufferedReader br = new BufferedReader(f);
			
			String linea = br.readLine();
			boolean resultadoFilaAlcanzado = false;
			boolean resultadoColumnaAlcanzado = false;
			
			int indiceFilaTablaTotalCargada = 0;
			int indiceFilaTablaARellenar = 0;
			int indiceFilaTablaDeOperadores = 0;
			int indiceColumnaDeResultados = 0;
			
			int aux = 0;
			
			while(linea != null)
			{
				boolean filaOperadores = false;
				
				if(cont == 0)
				{
					int tamTablaRellenar = Integer.parseInt(linea);
					aux = tamTablaRellenar;
					
					tablaARellenar = new String[tamTablaRellenar][tamTablaRellenar];
					tablaDeMarcasParaReemplazos = new String[tamTablaRellenar][tamTablaRellenar];
					columnaResultados = new String[tamTablaRellenar];
					filaResultados = new String[tamTablaRellenar];
				} else
				{
					int indiceColumnaDeTablaARellenar = 0;
					int indiceColumnaDeTablaDeOperadores = 0;
					
					String[] argumentos = linea.split(" ");
					
					if(cont == 1)
					{
						int tamTablaTotalACargar = argumentos.length;
						tablaTotalCargada = new String[tamTablaTotalACargar][tamTablaTotalACargar];
						tablaDeOperadores = new String[tamTablaTotalACargar-2][aux];
					}
					
					tablaTotalCargada[indiceFilaTablaTotalCargada++] = argumentos;
					
					for(int i = 0; i < argumentos.length; i++)
					{
						if(!resultadoFilaAlcanzado) // si aun no hemos llegado a la ultima fila del fichero...
						{
							if(argumentos[i].equals("="))
							{
								if(i == 0) // si es la penultima fila del fichero...
								{
									resultadoFilaAlcanzado = true;
								} else // si es la penultima fila del fichero...
								{
									resultadoColumnaAlcanzado = true;
								}
							} else
							{
								if(resultadoColumnaAlcanzado) // si hemos pasado el = de la fila i...
								{
									columnaResultados[indiceColumnaDeResultados++] = argumentos[i];
								} else // si aun no hemos pasado el = de la fila i...
								{
									if(!argumentos[i].equals("+") &&
											!argumentos[i].equals("-") &&
											!argumentos[i].equals("*") &&
											!argumentos[i].equals("/")) // no es un operador => es un operando
									{
//										System.out.println("indiceFilaTablaARellenar = " + indiceFilaTablaARellenar + " ; indiceColumnaDeTablaARellenar = " + indiceColumnaDeTablaARellenar);
//										System.out.println("argumentos[i] = " + argumentos[i]);
										
										if(argumentos[i].equals("?")) // la altura del arbol dependera el numero de "?" a reemplazar
										{
//											System.out.println(indiceFilaTablaARellenar + ";" + indiceColumnaDeTablaARellenar);
											tablaDeMarcasParaReemplazos[indiceFilaTablaARellenar][indiceColumnaDeTablaARellenar] = MARCA_PARA_INDICAR_REEMPLAZO;
											contNivel++;
										}
										tablaARellenar[indiceFilaTablaARellenar][indiceColumnaDeTablaARellenar] = argumentos[i];
										indiceColumnaDeTablaARellenar++;
									} else
									{
										if(argumentos[0].equals("+") ||
											argumentos[0].equals("-") ||
											argumentos[0].equals("*") ||
											argumentos[0].equals("/"))
										{
											filaOperadores = true;
										}
//										System.out.println(argumentos[i]);
										tablaDeOperadores[indiceFilaTablaDeOperadores][indiceColumnaDeTablaDeOperadores++] = argumentos[i];
									}	
								}
							}	
						} else // si estamos en la ultima fila del fichero...
						{
							filaResultados = argumentos;
						}
					}
					
					if(!filaOperadores)
					{
						indiceFilaTablaARellenar++;
					}
					indiceFilaTablaDeOperadores++;
					resultadoColumnaAlcanzado = false;
				}
				cont++;
				linea = br.readLine();
			}
			
			alturaArbol = contNivel;
			
			br.close();
			f.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static boolean isOperador(String input)
	{
		if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/"))
		{
			return true;
		}
		return false;
	}

	public static void main(String[] args)
	{
		//cargarFichero("./p6/"+args[0]);
		cargarFichero("test06.txt");
		
		long before = System.currentTimeMillis();
		cuadradoNumerico(0,0); // empezamos desde la raiz
		long after = System.currentTimeMillis();
		
		System.out.println("RESULTADOS");
		for(int i = 0; i < almacenSolucionesEncontradas.size(); i++)
		{	
			System.out.println("\nSolucion " + (i+1));
			mostrarResultados(almacenSolucionesEncontradas.get(i)); // para solucion encontrada la mostramos por pantalla
		}
		
		System.out.println("\nNumero de soluciones encontradas en total: " + almacenSolucionesEncontradas.size());
		System.out.println("Tiempo en milisegundos: " + (after-before));
		
	}

	private static void mostrarResultados(String[][] solucion)
	{
		int indiceFilaTablaRellenada = 0; // si no estamos en fila de operandos incrementamos este valor en la iteracion de la fila i
		int indiceFilaOperadores = 0; // se va a incrementar este valor en cada iteracion de la fila i
		
		int indiceColumnaResultados = 0;
		
		boolean mostrarResultadoFila = false; // cada tabla tiene una fila de resultados
		
		for(int i = 0; i < tablaTotalCargada.length; i++) // cada fila de la tablaTotalCargada...
		{
			String cadena = "\n";
			
			boolean filaOperadores = false; // la tabla tiene una fila de solo operadores (marcaremos la fila de operadores si nos encontramos en ella para mostrar solo operadores)
			
			if(isOperador(tablaTotalCargada[i][0])) // estamos en fila de operadores...
			{
				filaOperadores = true;
				
				for(int j = 0; j < tablaDeOperadores[0].length; j++)
				{
					cadena += "\t" + tablaDeOperadores[indiceFilaOperadores][j] + "\t";
				}
				
			} else if(tablaTotalCargada[i][0].equals("=")) // estamos en fila de "="...
			{
				// fila de "=" => mostrarResultadoFila = true
				mostrarResultadoFila = true;
				
				for(int j = 0; j < filaResultados.length; j++)
				{
					cadena += "\t=\t";
				}
				
			} else if(mostrarResultadoFila) // estamos en la ultima fila de la tablaTotalCargada...
			{
				for(int j = 0; j < filaResultados.length; j++)
				{
					cadena += "\t" + filaResultados[j] + "\t";
				}
				
			} else // estamos en fila normal...
			{
				int indiceColumnaTablaRellenada = 0;
				int indiceColumnaOperadores = 0;
				
				boolean mostrarResultadoColumna = false; // cada fila tiene su resultado que en conjunto forma una columna de resultados
				
				for(int j = 0; j < tablaTotalCargada[0].length; j++) // procesamos cada token de la fila i...
				{
					String argumento = tablaTotalCargada[i][j];
					
					if(argumento.equals("="))
					{
						mostrarResultadoColumna = true;
						
						cadena += "\t=";
						
					} else if(mostrarResultadoColumna)
					{
						cadena += "\t" + columnaResultados[indiceColumnaResultados];
						indiceColumnaResultados++;
					} else // token normal (operador o numero)...
					{
						if(isOperador(argumento))
						{
							cadena += "\t" + tablaDeOperadores[indiceFilaOperadores][indiceColumnaOperadores];
							indiceColumnaOperadores++;
						} else
						{
							cadena += "\t" + solucion[indiceFilaTablaRellenada][indiceColumnaTablaRellenada];
							indiceColumnaTablaRellenada++;
						}
					}
				}
				
			}
			
			System.out.println(cadena);
			
			indiceFilaOperadores++;
			
			if(!filaOperadores) // si no hemos procesado una fila de operadores...
			{
				indiceFilaTablaRellenada++;
			}
		}
	}

}