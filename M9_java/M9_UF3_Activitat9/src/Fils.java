import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Fils implements Runnable {
	
	//ATRIBUTS
	Socket socket;
	String nomClient;
	String cadena;
	
	PrintWriter fsortida;
	BufferedReader fentrada;
	
	//CONSTRUCTOR
	public Fils(Socket socket,String nomClient){
		this.socket=socket;
		this.nomClient = nomClient;
		fsortida=null;
	}
	
	//METODE DE LA CLASSE RUNNABLE
	@Override
	public void run() {
		
		boolean finalitzar = false;
		
		try {
			fsortida = new PrintWriter(socket.getOutputStream(), true);
			System.out.println(nomClient+" connectat... ");
			
			fsortida.println(nomClient);
			fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//Flux d'entrada del client
			while ((cadena = fentrada.readLine()) != null && !finalitzar) {
				fsortida.println(cadena);
				System.out.println(nomClient);
				System.out.println("Rebent: " + cadena);
				if (cadena.equals("*")){ 
						fentrada.close();
						fsortida.close();
						socket.close();
						finalitzar = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
