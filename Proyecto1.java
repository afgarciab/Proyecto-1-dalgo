import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author EQUIPO
 *
 */
public class Proyecto1 {

	private int[][] torre; 

	private static Hashtable<Portales, Portales> contenedor;
	
	private static Hashtable<Integer, Integer> energia;
	
	private int sumaEnergias;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		contenedor=new Hashtable<Portales, Portales>();
		energia = new Hashtable();
		
		//debo hacer la carga de datos.
		//luego se pone la funcion inicializarTablaCostosEnergia con su parametro.
		//luego se pone la funcion inicializarPortales con su parametro.
		//luego se pone la funcion inicializarTorre.
		//luego se pone la funcion calcularComplejidad.
	}
	/**
	 * 
	 * @param T = arreglo que contiene en [0] el numero de pisos,[1]num de cuartos y [2] numero Portales
	 */
	public void inicializarTorre(String[] T)
	{
		int pisos = Integer.parseInt(T[0]);//numero de pisos
		int cuartos = Integer.parseInt(T[2]);//numero de cuartos
		int portales = Integer.parseInt(T[0]);//numero de portales
		int aproxInfinito = pisos*cuartos*portales*sumaEnergias;
		for (int i =1;i<=pisos;i++)
		{
			for (int j=1;j<=cuartos;j++)
			{
				torre[i][j]=aproxInfinito;
				if(i==1&&j==1)
				{
					torre[i][j]=0;
				}

			}
		}

	}/**
	 * inicializa una tabla de hash con los portales
	 * @param PT = matriz que contiene dentro un String que contiene la posicion inicial y final de cada portal.
	 * */
	public void inicializarPortales( String[] PT)
	{
		for (String x: PT)
		{
			String[] posicionesPortales = x.split(",");
			int[] numeros = Arrays.stream(posicionesPortales).mapToInt(f->Integer.parseInt(f)).toArray();
			Portales portalEntrada = new Portales(numeros[0],numeros[1]);
			Portales portalSalida = new Portales(numeros[2],numeros[3]);
			contenedor.put(portalEntrada, portalSalida);
		} 
	}
	/**
	 * inicializa una tabla de hash con las energias de cada piso.
	 * @param costoEnergia
	 */
	public void inicializarTablaCostosEnergia(int[] E)
	{
		for (int i=1; i<=E.length;i++) {
		energia.put(i, E[i]);
		sumaEnergias+=E[i];
		}
	}

	/**
	 * 
	 * @return la ruta mas corta
	 */
	public int calcularComplejidad(  )
	{
		return 0;
	}

}
