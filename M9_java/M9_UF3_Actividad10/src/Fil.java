import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Fil implements Runnable {
	String nombreCliente;
	String cadena;
	static PrintWriter fsortida;
	BufferedReader fentrada;
	int numConexion;
	public Fil(int numConexion) throws IOException{
		fsortida=null;
		this.numConexion = numConexion;
		this.nombreCliente = Servidor.nombreClientes[this.numConexion];
		fsortida = new PrintWriter(Servidor.arraySockets[this.numConexion].getOutputStream(), true);
		System.out.println(nombreCliente+" connectat... ");
		fsortida.println(nombreCliente);
		fentrada = new BufferedReader(new InputStreamReader(Servidor.arraySockets[this.numConexion].getInputStream()));
	}

	@Override
	public void run() {
		boolean finHilo=false;
		try {
			cadena = fentrada.readLine();
			while (cadena != null && !finHilo) {
				if(cadena.charAt(0) == '1'){
					for (int i = 0; i < Servidor.nombreClientes.length; i++) {
						if(Servidor.nombreClientes[i].equals(cadena.split("_")[1].toString())){
							fsortida = new PrintWriter(Servidor.arraySockets[i].getOutputStream(), true);
							fsortida.println("["+nombreCliente+"]: " + cadena.split("_")[2].toString());
						}
					}
				}else{
				enviarATodos(cadena,nombreCliente);
				}
				System.out.println(nombreCliente);
				System.out.println("Rebent: "+cadena);
				if (cadena.equals("*")){ 
						fentrada.close();
						fsortida.close();
						finHilo = true;
					}else{
						cadena = fentrada.readLine();
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void enviarATodos(String mensaje,String nom) throws IOException{
		System.out.println(nom+" enviando a todos...");
		for (int i = 0; i < Servidor.arraySockets.length; i++) {
			if(Servidor.arraySockets[i] != null){
				fsortida = new PrintWriter(Servidor.arraySockets[i].getOutputStream(), true);
				if(mensaje.charAt(0) == 'S'){
					fsortida.println("Servidor --> " + mensaje.substring(1));
				}else{
				fsortida.println("["+nom+"]: " + mensaje);
				}
			}
		}
	}
}
