import java.net.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP4 {

	public static void main(String[] args) throws IOException {
		
		//Establim un parametre per controlar els clients.
		int parametreConexions = 3;//Integer.valueOf(args[0]);
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		String cadena = "";
		int contadorClients=0;
		
		System.out.println("Esperant connexions... ");
		
		int limit = parametreConexions;
		System.out.println(limit);
		
		PrintWriter fsortida = null;
		BufferedReader fentrada = null;
		
		while(contadorClients < limit){
			//Accepta connexio			
			Socket socket = servidor.accept();
			//Afegim un fil per cada client
			Fils fil = new Fils(socket,"Client " + (contadorClients + 1));
			Thread filThread = new Thread(fil);
			filThread.start();
			
			contadorClients++;
		}
		servidor.close();
		
	}
}

