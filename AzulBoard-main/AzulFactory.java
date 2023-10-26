import java.util.*;
public class AzulFactory {
	private ArrayList<AzulTile> tiles;
	public AzulFactory() {
		tiles = new ArrayList<AzulTile>();
	}
	public void addTiles(AzulTile t) {
		tiles.add(t);
	}
	public void removeTiles(ArrayList<AzulTile> x) {
		for(AzulTile a : x) {
			tiles.remove(a);
		}
	
	}
	public AzulTile removeTile(int x) {
		return tiles.remove(x);
	}
	public ArrayList<AzulTile> getTiles() {
		 return tiles;
	}
	
	
}