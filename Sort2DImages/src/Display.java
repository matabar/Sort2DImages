import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel {
	private static final long serialVersionUID = 1L;
	public static int dim = 900 / 3;
	private Image img;

	public Display() {
		this.img = createImage();
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, dim * 3, dim * 3, null);
		this.BubbleSortImg();
	}

	private void BubbleSortImg() {

		BufferedImage buffImg = (BufferedImage) this.img;
		int[] pixels = ((DataBufferInt) buffImg.getRaster().getDataBuffer()).getData();
		for (int l = 0; l <= 16; l += 8)
			for (int j = 0; j < (int) (Math.sqrt(dim)); j++)
				for (int i = 0; i < pixels.length - 1; i++) {
					checkBubble(i, l, pixels);
				}
	}

	private void checkBubble(int i, int l, int[] pixels) {
		int a = (pixels[i] >> l) & 255;
		int b = (pixels[i + 1] >> l) & 255;
		if (a > b) {
			int temp = pixels[i];
			pixels[i] = pixels[i + 1];
			pixels[i + 1] = temp;
		}
	}

	private Image createImage() {
		BufferedImage bufferedImage = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (int) (Math.pow(256, 3) * Math.random());
		}
		return bufferedImage;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Display());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dim * 3, dim * 3);
		frame.setVisible(true);
		while(true)
			frame.getContentPane().getComponent(0).repaint();
		
	}
}