import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	String cadena = "";
	public static int conexiones=0;
	static int limiteConexiones;
	public static String[] nombreClientes;
	public static Socket[] arraySockets;

	public static void main(String[] args) throws IOException {
		int numPort = 60000;
		limiteConexiones = 2;
		nombreClientes = new String[limiteConexiones];
		arraySockets = new Socket[limiteConexiones];
		ServerSocket servidor = new ServerSocket(numPort);
		
		System.out.println("Numero N limite Conexiones");
		System.out.println(limiteConexiones);	

		PrintWriter fsortida = null;
		BufferedReader fentrada = null;
		FilServidorEscriure filServ = new FilServidorEscriure();
		Thread filServThread = new Thread(filServ);
		filServThread.start();
		
		while(conexiones < limiteConexiones){
			System.out.println("Esperant connexió...");	
			Socket socket = servidor.accept();

			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String nom =fentrada.readLine();
			System.out.println(nom);
			
			nombreClientes[conexiones] = nom;
			arraySockets[conexiones] = socket;

			Fil fil = new Fil(conexiones);
			Thread filThread = new Thread(fil);
			filThread.start();

			conexiones++;
		}
		servidor.close();
	}
}
