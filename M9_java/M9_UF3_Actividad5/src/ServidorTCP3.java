import java.net.*;
import java.io.*;

public class ServidorTCP3 implements Runnable{
	Socket client;
	String cadena = "";
	ServerSocket server;
	static int numClient;

	public ServidorTCP3(Socket clientConnectat, ServerSocket server) {
		this.client = clientConnectat;
		this.server = server;
		this.numClient ++;
	}

	public static void main(String[] args) throws IOException {
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		Runnable[] arrayRunnable = new Runnable[3];
		Thread[] arrayThread = new Thread[3];

		for (int i = 0; i < arrayRunnable.length; i++) {
			Socket clientConnectat = servidor.accept();

			// Runnable
			arrayRunnable[i] = new ServidorTCP3(clientConnectat, servidor);

			// Thread
			arrayThread[i] = new Thread(arrayRunnable[i]);
			arrayThread[i].start();
		}

	}

	@Override
	public void run() {

		try {
			//FLUX DE SORTIDA AL CLIENT
			PrintWriter fsortida = new PrintWriter(this.client.getOutputStream(), true);
			//FLUX D'ENTRADA DEL CLIENT
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));

			fsortida.println("Client: " + this.numClient);
			while ((cadena = fentrada.readLine()) != null) {
				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")) break;
			}

			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexió... ");
			fentrada.close();
			fsortida.close();
			this.client.close();
			this.server.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}