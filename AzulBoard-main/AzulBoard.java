import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AzulBoard {
	TreeMap<Integer, AzulTile[]> m = new TreeMap<Integer, AzulTile[]>();
	AzulTile[][] wall = new AzulTile[5][5];
	AzulTile[] floor = new AzulTile[7];
	
	public AzulBoard() {
		for(int i = 0; i < 5; i++) {
			m.put(i, new AzulTile[i+1]);
		}
	}
	public void addTiles(int index, ArrayList<AzulTile> a) {
		AzulTile[] things = m.get(index);
		if(things != null) {
		int lastItem = things.length;
		for(int i = things.length-1; i >= 0; i--) {
			if(things[i] == null) {
				lastItem = i;
			}
		}
		int index2 = 0;
		for(int i = lastItem; i < things.length; i++) {
			if(index2 < a.size()) {
				things[i] = a.get(index2);
				index2++;
			}
			
		}
		m.put(index, things);
		for(AzulTile t : things) {
			if(t != null) {
			}
		}
		int index3 = 0;
		for(int i = floor.length - 1; i >= 0; i--) {
			if(floor[i] == null) {
				index3 = i;
			}
		}
		//index2 = 0;
		if(index2 < a.size()) {
			for(int i = index2; i < a.size(); i++) {
				if(floor[6] == null) {
					floor[index3] = a.get(index2);
				}
				index3++;
			}
		}
		}
		
	}
	public int moveRows() {
		int score = 0;
		Set<Integer> s = m.keySet();
		Iterator<Integer> iter = s.iterator();
		int rowNum = 0;
		while(iter.hasNext()) {
			int x = iter.next();
			int spotX = -1;
			int spotY = -1;
			AzulTile[] a = m.get(x);
			if(a[a.length-1] != null) {
				if(a[0].getColor().equals("Blue")) {
					wall[rowNum][rowNum%5] = a[0];
					spotY = rowNum;
					spotX = rowNum%5;
				}
				if(a[0].getColor().equals("Red")) {
					wall[rowNum][(rowNum+2)%5] = a[0];
					spotY = rowNum;
					spotX = (rowNum+2)%5;
				}
				if(a[0].getColor().equals("Yellow")) {
					wall[rowNum][(rowNum+1)%5] = a[0];
					spotY = rowNum;
					spotX = (rowNum+1)%5;
				}
				if(a[0].getColor().equals("Black")) {
					wall[rowNum][(rowNum+3)%5] = a[0];
					spotY = rowNum;
					spotX = (rowNum+3)%5;
				}
				if(a[0].getColor().equals("White")) {
					wall[rowNum][(rowNum+4)%5] = a[0];
					spotY = rowNum;
					spotX = (rowNum+4)%5;
				}
				int LCount = 0;
			    int RCount = 0;
				m.put(x, new AzulTile[a.length]);
				if(spotX >= 0 && spotY >= 0) {
					for(int i = spotX; i >= 0; i--) {
						if(wall[spotY][i] != null) {
							score++;
							LCount++;
						}
						else {
							break;
						}
					}
					
					for(int i = spotX; i < wall[rowNum].length; i++) {
						if(wall[spotY][i] != null) {
							score++;
							LCount++;
						}
						else {
							break;
						}
					}
					
					for(int i = spotY; i >= 0; i--) {
						if(wall[i][spotX] != null) {
							score++;
							RCount++;
						}
						else {
							break;
						}
					}
					for(int i = spotY; i < wall.length; i++) {
						if(wall[i][spotX] != null) {
							score++;
							RCount++;
						}
						else {
							break;
						}
					}
					RCount -= 2;
					LCount -= 2;
					if(RCount > 0 && LCount > 0) {
						score -= 2;
					}
					else {
						score -= 3;
					}
				}
			}
			
			rowNum++;
		}
		//m = new TreeMap<Integer, AzulTile[]>();
		for(int i = 0; i < floor.length; i++) {
			if(floor[i] != null) {
				if(i <= 1) {
					score--;
				}
				if(i >= 2 && i <= 4) {
					score = score - 2;
				}
				if(i > 4) {
					score = score - 3;
				}
			}
		}
		floor = new AzulTile[7];
		return score;
	}
	public ArrayList<AzulTile> getDiscardPile() {
		ArrayList<AzulTile> discarded = new ArrayList<>();
		Set<Integer> s = m.keySet();
		Iterator<Integer> iter = s.iterator();
		//int rowNum = 0;
		while(iter.hasNext()) {
			int x = iter.next();
			AzulTile[] a = m.get(x);
			if(a[a.length-1] != null) {
				for(int i = 0; i < a.length-1; i++) {
					discarded.add(a[i]);
				}
				
			}
		}
		for(AzulTile a : floor) {
			if(a != null) {
				discarded.add(a);
			}
		}
		
		return discarded;
	}
	public AzulTile[][] getWall() {
		return wall;
	}
	public AzulTile[] getFloor() {
		return floor;
	}
	public TreeMap<Integer, AzulTile[]> getBoard() {
		return m;
	}
	public AzulTile getFirst(int index) {
		if(m.get(index) != null) {
			return m.get(index)[0];
		}
		return null;
	}
	public void addFloor(AzulTile a) {
		
		int i = 0;
		while(floor[i] != null) {
			i++;
			if(i == 7) {
				break;
			}
		}
		if(!(i >= 7)) {
			floor[i] = a;
		}
		
	}
	public int detectColumns() {
		int columnsFound = 0;
		
		for (int i = 0; i < wall.length; i ++)  {
			int tileCount = 0;
			for (int j = 0; j < wall.length; j ++) {
				if (wall[j][i] != null) {
					tileCount ++;
				}
			}
			if (tileCount == 5) {
				columnsFound ++;
			}
		}
			
		return columnsFound;
	}
	public int detectRows() { int rowsFound = 0; for (int i = 0; i < wall.length; i ++) { int tileCount = 0; for (int j = 0; j < wall.length; j ++) { if (wall[i][j] != null) { tileCount ++; } } if (tileCount == 5) { rowsFound ++; } } return rowsFound; } 
	public int detectColors() { int completeColors = 0; for (int i = 0; i < wall.length; i ++) { int tileCount = 0; for (int j = 0; j < wall.length; j ++) { if (wall[(i + j)%wall.length][j] != null) { tileCount ++; } } if (tileCount == 5) { completeColors ++; } }return completeColors;
	
	}}
	