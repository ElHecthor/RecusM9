import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.ImageIcon;

class Nau extends Thread {

	private String nomNau;
	private int x, y;
	int dsx, dsy, v;
	private int tx = 10;
	private int ty = 10;
	private String img = "/images/nau3.png";
	private Image image;
	private int altura;
	private int amplada;
	private boolean destruit;
	private boolean pass;
	Vector<Bala> listBalas = new Vector<Bala>();

	public Nau(String nomNau, int x, int y, int dsx, int dsy, int v, int altura, int amplada, String direccioImatge) {
		this.nomNau = nomNau;
		this.x = x;
		this.y = y;
		this.dsx = dsx;
		this.dsy = dsy;
		this.v = v;
		this.img = direccioImatge;
		image = new ImageIcon(Nau.class.getResource(direccioImatge)).getImage();
		this.altura = altura;
		this.amplada = amplada;
		tx = image.getHeight(null);
		ty = image.getWidth(null);
		Thread t = new Thread(this);
		destruit = false;
		t.start();
	}

	public int velocitat() {
		return v;
	}

	public void moure() {
		x = x + dsx;
		y = y + dsy;
		// si arriva als marges ...
		if (x >= 450 - tx || x <= tx)
			dsx = -dsx;
		if (y >= 500 - ty || y <= ty)
			dsy = -dsy;
	}

	public void pinta(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.image, x, y, null);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(this.v);
			} catch (Exception e) {
			}
			moure();
		}
	}

	public void esquerra() {
		this.dsx = -10;
	}

	public void dreta() {
		this.dsx = 10;
	}
	
	public String getNomNau() {
		return nomNau;
	}

	public void setNomNau(String nomNau) {
		this.nomNau = nomNau;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDsx() {
		return dsx;
	}

	public void setDsx(int dsx) {
		this.dsx = dsx;
	}

	public int getDsy() {
		return dsy;
	}

	public void setDsy(int dsy) {
		this.dsy = dsy;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getTx() {
		return tx;
	}

	public void setTx(int tx) {
		this.tx = tx;
	}

	public int getTy() {
		return ty;
	}

	public void setTy(int ty) {
		this.ty = ty;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAmplada() {
		return amplada;
	}

	public void setAmplada(int amplada) {
		this.amplada = amplada;
	}

	public boolean isDestruit() {
		return destruit;
	}

	public void setDestruit(boolean destruit) {
		this.destruit = destruit;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Vector<Bala> getListBalas() {
		return listBalas;
	}

	public void setListBalas(Vector<Bala> listBalas) {
		this.listBalas = listBalas;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null)-15,image.getHeight(null)-15);
	}

	public void para() {
		this.dsx = 0;
	}

}
