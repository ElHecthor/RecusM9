
import java.net.*;
import java.io.*;

public class ClientTCP2 {

	public static void main (String[] args) throws Exception {

		String host = "localhost";
		int port = 60000;//Port remot
		Socket client = new Socket(host, port);

		//FLUX DE SORTIDA AL SERVIDOR
		PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);

		//FLUX D'ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String eco2 = "";
		String cadena, eco1 = "";
				
		eco2 = fentrada.readLine();
		System.out.println(eco2);
		System.out.println("Introdueix la cadena: ");
		//Lectura teclat
		cadena = in.readLine();
				
		while (cadena != null) {

			//Enviament cadena al servidor
			fsortida.println(cadena);
			//Rebuda cadena del servidor
			eco1 = fentrada.readLine();
			System.out.println("  =>ECO: "+ eco1);
			//Lectura del teclat
			cadena = in.readLine();
		}

		fsortida.close();
		fentrada.close();
		System.out.println("Finalització de l'enviament...");
		in.close();
		client.close();
	}



}
