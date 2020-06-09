package ex5;

public class RunnableFil implements Runnable {

    String strImprimir;
    public RunnableFil(String strP) {
        strImprimir=strP;
    }

    public void run(){
        for(int x=0;x<5;x++){
            System.out.println(strImprimir+ " " + x);
        }

    }

    public static void main(String[] args) {

        //Creem dos objecte de la classe RunnableFil
        RunnableFil objRunnable1 = new RunnableFil("Fil 1");
        RunnableFil objRunnable2 = new RunnableFil("Fil 2");
        RunnableFil objRunnable3 = new RunnableFil("Fil 3");
        RunnableFil objRunnable4 = new RunnableFil("Fil 4");
        RunnableFil objRunnable5 = new RunnableFil("Fil 5");
        RunnableFil objRunnable6 = new RunnableFil("Fil 6");
        RunnableFil objRunnable7 = new RunnableFil("Fil 7");
        RunnableFil objRunnable8 = new RunnableFil("Fil 8");
        RunnableFil objRunnable9 = new RunnableFil("Fil 9");
        RunnableFil objRunnable10 = new RunnableFil("Fil 10");

        //Creem dos Fils i li passem per paràmetres els objecte de la classe RunnableFil
        Thread primer = new Thread(objRunnable1);
        Thread segon = new Thread(objRunnable2);
        Thread tercer = new Thread(objRunnable3);
        Thread quart = new Thread(objRunnable4);
        Thread cinque = new Thread(objRunnable5);
        Thread seis = new Thread(objRunnable6);
        Thread siete = new Thread(objRunnable7);
        Thread ocho = new Thread(objRunnable8);
        Thread nueve = new Thread(objRunnable9);
        Thread diez = new Thread(objRunnable10);
        // Hem creat dos fils primer i segon, però no s’han executat.
        // Per poder−lo executar s’ha de cridar al mètode start()
        primer.start();
        segon.start();
        tercer.start();
        quart.start();
        cinque.start();
        seis.start();
        siete.start();
        ocho.start();
        nueve.start();
        diez.start();

        System.out.println("Final Fil Principal");

    }
}