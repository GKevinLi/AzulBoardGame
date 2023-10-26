import java.util.*;
public class AzulMiddlePile {
	private ArrayList<AzulTile> tiles;
	public AzulMiddlePile() {
		tiles = new ArrayList<>();
	}
	public void addTile(AzulTile x) {
		tiles.add(x);
		
	}
	public AzulTile removeTile(AzulTile x) {
		tiles.remove(x);
		return x;
	}
	public ArrayList<AzulTile> getTiles() {
		 return tiles;
	}
}