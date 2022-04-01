import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author af.garciab
 *
 */
public class Proyecto1 {

	private int pisos;

	private int cuartos;

	private int[][] torre; 

	private static Hashtable<Portales, Portales> contenedor;

	private static Hashtable<Integer, Integer> energia;

	private ArrayList<Portales> portales;

	private int sumaEnergias;

	private static int aproxInfinito;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		contenedor=new Hashtable<Portales, Portales>();
		energia = new Hashtable<Integer, Integer>();

		Proyecto1 instancia = new Proyecto1();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			System.out.println("aqui llegue");
			String line = br.readLine();
			int casos = Integer.parseInt(line);
			line = br.readLine();
			for(int i=0;i<casos && line!=null && line.length()>0 && !"0".equals(line);i++) {

				/* {N,M,P} Un arreglo con los numero necesarios para construir la torre */
				final String [] torre = line.split(" ");
				/* P es necesario para saber cuantos portales hay y usarla despues.
				 * Es una herramienta sorpresa que nos ayudara mas tarde */
				int p = Integer.parseInt(torre[2]);


				line = br.readLine();
				String [] datos = line.split(" ");
				/* {N numeros} Un arreglo con el costo de energia de cada  piso */
				final int[] energia = Arrays.stream(datos).mapToInt(f->Integer.parseInt(f)).toArray();

				/* {Xstart Ystart Xend Yend} Arreglo que indica el comportamiento de los portales*/
				String [] portales = new String [p];
				int j=0;
				while(j < p) {
					line = br.readLine();
					portales[j] = line;
					j++;
				}
				instancia.inicializarTablaCostosEnergia(energia);
				instancia.inicializarTablaPortales(portales);
				instancia.inicializarTorre(torre);
				int respuesta = instancia.calcularComplejidad(portales);
				if(respuesta==aproxInfinito) {
					System.out.println("NO EXISTE");
				}else {
					System.out.println(respuesta);
				}
				line = br.readLine();
			}			
		}

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
		pisos = Integer.parseInt(T[0]);//numero de pisos
		cuartos = Integer.parseInt(T[1]);//numero de cuartos
		int portales = Integer.parseInt(T[2]);//numero de portales
		aproxInfinito = pisos*cuartos*portales*sumaEnergias;
		torre=new int[pisos][cuartos];

		for (int i=0;i<pisos;i++)
		{
			for (int j=0;j<cuartos;j++)
			{
				torre[i][j]=aproxInfinito;

			}
		}	
		torre[0][0]=0;


	}/**
	 * inicializa una tabla de hash con los portales
	 * @param PT = matriz que contiene dentro un String que contiene la posicion inicial y final de cada portal.
	 * */
	public void inicializarTablaPortales( String[] PT)
	{
		portales=new ArrayList<Portales>();
		for (String x: PT)
		{
			String[] posicionesPortales = x.split(" ");
			int[] numeros = Arrays.stream(posicionesPortales).mapToInt(f->Integer.parseInt(f)).toArray();
			Portales portalEntrada = new Portales(numeros[0]-1,numeros[1]-1);
			portales.add(portalEntrada);
			Portales portalSalida = new Portales(numeros[2]-1,numeros[3]-1);
			contenedor.put(portalEntrada, portalSalida);
		} 
	}
	/**
	 * inicializa una tabla de hash con las energias de cada piso.
	 * @param costoEnergia
	 */
	public void inicializarTablaCostosEnergia(int[] E)
	{
		for (int i=0; i<E.length;i++) {
			energia.put(i, E[i]);
			sumaEnergias+=E[i];
		}
	}

	/**
	 * 
	 * @return la ruta mas corta
	 */
	public int calcularComplejidad( String[] PT )
	{
		
		
		for (int i =0; i<pisos;i++)
		{
			for (int j=0; j<cuartos;j++)
			{
				if(i==0&&j==0)
				{
					torre[i][j]=0; //si estamos al inicio
				}
				else {

						if(j==0) //en la esquina de la izquieda
						{
							torre[i][j]=Integer.min(torre[i][j+1]+energia.get(i), torre[i][j]);
						}
						else if(j==cuartos-1) //en la esquina de la derecha
						{
							torre[i][j]=Integer.min(torre[i][j-1]+energia.get(i), torre[i][j]);
						}
						else {//toca revisar si es mas barato de derecha a izquierda o al revez
							torre[i][j]=Integer.min(torre[i][j-1]+energia.get(i), torre[i][j]);
							torre[i][j]=Integer.min(torre[i][j+1]+energia.get(i), torre[i][j]);
						}
						for (int k=0;k<portales.size();k++) {
							Portales a = portales.get(k);
							if(a.getX()==i&&a.getY()==j) {
							Portales portalEnd= contenedor.get(a);
							torre[portalEnd.getX()][portalEnd.getY()]=torre[i][j];
							portales.remove(k);
							}
						}
					}
				}
				
			}
		
		return torre[pisos-1][cuartos-1];
	}

}
