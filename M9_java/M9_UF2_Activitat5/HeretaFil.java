package ex5;

public class HeretaFil extends Thread {


    String strImprimir;
    public HeretaFil(String strP) {
        strImprimir=strP;
    }

    public void run(){
        for(int x=0;x<5;x++){
            System.out.println(strImprimir+ " " + x);
        }

    }

    public static void main(String[] args) {

        Thread primer = new HeretaFil("Fil 1");
        Thread segon = new HeretaFil("Fil 2");
        Thread tercer = new HeretaFil("Fil 3");
        Thread quart = new HeretaFil("Fil 4");
        Thread cinque = new HeretaFil("Fil 5");
        Thread seis = new HeretaFil("Fil 6");
        Thread siete = new HeretaFil("Fil 7");
        Thread ocho = new HeretaFil("Fil 8");
        Thread nueve = new HeretaFil("Fil 9");
        Thread diez = new HeretaFil("Fil 10");
        // Hem creat dos fils primer i segon, però no s’han executat.
        // Per poder−lo executar s’ha de cridar al mètode start()
        primer.start();
        segon.start();
        tercer.start();
        quart.run();
        cinque.run();
        seis.start();
        siete.start();
        ocho.start();
        nueve.start();
        diez.start();

        System.out.println("Final Fil Principal");

    }
}
