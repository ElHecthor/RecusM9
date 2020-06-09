package ex5;

class TiradaDaus {
    private int tiradaDau;

    public TiradaDaus (int e) {
        tiradaDau=e;
    }

    public int getSumaTirada() {
        return tiradaDau;
    }

    public void setSumaTirada(int e) {
        tiradaDau += e;
    }
}


public class JoinFils implements Runnable {

	private TiradaDaus xobj;

	public JoinFils(TiradaDaus m) {
		xobj=m;
	}
	public void run(){
		try{
			Thread.sleep(1000);
			int resultatDau=(int) (Math.random()*6) + 1;
			xobj.setSumaTirada(resultatDau);
			System.out.println("Tirada fil "+Thread.currentThread().getName() +
					": " +resultatDau);
		}catch (InterruptedException e){
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TiradaDaus ans=new TiradaDaus(0);

		JoinFils obj1 = new JoinFils(ans);
        JoinFils obj2 = new JoinFils(ans);
        JoinFils obj3 = new JoinFils(ans);
        JoinFils obj4 = new JoinFils(ans);
        JoinFils obj5 = new JoinFils(ans);
        JoinFils obj6 = new JoinFils(ans);
        JoinFils obj7 = new JoinFils(ans);
        JoinFils obj8 = new JoinFils(ans);
        JoinFils obj9 = new JoinFils(ans);
        JoinFils obj10 = new JoinFils(ans);
        JoinFils obj11 = new JoinFils(ans);
        JoinFils obj12 = new JoinFils(ans);
        JoinFils obj13 = new JoinFils(ans);
        JoinFils obj14 = new JoinFils(ans);
        JoinFils obj15 = new JoinFils(ans);
        
        Thread fil_1 = new Thread(obj1);
        fil_1.setName("Dau 1");
        Thread fil_2 = new Thread(obj2);
        fil_2.setName("Dau 2");
        Thread fil_3 = new Thread(obj3);
        fil_3.setName("Dau 3");
        Thread fil_4 = new Thread(obj3);
        fil_3.setName("Dau 4");
        Thread fil_5 = new Thread(obj3);
        fil_3.setName("Dau 5");
        Thread fil_6 = new Thread(obj3);
        fil_3.setName("Dau 6");
        Thread fil_7 = new Thread(obj3);
        fil_3.setName("Dau 7");
        Thread fil_8 = new Thread(obj3);
        fil_3.setName("Dau 8");
        Thread fil_9 = new Thread(obj3);
        fil_3.setName("Dau 9");
        Thread fil_10 = new Thread(obj3);
        fil_3.setName("Dau 10");
        Thread fil_11 = new Thread(obj3);
        fil_3.setName("Dau 11");
        Thread fil_12 = new Thread(obj3);
        fil_3.setName("Dau 12");
        Thread fil_13 = new Thread(obj3);
        fil_3.setName("Dau 13");
        Thread fil_14 = new Thread(obj3);
        fil_3.setName("Dau 14");
        Thread fil_15 = new Thread(obj3);
        fil_3.setName("Dau 15");
        
        fil_1.start();
        fil_2.start();
        fil_3.start();
        fil_4.start();
        fil_5.start();
        fil_6.start();
        fil_7.start();
        fil_8.start();
        fil_9.start();
        fil_10.start();
        fil_11.start();
        fil_12.start();
        fil_13.start();
        fil_14.start();
        fil_15.start();
        
        fil_1.join(); //Espera el fil_1 que el fil principal, el que l’ha invocat acabi
        fil_2.join();
        fil_3.join();
        fil_4.join();
        fil_5.join();
        fil_6.join();
        fil_7.join();
        fil_8.join();
        fil_9.join();
        fil_10.join();
        fil_11.join();
        fil_12.join();
        fil_13.join();
        fil_14.join();
        fil_15.join();
        
        System.out.println("Total tirada: "+ ans.getSumaTirada());
        System.out.println("Final Fil Principal");

    }

}