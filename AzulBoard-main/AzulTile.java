import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AzulTile {
	private String color;
	private BufferedImage image;
	public AzulTile(String s) {
		color = s;
		try {
			image = ImageIO.read(backgroundPainter.class.getResourceAsStream("/images/" + color + "Tile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getColor() {
		return color;
	}
	public BufferedImage getImage() {
		return image;
	}
}