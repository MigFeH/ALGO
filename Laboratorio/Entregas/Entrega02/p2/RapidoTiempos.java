package p2;

/* Esta clase mide tiempos del metodo Rapido
para los 3 supuestos de orden incial (ordenado, inverso y aleatorio) */

public class RapidoTiempos
{

static int []v;

public static void main (String arg [])
{
long t1,t2;
String opcion=arg[0];
	
for (int n= 250000; n<=1000000000; n*= 2)
{
	v=new int [n];

   	if (opcion.compareTo("ordenado")==0)
		Vector.ordenDirecto(v);
   	else if (opcion.compareTo("inverso")==0)
		Vector.ordenInverso(v);
   	else
		Vector.ordenAleatorio(v);
		
  	t1 = System.currentTimeMillis();
	  
	Rapido.rapido(v);
         
	t2 = System.currentTimeMillis();

  	System.out.println (n+"\t"+(t2-t1));
}
}
}
