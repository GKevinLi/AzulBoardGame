import java.util.*;
public class AzulBag {
	private ArrayList<AzulTile> bag = new ArrayList<AzulTile>();
public AzulBag() {
	for(int i = 0; i < 20; i++) {
		bag.add(new AzulTile("Yellow"));
	}
	for(int i = 0; i < 20; i++) {
		bag.add(new AzulTile("Red"));
	}
	for(int i = 0; i < 20; i++) {
		bag.add(new AzulTile("Blue"));
	}
	for(int i = 0; i < 20; i++) {
		bag.add(new AzulTile("Black"));
	}
	for(int i = 0; i < 20; i++) {
		bag.add(new AzulTile("White"));
	}
	shuffle();
}
public void shuffle() {
	Collections.shuffle(bag);
}
public void addTiles(AzulTile x) {
	bag.add(x);
}
public void removeTiles(AzulTile y) {
	bag.remove(y);
}
public AzulTile removeRandomTile() {
	return bag.remove(0);
}
public int getSize() {
	return bag.size();
}
}