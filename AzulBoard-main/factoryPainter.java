import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class factoryPainter extends JPanel {
	@SuppressWarnings("unused")
	private BufferedImage factoryScreen, Blue, Black, Red, White, Yellow;
	public factoryPainter() {
		
		try {
			White = ImageIO.read(backgroundPainter.class.getResource("/images/WhiteTile.png"));
			Blue = ImageIO.read(backgroundPainter.class.getResource("/images/BlueTile.png"));
			Yellow = ImageIO.read(backgroundPainter.class.getResource("/images/YellowTile.png"));
			Black = ImageIO.read(backgroundPainter.class.getResource("/images/BlackTile.png"));
			Red = ImageIO.read(backgroundPainter.class.getResource("/images/RedTile.png"));
			factoryScreen = ImageIO.read(backgroundPainter.class.getResource("/images/FactoryScreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void paint(Graphics g, AzulFactory f) {
		g.drawImage(factoryScreen,(getWidth() - getHeight())/2,0,getHeight(),getHeight(), null);
		ArrayList<AzulTile> a = f.getTiles();
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i) != null) {
				if(i == 0) {
					g.drawImage(a.get(i).getImage(), 590, 135, 376, 376, null);
		
				}
				if(i == 1) {
					g.drawImage(a.get(i).getImage(), 590, 511, 376, 376, null);
				}
				if(i == 2) {
					g.drawImage(a.get(i).getImage(), 966, 135, 376, 376, null);
				}
				if(i == 3) {
					g.drawImage(a.get(i).getImage(), 966, 511, 376, 376, null);
				}
			}
		}
		/*
		if(a.get(3) != null) {
			g.drawImage(a.get(3).getImage(), 898, 484, 376, 376, null);
		}
		if(a.get(1) != null) {
			g.drawImage(a.get(1).getImage(), 522, 484, 376, 376, null);
		}
		if(a.get(2) != null) {
			g.drawImage(a.get(2).getImage(), 898, 108, 376, 376, null);
		}
		if(a.get(0) != null) {
			g.drawImage(a.get(0).getImage(), 522, 108, 376, 376, null);
		}
		*/
		
	}
	
}