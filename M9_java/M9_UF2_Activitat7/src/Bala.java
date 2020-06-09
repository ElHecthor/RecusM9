import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

class Bala extends Thread {

	private int x;
	private int y;
	private int velocidad;
	private String img;
	private Image image;
	private int altura;
	private int amplada;
	private boolean enemic;
	private boolean destruit;
	private boolean pass;

	public Bala(int velocidad, int altura, int amplada, int x, int y, boolean enemic, String direccioImatge) {
		this.velocidad = velocidad;
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.amplada = amplada;
		destruit = false;
		this.enemic = enemic;
		image = new ImageIcon(Nau.class.getResource(direccioImatge)).getImage();
		Thread t = new Thread(this); 
		t.start();
	}

	public void pinta(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.image, x, y, null);
	}

	public void colisio(Graphics g) throws InterruptedException{
		velocidad=0;
		destruit = true;
	}

	public void run() {
		pass = true;
		while (pass) {
			try {
				Thread.sleep(this.velocidad);
			} catch (Exception e) {
			}
			if (enemic) {
				moureBalaBaix();
			} else {
				moureBalaDalt();
			}
		}
	}

	public void moureBalaBaix (){
		y = y + velocidad;

		if(y > altura){
			destruit = true;
		}
	}

	public void moureBalaDalt (){
		y = y - velocidad;
		if( y < 5){
			destruit = true;
		}
	}
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
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

	public boolean isEnemic() {
		return enemic;
	}

	public void setEnemic(boolean enemic) {
		this.enemic = enemic;
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
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getHeight(null),image.getWidth(null));
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}