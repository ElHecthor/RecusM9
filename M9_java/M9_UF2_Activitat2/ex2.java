//Johan Gonzalez
//Hector Navarrete

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ex2 {
	
	

	public static void main(String[] args) throws InterruptedException {
		//mostrem hora actual abans d’execució
		Calendar calendario = new GregorianCalendar();
		System.out.println("Inici: "+ calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) +
				":" + calendario.get(Calendar.SECOND));
		// Crea un pool de 4 fils
		final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(4);
		// Crea objecte Runnable.
		final Runnable ob = new ex2().new ExecutaFil();
		// Programa Fil, s’inicia als 5 segons i després es va executant cada 6 segons
		schExService.scheduleWithFixedDelay(ob, 5, 6, TimeUnit.SECONDS);
		// Espera per acabar 30 segons (para terminar 5 un rango de 29 a 31)
		schExService.awaitTermination(30, TimeUnit.SECONDS);
		// shutdown .
		schExService.shutdownNow();
		System.out.println("Completat");
	}

	// Fil Runnable
	class ExecutaFil implements Runnable {
		@Override
		public void run() {
			Calendar calendario = new GregorianCalendar();
			System.out.println("Hora execució tasca: "+
					calendario.get(Calendar.HOUR_OF_DAY) + ":" +
					calendario.get(Calendar.MINUTE) + ":" +
					calendario.get(Calendar.SECOND));
			System.out.println("Tasca en execució");
			System.out.println("Execució acabada");
		}
	}
}
