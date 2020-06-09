import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FilClient implements Runnable {
	Socket socket;
	String nombreCliente,cadena;
	BufferedReader fentrada;
 
    public FilClient(Socket socket,BufferedReader fentrada) throws IOException {
        this.socket = socket;
        this.fentrada = fentrada;
    }

    public void run() {
    	boolean inicio = true;
        while (inicio) {
            try {
            	String codigo = fentrada.readLine();
                System.out.println(codigo);

            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
                inicio = false;
            }
            }
        }
    }
