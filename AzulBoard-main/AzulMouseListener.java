import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AzulMouseListener implements MouseListener{
	backgroundPainter b;
	int numPlayers = 4;
	//boardPainter bo;
	boolean tilesPlacing = false;;
	//factoryPainter f;

	public AzulMouseListener(backgroundPainter a) {
		b = a;
		//bo = bb;
		//f = c;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		//System.out.println(x);
		//System.out.println(y);
		String event = b.getHappening();
		if(event.equals("Start Screen")) {
			if(x >= 760 && x <= 1040 && y >= 100 && y <= 200) {
				b.doPlayers();
				b.setHappening("Game Screen");
			}
			if (x >= 449 && x <= 654 && y >= 0 && y <= 205) {
				event = "Rules";
				b.setHappening("Rules");
			}
			if (x >= 657 && x < 855 && y > 818 && y < 920) {
				b.setPlayers(2);
				numPlayers = 2;
			}
			if (x >= 855 && x < 1061 && y > 818 && y < 920) {
				b.setPlayers(3);
				numPlayers = 3;
			}
			if (x >= 1061 && x < 1268 && y > 818 && y < 920) {
				b.setPlayers(4);
				numPlayers = 4;
			} 
		}
		else if (event.equals("Rules")) {
			if (x >= 0 && x <= 205 && y >= 0 && y <= 205) {
				event = "Start Screen";
				b.setHappening(event);
			}
		}
		else if(event.equals("Game Screen")) {
			if(x >= 1612 && y >= 174 && x <= 1901 && y <= 578 && !tilesPlacing) {
				b.setHappening("Middle Screen");
			}
			if(((x-1036)*(x-1036)) + ((y-98)*(y-98)) < 83*83 && !tilesPlacing) {
				b.setFactoryClicked(1);
				b.setHappening("Factory Screen");
			}
			if(((x-1267)*(x-1267)) + ((y-98)*(y-98)) < 83*83 && !tilesPlacing) {
				b.setFactoryClicked(2);
				b.setHappening("Factory Screen");
			}
			if(((x-1499)*(x-1499)) + ((y-98)*(y-98)) < 83*83 && !tilesPlacing) {
				b.setFactoryClicked(3);
				b.setHappening("Factory Screen");
			}
			if(((x-1036)*(x-1036)) + ((y-304)*(y-304)) < 83*83 && !tilesPlacing) {
				b.setFactoryClicked(4);
				b.setHappening("Factory Screen");
			}
			if(((x-1267)*(x-1267)) + ((y-304)*(y-304)) < 83*83 && !tilesPlacing) {
				b.setFactoryClicked(5);
				b.setHappening("Factory Screen");
			}
			if(((x-1499)*(x-1499)) + ((y-304)*(y-304)) < 83*83 && !tilesPlacing && numPlayers >= 3) {
				b.setFactoryClicked(6);
				b.setHappening("Factory Screen");
			}
			if(((x-1036)*(x-1036)) + ((y-510)*(y-510)) < 83*83 && !tilesPlacing && numPlayers >= 3) {
				b.setFactoryClicked(7);
				b.setHappening("Factory Screen");
			}
			if(((x-1267)*(x-1267)) + ((y-510)*(y-510)) < 83*83 && !tilesPlacing  && numPlayers >= 4) {
				b.setFactoryClicked(8);
				b.setHappening("Factory Screen");
			}
			if(((x-1499)*(x-1499)) + ((y-510)*(y-510)) < 83*83 && !tilesPlacing  && numPlayers >= 4) {
				b.setFactoryClicked(9);
				b.setHappening("Factory Screen");
			}
			if(tilesPlacing) {
				if(x >= 328 && y >= 503 && x <= 399 && y <= 570) {
					boolean thing = false;
					AzulTile[][] wall = b.getPlayer().getBoard().getWall();
					if(b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
					if(b.getTileClicked() != null) {
						for(AzulTile a : wall[0]) {
							if(a != null && a.getColor().equals(b.getTileClicked().getColor())) {
								thing = true;
							}
						}
					}
					}
					if(b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
					if(b.getMiddleTileClicked() != null) {
						for(AzulTile a : wall[0]) {
							if(a != null && a.getColor().equals(b.getMiddleTileClicked().getColor())) {
								thing = true;
							}
						}
					}
					}
					
					/*
					if(b.getPlayer().getBoard().getFirst(0) == null) {
						b.setRowClicked(0);
						tilesPlacing = false;
						
					}
					*/
					if( thing == false && b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
					if(b.getPlayer().getBoard().getFirst(0) == null || b.getPlayer().getBoard().getFirst(0).getColor().equals(b.getTileClicked().getColor())) {
						b.setRowClicked(0);
						tilesPlacing = false;
						
					}
					}
					if(thing == false &&   b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
						if(b.getPlayer().getBoard().getFirst(0) == null ||b.getPlayer().getBoard().getFirst(0).getColor().equals(b.getMiddleTileClicked().getColor())) {
							b.setRowClicked(0);
							tilesPlacing = false;
							
						}
						}
					
				}
				if(x >= 250 && y >= 579 && x <= 399 && y <= 646) {
					boolean thing = false;
					AzulTile[][] wall = b.getPlayer().getBoard().getWall();
					if(b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getTileClicked() != null) {
							for(AzulTile a : wall[1]) {
								if(a != null && a.getColor().equals(b.getTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
						if(b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
						if(b.getMiddleTileClicked() != null) {
							for(AzulTile a : wall[1]) {
								if(a != null && a.getColor().equals(b.getMiddleTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
					/*
					if(thing == false && b.getPlayer().getBoard().getFirst(1) == null) {
						b.setRowClicked(1);
						tilesPlacing = false;
					}
					*/
					if(thing == false && b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getPlayer().getBoard().getFirst(1) == null || b.getPlayer().getBoard().getFirst(1).getColor().equals(b.getTileClicked().getColor())) {
							b.setRowClicked(1);
							tilesPlacing = false;
							
						}
						}
						if(thing == false && b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
							if(b.getPlayer().getBoard().getFirst(1) == null || b.getPlayer().getBoard().getFirst(1).getColor().equals(b.getMiddleTileClicked().getColor())) {
								b.setRowClicked(1);
								tilesPlacing = false;
								
							}
							}
				}
				if(x >= 171 && y >= 656 && x <= 399 && y <= 724) {
					boolean thing = false;
					AzulTile[][] wall = b.getPlayer().getBoard().getWall();
					if(b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getTileClicked() != null) {
							for(AzulTile a : wall[2]) {
								if(a != null && a.getColor().equals(b.getTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
						if(b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
						if(b.getMiddleTileClicked() != null) {
							for(AzulTile a : wall[2]) {
								if(a != null && a.getColor().equals(b.getMiddleTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
						/*
					if(thing == false && b.getPlayer().getBoard().getFirst(2) == null) {
						b.setRowClicked(2);
						tilesPlacing = false;
					}
					*/
					if(thing == false && b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getPlayer().getBoard().getFirst(2) == null || b.getPlayer().getBoard().getFirst(2).getColor().equals(b.getTileClicked().getColor())) {
							b.setRowClicked(2);
							tilesPlacing = false;
							
						}
						}
						if(thing == false && b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
							if(b.getPlayer().getBoard().getFirst(2) == null || b.getPlayer().getBoard().getFirst(2).getColor().equals(b.getMiddleTileClicked().getColor())) {
								b.setRowClicked(2);
								tilesPlacing = false;
								
							}
							}
				}
				if(x >= 90 && y >= 732 && x <= 399 && y <= 801) {
					boolean thing = false;
					AzulTile[][] wall = b.getPlayer().getBoard().getWall();
					if(b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getTileClicked() != null) {
							for(AzulTile a : wall[3]) {
								if(a != null && a.getColor().equals(b.getTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
						if(b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
						if(b.getMiddleTileClicked() != null) {
							for(AzulTile a : wall[3]) {
								if(a != null && a.getColor().equals(b.getMiddleTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
					
					if(thing == false && b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getPlayer().getBoard().getFirst(3) == null ||b.getPlayer().getBoard().getFirst(3).getColor().equals(b.getTileClicked().getColor())) {
							b.setRowClicked(3);
							tilesPlacing = false;
							
						}
						}
						if(thing == false && b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
							if(b.getPlayer().getBoard().getFirst(3) == null ||b.getPlayer().getBoard().getFirst(3).getColor().equals(b.getMiddleTileClicked().getColor())) {
								b.setRowClicked(3);
								tilesPlacing = false;
								
							}
							}
				}
				if(x >= 12 && y >= 810 && x <= 399 && y <= 878) {
					boolean thing = false;
					AzulTile[][] wall = b.getPlayer().getBoard().getWall();
					if(b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getTileClicked() != null) {
							for(AzulTile a : wall[4]) {
								if(a != null && a.getColor().equals(b.getTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
						if(b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
						if(b.getMiddleTileClicked() != null) {
							for(AzulTile a : wall[4]) {
								if(a != null && a.getColor().equals(b.getMiddleTileClicked().getColor())) {
									thing = true;
								}
							}
						}
						}
					
					if(thing == false && b.getTilesClicked() != null && b.getTilesClicked().size() > 0) {
						if(b.getPlayer().getBoard().getFirst(4) == null || b.getPlayer().getBoard().getFirst(4).getColor().equals(b.getTileClicked().getColor())) {
							b.setRowClicked(4);
							tilesPlacing = false;
							
						}
						}
						if(thing == false && b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0) {
							if(b.getPlayer().getBoard().getFirst(4) == null ||b.getPlayer().getBoard().getFirst(4).getColor().equals(b.getMiddleTileClicked().getColor())) {
								b.setRowClicked(4);
								tilesPlacing = false;
								
							}
							}
				}
				if(x >= 8 && y >= 932 && x <= 601 && y <= 1008) {
					b.setRowClicked(6);
					tilesPlacing = false;
				}
				
			}
			
			
		}
		else if(event.equals("Factory Screen")) {
			if(x >= 1521 && y >= 213 && x <= 1821 && y <= 313) {
				b.setHappening("Game Screen");
				b.setSelectedTiles(new ArrayList<AzulTile>());
			}
			if(x >= 522 && y >= 107 && x <= 898 && y <= 484) {
				b.setTileClicked(1);
			}
			if(x >= 522 && y > 484 && x <= 898 && y <= 861) {
				b.setTileClicked(2);
			}
			if(x >= 898 && y >= 107 && x <= 1274 && y <= 484) {
				b.setTileClicked(3);
			}
			if(x >= 898 && y > 484 && x <= 1274 && y <= 861) {
				b.setTileClicked(4);
			}
			if(x >= 1521 && y >= 651 && x <= 1821 && y <= 751) {
				b.setHappening("Game Screen");
				tilesPlacing = true;
				b.setDrawnTiles(false);			
			}
		}
		else if(event.equals("Middle Screen")) {
			int midNum = (5 * ((y-122)/105)) + ((x-574)/105);
			//System.out.println(midNum);
			if(midNum >= 0 && midNum < b.getMiddlePile().getTiles().size()) {
				b.setMiddleTileClicked(b.getMiddlePile().getTiles().get(midNum));
			}
			if(x >= 1521 && y >= 213 && x <= 1821 && y <= 313) {
				b.setHappening("Game Screen");
				b.setSelectedMiddleTiles();
			}
			if(b.getSelectedMiddleTiles() != null && b.getSelectedMiddleTiles().size() > 0 && x >= 1521 && y >= 651 && x <= 1821 && y <= 751) {
				b.setHappening("Game Screen");
				tilesPlacing = true;
				b.setDrawnTiles(false);
			}
			
		}
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}