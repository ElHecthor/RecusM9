import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		String msj="";
		int port = 60000;//Port remot
		Socket client = new Socket(host, port);
		String cadena, eco = "";
		
		//FLUX DE SORTIDA AL SERVIDOR
		PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
		
		//FLUX D'ENTRADA AL SERVIDOR
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Introduce tu nombre : ");
		//Lectura teclat
		cadena = in.readLine();
		fsortida.println(cadena);
		
		// mensaje al conectarse al servidor
		msj = fentrada.readLine();
		System.out.println(msj);
		
		FilClient filclient = new FilClient(client,fentrada);
		Thread filThread = new Thread(filclient);
		filThread.start();
		
		cadena = in.readLine();
		while (cadena != null) {
			//Enviament cadena al servidor
			fsortida.println(cadena);
			cadena = in.readLine();
		}
		fsortida.close();
		fentrada.close();
		System.out.println("Finalització de l'enviament...");
		in.close();
		client.close();
	}

}
