import java.io.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ClientFTP2 {
	public static void main (String[] args) {

		Scanner scan = new Scanner(System.in);

		//Servidor FTP
		FTPClient client = new FTPClient();

		// Servidores
		//  ftp.urv.es
		//  192.168.1.107
		System.out.print("Especifique servidor: ");
		String ServerFTP = scan.nextLine();
		System.out.println("Ens connectem al servidor: "+ServerFTP);

		//Usuari FTP
		//  anonymous : guest
		//  alumne : 1234
		System.out.print("Usuario: ");
		String usuari = scan.nextLine();
		System.out.print("Contraseña: ");
		String contrasenya = scan.nextLine();

		try {
			client.connect(ServerFTP);
			boolean login = client.login(usuari, contrasenya);
			if (login)
				System.out.println("Login correcte... ");

			else {
				System.out.println("Login incorrecte... ");
				client.disconnect();
				System.exit(1);
			}

			System.out.println("Directori actual: "+client.printWorkingDirectory());
			FTPFile[] files = client.listFiles();
			System.out.println("Fitxers al directori actual: "+files.length);

			//Array par a visualitzar el tipus de fitxer
			String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};

			for (int i=0; i<files.length; i++) {
				System.out.println("\t"+files[i].getName()+"=>"+tipus[files[i].getType()]);

			}

			// :DDD
			System.out.println("Menu: ");
			System.out.println("(1) Crear Fitxer ");
			System.out.println("(2) Eliminar Fitxer ");
			System.out.println("(3) Pujar Fitxer ");
			System.out.println("(4) Rename Fitxer ");
			String respuesta = scan.nextLine();

			if (respuesta.equals("1")) {
				CreaFitxerFTP(client, respuesta, respuesta, respuesta, scan);
			} else if (respuesta.equals("2")) {
				EliminaFitxerFTP(client, respuesta, respuesta, respuesta, scan);
			} else if (respuesta.equals("3")) {
				PujarFitxerFTP(client, respuesta, respuesta, respuesta, scan);
			} else if (respuesta.equals("4")) {
				RenameFitxerFTP(client, respuesta, respuesta, respuesta, scan);
			} else {
				System.out.println("Opció incorrecte... ");
				System.out.println("Desconectant del servidor");
				client.disconnect();
				System.exit(1);
			}
			// EditADO FEDE :()

			boolean logout = client.logout();

			if (logout)

				System.out.println("Logout del servidor FTP... ");

			else

				System.out.println("Error en fer un logout... ");

			client.disconnect();
			System.out.println("Desconnectat... ");

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}

	public static void CreaFitxerFTP(FTPClient client, String Servidor, String usuari, String contrasenya, Scanner scan) {
		{

			try {
				//Crea el directori
				System.out.print("Adreça directori nou: ");
				String adreca = scan.nextLine();

				if (client.makeDirectory(adreca)) {

					System.out.println("Directori creat... ");
					client.changeWorkingDirectory(adreca);

				} else {

					System.out.println("No s'ha pogut crear el directori... ");

				}

				client.logout();
				client.disconnect();
				System.out.println("L'operació ha acabat... ");

			} catch (IOException ioe) {

				ioe.printStackTrace();

			}
		}
	}

	public static void EliminaFitxerFTP(FTPClient client, String Servidor, String usuari, String contrasenya, Scanner scan) {

		try {

			//Elimina el fitxer
			System.out.print("Adreça + fitxer a eliminar: ");
			String adreca = scan.nextLine();

			if (client.deleteFile(adreca)) {

				System.out.println("Fitxer eliminat... ");

			} else {

				System.out.println("No s'ha pogut eliminar el fitxer... ");

			}

			client.logout();
			client.disconnect();
			System.out.println("L'operació ha acabat... ");

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}

	public static void PujarFitxerFTP(FTPClient client, String Servidor, String usuari, String contrasenya, Scanner scan) {

		try {
			boolean login = client.login(usuari, contrasenya);
			System.out.print("Adreça: ");
			String adreca = scan.nextLine();

			if (login) {

				client.changeWorkingDirectory(adreca);
				client.setFileType(FTP.BINARY_FILE_TYPE);

				//Stream d'entrada amb el fitxer que es vol pujar
				System.out.print("Directori + fitxer que es vol pujar: ");
				String fichero1 = scan.nextLine();
				System.out.print("Nom fitxer: ");
				String fichero2 = scan.nextLine();
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(fichero1));
				client.storeFile(fichero2, in);

				in.close();
				client.logout();
				client.disconnect();
				System.out.println("L'operació ha acabat... ");

			}

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}

	public static void RenameFitxerFTP(FTPClient client, String Servidor, String usuari, String contrasenya, Scanner scan) {

		try {
			//Descarregar el fitxer
			System.out.print("Adreça: ");
			String adreca = scan.nextLine();
			client.changeWorkingDirectory(adreca);

			System.out.print("Fichero a renombrar: ");
			String fichero1 = scan.nextLine();
			System.out.println("Renombrar a:");
			String fichero2 = scan.nextLine();
			if (client.rename(fichero1, fichero2)) {

				System.out.println("Fitxer reanomenat... ");

			} else {

				System.out.println("No s'ha pogut reanomenat el fitxer... ");

			}

			client.logout();
			client.disconnect();
			System.out.println("L'operació ha acabat... ");

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}
}