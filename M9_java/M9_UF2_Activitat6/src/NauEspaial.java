import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		NauEspaial f = new NauEspaial();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Naus Espaials");
		f.setContentPane(new PanelNau());
		f.setSize(480, 560);
		f.setVisible(true);

	}
}

class PanelNau extends JPanel implements Runnable {
	private int numNaus = 3;
	Thread[] filsNau;
	Nau[] nau;

	public PanelNau() {
		filsNau = new Thread[numNaus];
		nau = new Nau[numNaus];
		for (int i = 0; i < filsNau.length; i++) {
			filsNau[i] = new Thread(this);
			filsNau[i].setName("Fil Nau" + i);

			Random rand = new Random();
			int velocitat = rand.nextInt(50);
			int posX = rand.nextInt(100) + 30;
			int posY = rand.nextInt(100) + 30;
			int dX = rand.nextInt(3) + 1;
			int dY = rand.nextInt(3) + 1;
			nau[i] = new Nau(posX, posY, dX, dY, velocitat);
		}
		for (int i = 0; i < filsNau.length; ++i)
			filsNau[i].start();
	}
	
	public void run() {
		for (int i = 0; i < filsNau.length; ++i) {
			while (filsNau[i].currentThread() == filsNau[i]) {
				try {
					filsNau[i].sleep(nau[i].velocitat());
					nau[i].moure();
				} catch (InterruptedException e) {
				}
				repaint();
			}

		}
	}

	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < nau.length; ++i)
			nau[i].pinta(g);
	}
}

class Nau {
	private int x, y;
	private int dsx, dsy, v;
	private int tx = 10;
	private int ty = 10;

	private String img = "/images/nau.jpg";
	private Image image;
	
	public Nau(int x, int y, int dsx, int dsy, int v) {
		this.x = x;
		this.y = y;
		this.dsx = dsx;
		this.dsy = dsy;
		this.v = v;
		image = new ImageIcon(Nau.class.getResource("/images/nau1.png")).getImage();
	}
	
	public int velocitat() {
		return v;
	}

	public void moure (){
		x=x + dsx;
		y=y + dsy;
		
		if ( x>= 450 - tx || x<= tx) {
			dsx = - dsx;
		}
		if ( y >= 500 - ty || y<=ty ) {
			dsy = - dsy;
		}
	}
	
	public void pinta(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.image, x, y, null);
	}
}
