import java.util.*;
import java.util.concurrent.*;

public class ex1 {

	static class Multiplicacio implements Callable<Integer> {
		private int operador1;
		private int operador2;
		
		public Multiplicacio(int operador1, int operador2) {
			this.operador1 = operador1;
			this.operador2 = operador2;
			}
			
		@Override
		public Integer call() throws Exception {
			return operador1 + operador2; // Suma
			}
		}

	public static void main(String[] args) throws
		InterruptedException, ExecutionException {
			ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5); // Lanza 5 hilos
			List<Multiplicacio> llistaTasques= new ArrayList<Multiplicacio>();
			for (int i = 0; i < 25; i++) { // Lanza 25 calculos aleatorios
				Multiplicacio calcula = new Multiplicacio((int)(Math.random()*10), (int)(Math.random()*10));
				llistaTasques.add(calcula);
				}
			List <Future<Integer>> llistaResultats;
			llistaResultats = executor.invokeAll(llistaTasques);
			
			executor.shutdown();
			
			for (int i = 0; i < llistaResultats.size(); i++) {
				Future<Integer> resultat = llistaResultats.get(i);
				try {
					System.out.println("Resultat tasca "+i+ " és:" +
					resultat.get());
				} 
				catch (InterruptedException | ExecutionException e)
					{
					}
				}
		}
	}
