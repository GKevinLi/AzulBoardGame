//import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AzulFrame extends JFrame {
	private static final int height = 1056;
	private static final int width = 1936;
	BufferedImage startScreen;
	public AzulFrame(String name) {
		super(name);
		try {
			startScreen = ImageIO.read(backgroundPainter.class.getResource("/images/azulStartScreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		backgroundPainter starter = new backgroundPainter();
		AzulMouseListener startIt = new AzulMouseListener(starter);
		starter.addListener(startIt);
		add(starter);
		setIconImage(startScreen);
		setVisible(true);
		
	}
}