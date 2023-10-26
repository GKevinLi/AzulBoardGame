# AzulBoard
https://docs.google.com/document/d/1zWKbpLgvSHqZZ5aJ8TewGHsCI2WlZjWqo7DPjIsswyY/edit?usp=sharing
Factory Coords
https://docs.google.com/document/d/1RnshOlTpwqTIT_xWFkTHbefcZ8YPaXIPtbiHafqkd-8/edit?usp=sharing
public int detectRows() {
		int rowsFound = 0;
		for (int i = 0; i < wall.length; i ++)  {
			int tileCount = 0;
			for (int j = 0; j < wall.length; j ++) {
				if (wall[i][j] != null) {
					tileCount ++;
				}
			}
			if (tileCount == 5) {
				rowsFound ++;
			}
		}
		return rowsFound;
	}
	public int detectColors() {
		int completeColors = 0;
		for (int i = 0; i < wall.length; i ++) {
			int tileCount = 0;
			for (int j = 0; j < wall.length; j ++) {
				if (wall[(i + j)%wall.length][j] != null) {
					tileCount ++;
				}
			}
			if (tileCount == 5) {
				completeColors ++;
			}
		}
