import java.awt.*;
import java.util.*;
import javax.swing.*;

import java.awt.event.*;


public class NauEspaial extends javax.swing.JFrame {
	
	public NauEspaial() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(255, 255, 255));
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
						Short.MAX_VALUE));
		pack();
	}	

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		NauEspaial f = new NauEspaial();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Naus Espaials");
		final int altura = 480;
		final int amplada = 560;
		f.setContentPane(new PanelNau(altura, amplada));
		f.setSize(altura, amplada);
		f.setVisible(true);
	}
}

class PanelNau extends JPanel implements Runnable, KeyListener {
	Choque colisio = new Choque(2);
	private int numNavesEnemigas = 10;
	Vector<Nau> nau = new Vector<Nau>();
	Nau naveJugador;
	Bala bala;
	Bala balaEnemic;
	Vector<Bala> listBalas = new Vector<Bala>();
	int altura;
	int anchura;


	public PanelNau(int altura, int amplada) {
		setBackground(Color.WHITE);
		this.altura = altura;
		this.anchura = amplada;

		for (int i = 0; i < nau.capacity(); i++) {
			Random rand = new Random();
			int velocitat = (rand.nextInt(3) + 5) * 10;
			int posX = rand.nextInt(100) + 30;
			int posY = rand.nextInt(100) + 30;
			int dX = rand.nextInt(3) + 1;
			int dY = rand.nextInt(3) + 1;
			String nomNau = Integer.toString(i);
			nau.add(new Nau(nomNau, posX, posY, dX, dY, velocitat, altura, amplada, "/images/nau3.png"));

		}

		addKeyListener(this);
		setFocusable(true);
		naveJugador = new Nau("NaveJugador", 200, 400, 0, 0, 100, altura, amplada, "/images/nau2.png" );
		Thread n = new Thread(this);
		n.start();

	}

	public void run() {
		while(true) {
			try { Thread.sleep(10);} catch(Exception e) {} // espero 0,01 segons

			if(numNavesEnemigas != 0){
				repaint();
				try {
					comprobarColisiones();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				destruirNaus();
			}else{
				String finalJuego;
				if(numNavesEnemigas == 0){
					finalJuego="Ganas";
				}else{ 
					finalJuego="Final";
				}
				mostraMissatge(getGraphics(),finalJuego,altura,anchura/2,30);

				for (int i = 0; i < nau.size(); i++) {
					nau.elementAt(i).interrupt();
				}
				naveJugador.interrupt();
			}
		}                   
	}

	private void mostraMissatge(Graphics g, String string,int x, int y, int mida) {

		Font font = new Font("Arial", Font.BOLD, mida);
		FontMetrics fontmetrics = getFontMetrics(font);

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(string, (x - fontmetrics.stringWidth(string)) / 2,y);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<nau.size();++i) {
			nau.elementAt(i).pinta(g);
			naveJugador.pinta(g);
		}

		for (int j = 0; j < listBalas.size(); j++) {
			listBalas.elementAt(j).pinta(g);
		}

	}
	
	
	public void destruirNaus(){
		for (int j = 0; j < nau.size(); j++) {
			if(nau.elementAt(j).isDestruit()){

				nau.elementAt(j).interrupt();
				nau.elementAt(j).setPass(false);
				nau.remove(j);
			}
		}
	} 

	public void comprobarColisiones() throws InterruptedException{
		
		Thread colisioBala = new Thread(colisio);
		colisioBala.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			naveJugador.esquerra();
		}
		if (e.getKeyCode() == 39) { 
			naveJugador.dreta();
		} 
		if (e.getKeyCode() == 32) {
			bala = new Bala(60, altura, anchura, naveJugador.getX(), naveJugador.getY(), false, "/images/bala1.png");
			listBalas.add(bala);
			bala.start();
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	private class Choque implements Runnable{

		private int comproba;
		private boolean pass;

		public Choque(int comproba){
			this.comproba= comproba;
		}

		public void run() {
			pass = true;

			while(pass){
				if(comproba == 1){
					colisioEnemics();
				}else if(comproba==2){
					try {
						eliminarNavesEnemigas();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					try {
						colisioBales();
					} catch (InterruptedException e) {
						e.printStackTrace(); 
					}
				}

				pass=false;
			}
		}

		public void eliminarNavesEnemigas() throws InterruptedException{
			for (Bala m : listBalas){
				Rectangle rectangle = m.getBounds();
				for (Nau nauEnemiga : nau) {
					Rectangle rectangle2 = nauEnemiga.getBounds();
					if (rectangle.intersects(rectangle2)) {
						nauEnemiga.setDsx(0);
						nauEnemiga.setDsy(0);
						nauEnemiga.setDestruit(true);
						
						m.setX(-50);
						m.setY(50);
						m.setDestruit(true);
						numNavesEnemigas--;
					}
				}
			}
		}

		public void colisioEnemics(){
			Rectangle rectangle3 = naveJugador.getBounds();
			for (Nau nauEnemiga : nau) {
				Rectangle rectangle2 = nauEnemiga.getBounds();
				if (rectangle3.intersects(rectangle2)) {
					nauEnemiga.setDsx(0);
					nauEnemiga.setDsy(0);
					nauEnemiga.interrupt();

				}
			}
		}

		public void colisioBales() throws InterruptedException{
			for (int i = 0; i < nau.size(); i++) {
				for (Bala bala1 : nau.elementAt(i).listBalas) {
					Rectangle rectangle5 = bala1.getBounds();
					Rectangle rectangle4 = naveJugador.getBounds();

					if (rectangle5.intersects(rectangle4)) {
						bala1.colisio(getGraphics());
					}   
				}
			}
		}
	}
}
