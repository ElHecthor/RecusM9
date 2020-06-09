import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FilServidorEscriure implements Runnable {
	private String cadena;
	private BufferedReader in;
	public FilServidorEscriure() {
		in =  new BufferedReader(new InputStreamReader(System.in));
	}
	@Override
	public void run(){ 
	boolean finHilo=false;
	while(!finHilo){
		try {
			cadena = in.readLine();
		
		while (cadena != null) {
			System.out.println("->Servidor: "+cadena);
			Fil.enviarATodos("S"+cadena,"Servidor");
			cadena = in.readLine();
		}
		in.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
