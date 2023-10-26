import java.awt.Graphics;
//import java.awt.AlphaComposite;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics2D;
@SuppressWarnings("serial")
public class backgroundPainter extends JPanel {
	String whatsHappening;
	BufferedImage startScreen;
	BufferedImage playingScreen;
	BufferedImage factoryScreen;
	boolean fullRow = false;
	BufferedImage middleScreen, board, Blue, Red, White, Yellow, Black, background, frame, Rule1, Rule2, Rule3, Rule4, Rule5, Rulebook;
	int factoryClicked;
	int tileClicked = -1;
	int rowClicked = -1;
	AzulTile middleTileClicked;
	AzulTile firstTile;
	AzulBoard b1 = new AzulBoard();
	AzulBoard b2 = new AzulBoard();
	AzulBoard b3 = new AzulBoard();
	AzulBoard b4 = new AzulBoard();
	AzulPlayer[] players;
	int turnIndex = 0;
	boolean drawnTiles = false;
	int numPlayers;
	boolean takenBefore = false;
	boolean test = false;
	AzulBoard b = new AzulBoard();
	//AzulPlayer p1 = new AzulPlayer(b);
	AzulFactory[] factories;
	ArrayList<AzulTile> selectedTiles;
	ArrayList<AzulTile> selectedMiddleTiles;
	AzulMiddlePile m = new AzulMiddlePile();
	AzulBag bag = new AzulBag();
	ArrayList<AzulTile> discardedTiles = new ArrayList<AzulTile>();
	factoryPainter f = new factoryPainter();
	public backgroundPainter() {
		whatsHappening = "Start Screen";
		numPlayers = 4;
		
			
		firstTile = new AzulTile("First");
		m.addTile(firstTile);
		takenBefore = false;
		try {
			startScreen = ImageIO.read(backgroundPainter.class.getResource("/images/azulStartScreen.png"));
			playingScreen = ImageIO.read(backgroundPainter.class.getResource("/images/azulPlayerBoards2.png"));
			middleScreen = ImageIO.read(backgroundPainter.class.getResource("/images/MiddleScreen.png"));
			factoryScreen = ImageIO.read(backgroundPainter.class.getResource("/images/FactoryScreen.png"));
			background = ImageIO.read(backgroundPainter.class.getResource("/images/Background.png"));
			board = ImageIO.read(backgroundPainter.class.getResource("/images/board2.png"));
			Blue = ImageIO.read(backgroundPainter.class.getResource("/images/BlueTile.png"));
			Red = ImageIO.read(backgroundPainter.class.getResource("/images/RedTile.png"));
			White = ImageIO.read(backgroundPainter.class.getResource("/images/WhiteTile.png"));
			Yellow = ImageIO.read(backgroundPainter.class.getResource("/images/YellowTile.png"));
			Black = ImageIO.read(backgroundPainter.class.getResource("/images/BlackTile.png"));
			Rule1 = ImageIO.read(backgroundPainter.class.getResource("/images/Rule1.PNG"));
			Rule2 = ImageIO.read(backgroundPainter.class.getResource("/images/Rule2.PNG"));
			Rule3 = ImageIO.read(backgroundPainter.class.getResource("/images/Rule3.PNG"));
			Rule4 = ImageIO.read(backgroundPainter.class.getResource("/images/Rule4.PNG"));
			Rule5 = ImageIO.read(backgroundPainter.class.getResource("/images/Rule5.PNG"));
			Rulebook = ImageIO.read(backgroundPainter.class.getResource("/images/RuleBookImg.png"));
			
			//frame = ImageIO.read(backgroundPainter.class.getResource("/images/frame.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setPlayers(int num) {
		numPlayers = num;
	}
	public void doPlayers() {
		if (numPlayers == 2) {
			 factories = new AzulFactory[5];
		}
		if (numPlayers == 3) {
			factories =  new AzulFactory[7];
		}
		if (numPlayers == 4) {
			 factories = new AzulFactory[9];
		}
		for(int i = 0; i < factories.length; i++) {
			factories[i] = new AzulFactory();
			for(int j = 0; j <4; j++) {
				factories[i].addTiles(bag.removeRandomTile());
				//System.out.println(bag.getSize());
			}
		}
			
			//m.addTile(new AzulTile("Red"));
		
		players = new AzulPlayer[numPlayers];
		for (int i = 0; i < numPlayers; i ++) {
			players[i] = new AzulPlayer(new AzulBoard() ,"Player " + (i + 1));
		}
		
	}
	@Override
	public void paint(Graphics g) {
		if (whatsHappening.equals("Start Screen")) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(startScreen,(getWidth() - getHeight())/2,0,getHeight(),getHeight(), null);
			g.drawImage(Rulebook, 449, 0, 654 - 449, 205, null);
			
			
	}
		else if (whatsHappening.equals("Rules")) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(Rule1, 205, 0, 500, 500, null);
			g.drawImage(Rule2, 705, 0, 500, 500, null);
			g.drawImage(Rulebook, 0, 0, 654 - 449, 205, null);
			g.drawImage(Rule3, 1205, 0, 500, 500, null);
			g.drawImage(Rule4, 205, 500, 500, 500, null);
			g.drawImage(Rule5, 705, 500, 500, 500, null);
		}
		else if (whatsHappening.equals("Game Screen")) {
			if(factoryClicked >= 1) {
				factories[factoryClicked - 1].removeTiles(selectedTiles);
				
			}
				if(tileClicked >= 1 && selectedTiles.size() != 0) {
					//drawnTiles = false;
				while(factories[factoryClicked - 1].getTiles().size() > 0) {
					AzulTile temp = factories[factoryClicked - 1].removeTile(0);
					m.addTile(temp);
				}
				}
				if(selectedMiddleTiles != null && selectedMiddleTiles.size() > 0) {
					for(AzulTile a : selectedMiddleTiles) {
						m.removeTile(a);
						//System.out.println("D" + discardedTiles.size());
					}
					if(!(takenBefore)) {
						/*
						for(AzulTile a : m.getTiles()) {
							if(a.getColor().equals(firstTile.getColor())) {
								m.removeTile(a);
							}
						}
						*/
						m.removeTile(firstTile);
						players[turnIndex].getBoard().addFloor(firstTile);
						players[turnIndex].makeFirstPlayer();
						takenBefore = true;
					}
				}
				if(test == true) {
					doit();
				}
			    tileClicked = 0;
				g.drawImage(playingScreen,0,0,getWidth(),getHeight(), null);
				g.drawImage(board, 0, (getHeight()/4)-getHeight()/19, (getWidth()*2/5)+getWidth()/24, (getHeight()*3/4)+getHeight()/20, null);
				g.drawImage(board, (int)(getWidth()/2.25), (getHeight()*3/4)-getWidth()/26, (getWidth()/6)+getWidth()/90, (getHeight()/3)-getHeight()/100, null);
				g.drawImage(board, (int)(getWidth()/2.25) + (getWidth()/6)+getWidth()/72, (getHeight()*3/4)-getHeight()/14, (getWidth()/6)+getWidth()/90, (getHeight()/3)-getHeight()/100, null);
				g.drawImage(board, (int)(getWidth()/2.25) + 2*((getWidth()/6)+getWidth()/72), (getHeight()*3/4)-getWidth()/26, (getWidth()/6)+getWidth()/90, (getHeight()/3)-getHeight()/100, null);
				g.setFont(new Font("Courier", Font.BOLD,75));
				g.drawString(players[(turnIndex)].getName() + " Points " + players[(turnIndex)%4].getScore(), 25, 175);
				g.setFont(new Font("Courier", Font.BOLD,20));
				g.drawString("Tiles: " + bag.getSize(), 1700, 100);
				g.drawString(players[(turnIndex + 1)%numPlayers].getName()  + " Points " + players[(turnIndex + 1)%numPlayers].getScore(), 880, 670);
				if (numPlayers >= 3) {
					g.drawString(players[(turnIndex + 2)%numPlayers].getName() + " Points " + players[(turnIndex + 2)%numPlayers].getScore(), 1225, 670);
					if (numPlayers >= 4) {
					g.drawString(players[(turnIndex + 3)%numPlayers].getName() + " Points " + players[(turnIndex + 3)%numPlayers].getScore(), 1567, 670);
					}
				}
				drawFactories(g, factories[0], 1036, 98);
				drawFactories(g, factories[1], 1267, 98);
				drawFactories(g, factories[2], 1499, 98);
				drawFactories(g, factories[3], 1036, 304);
				drawFactories(g, factories[4], 1267, 304);
				if (numPlayers >= 3) {
				drawFactories(g, factories[5], 1499, 304);
				drawFactories(g, factories[6], 1036, 510);
				if (numPlayers >= 4) {
				drawFactories(g, factories[7], 1267, 510);
				drawFactories(g, factories[8], 1499, 510);
				}
				}
				drawMiddle(g, m);
				drawFloor(g, players[turnIndex]);
				drawLeftFloor(g, players[(turnIndex+1)%numPlayers]);
				if (numPlayers >= 3) {
				drawMiddleFloor(g, players[(turnIndex+2)%numPlayers]);
				if (numPlayers >= 4) {
				drawRightFloor(g, players[(turnIndex+3)%numPlayers]);
				}
				}
				drawTiles(g, players[turnIndex]);
				drawLeftBoard(g, players[(turnIndex+1)%numPlayers]);
				if (numPlayers >= 3) {
				drawMiddleBoard(g, players[(turnIndex+2)%numPlayers]);
				if (numPlayers >= 4) {
				drawRightBoard(g, players[(turnIndex+3)%numPlayers]);
				}
				}
				drawWall(g, players[turnIndex]);
				drawLeftWall(g, players[(turnIndex+1)%numPlayers]);
				if (numPlayers >= 3) {
				drawMiddleWall(g, players[(turnIndex+2)%numPlayers]);
				if (numPlayers >= 4) {
				drawRightWall(g, players[(turnIndex+3)%numPlayers]);
				}
				}
				drawPoints(g, players[turnIndex]);
				drawLeftPoints(g, players[(turnIndex+1)%numPlayers]);
				if (numPlayers >= 3) {
				drawMiddlePoints(g, players[(turnIndex+2)%numPlayers]);
				if (numPlayers >= 4) {
				drawRightPoints(g, players[(turnIndex+3)%numPlayers]);
				}
				}
				if(rowClicked > -1 && drawnTiles == false) {
					if(rowClicked == 6) {
						if(selectedTiles != null && selectedTiles.size() > 0) {
							for(AzulTile a : selectedTiles) {
								players[turnIndex].getBoard().addFloor(a);
							}
						}
						if(selectedMiddleTiles != null && selectedMiddleTiles.size() > 0) {
							for(AzulTile a : selectedMiddleTiles) {
								players[turnIndex].getBoard().addFloor(a);
							}
						}
						
					}
					else {
						if(selectedTiles != null && selectedTiles.size() > 0) {
							players[turnIndex].getBoard().addTiles(rowClicked, selectedTiles);
						}
						if(selectedMiddleTiles != null && selectedMiddleTiles.size() > 0) {
							players[turnIndex].getBoard().addTiles(rowClicked, selectedMiddleTiles);
						}
					}
					
					
					
					selectedTiles = new ArrayList<>();
					selectedMiddleTiles = new ArrayList<AzulTile>();
					drawTiles(g, players[turnIndex]);
					drawLeftBoard(g, players[(turnIndex+1)%numPlayers]);
					if (numPlayers >= 3) {
					drawMiddleBoard(g, players[(turnIndex+2)%numPlayers]);
					if (numPlayers >= 4) {
					drawRightBoard(g, players[(turnIndex+3)%numPlayers]);
					}
					}
					drawFloor(g, players[turnIndex]);
					drawLeftFloor(g, players[(turnIndex+1)%numPlayers]);
					if (numPlayers >= 3) {
					drawMiddleFloor(g, players[(turnIndex+2)%numPlayers]);
					if (numPlayers >= 4) {
					drawRightFloor(g, players[(turnIndex+3)%numPlayers]);
					}
					}
					drawWall(g, players[turnIndex]);
					drawLeftWall(g, players[(turnIndex+1)%numPlayers]);
					if (numPlayers >= 3) {
					drawMiddleWall(g, players[(turnIndex+2)%numPlayers]);
					if (numPlayers >= 4) {
					drawRightWall(g, players[(turnIndex+3)%numPlayers]);
					}
					}
					drawPoints(g, players[turnIndex]);
					drawLeftPoints(g, players[(turnIndex+1)%numPlayers]);
					if (numPlayers >= 3) {
					drawMiddlePoints(g, players[(turnIndex+2)%numPlayers]);
					if (numPlayers >= 4) {
					drawRightPoints(g, players[(turnIndex+3)%numPlayers]);
					}
					}
					drawnTiles = true;
					test = true;
					repaint();
					
					
					
					
					
					
				}
				int emptyCount = 0;
				for(AzulFactory a : factories) {
					if(a.getTiles().size() == 0) {
						emptyCount++;
					}
				}
				if(rowClicked >= -1 && ((numPlayers == 2 && emptyCount == 5) || (numPlayers == 3 && emptyCount == 7) || (numPlayers == 4 && emptyCount == 9)) && m.getTiles().size() == 0) {
					
					selectedMiddleTiles = new ArrayList<AzulTile>();
					for(AzulTile a : players[0].getBoard().getDiscardPile()) {
						discardedTiles.add(a);
					}
					for(AzulTile a : players[1].getBoard().getDiscardPile()) {
						discardedTiles.add(a);
					}
					if (numPlayers >= 3) {
					for(AzulTile a : players[2].getBoard().getDiscardPile()) {
						discardedTiles.add(a);
					}
					if (numPlayers >= 4) {
					for(AzulTile a : players[3].getBoard().getDiscardPile()) {
						discardedTiles.add(a);
					}
					}
					}
					for(int i = 0; i < discardedTiles.size(); i++) {
						if(discardedTiles.get(i).getColor().equals(firstTile.getColor())) {
							discardedTiles.remove(i);
						}
					}
					//discardedTiles.remove(firstTile);
					//System.out.println("D" + discardedTiles.size());
					players[0].changeScore(players[0].getBoard().moveRows());
					players[1].changeScore(players[1].getBoard().moveRows());
					if (numPlayers >= 3) {
					players[2].changeScore(players[2].getBoard().moveRows());
					if (numPlayers >= 4) {
					players[3].changeScore(players[3].getBoard().moveRows());
					}
					}
					//firstTile = new AzulTile("First");
				
					m.addTile(firstTile);
					takenBefore = false;
					for(int i = 0; i < players.length; i++) {
						if(players[i].isFirstPlayer()) {
							turnIndex = i - 1;
						}
						System.out.println("I've been run");
						players[i].stopFirstPlayer();
					}
					for(int i = 0; i < factories.length; i++) {
						//factories[i] = new AzulFactory();
						for(int j = 0; j <4; j++) {
							if(bag.getSize() <= 0) {
								if(discardedTiles.size() > 0) {
									for(AzulTile a: discardedTiles ) {
										bag.addTiles(a);
									}
								}
								bag.shuffle();
								discardedTiles = new ArrayList<AzulTile>();
								
							}
							if(bag.getSize() > 0) {
								factories[i].addTiles(bag.removeRandomTile());
								//System.out.println(bag.getSize());
							}
							
								
							
							
						}
						
						//m.addTile(new AzulTile("Red"));
					
						
					}
					int completedRows = 0;
					for(AzulPlayer a : players) {
						if(a.getBoard().detectRows() > 0) {
							completedRows++;
						}
					}
					if(completedRows >= 1) {
						for(int i = 0; i < players.length; i++) {
							players[i].changeScore(players[i].getBoard().detectRows() * 2);
							players[i].changeScore(players[i].getBoard().detectColumns() * 7);
							players[i].changeScore(players[i].getBoard().detectColors() * 10);
						}
						whatsHappening = "End Game";
						
					}
					repaint();
					
				}
				//drawTiles(g, players[turnIndex]);
				
				
				
				//drawOtherTiles(g, players[(turnIndex+2)%4]);
				
				//drawOtherTiles(g, players[(turnIndex+3)%4]);
				//repaint();
				
				
				
				
		}
		else if (whatsHappening.equals("Factory Screen")) {
			//drawnTiles = false;
			rowClicked = -2;
			selectedTiles = new ArrayList<>();
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(factoryScreen,(getWidth() - getHeight())/2,0,getHeight(),getHeight(), null);
			Graphics2D g2 = (Graphics2D) g;
			double thickness = 2;
			//Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke((float) thickness));
			g2.drawRect(1521, 213, 300, 100);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
			g.drawString("Back", 1611, 278);
			
			f.paint(g,factories[factoryClicked - 1]);
			
			g2.setStroke(new BasicStroke((float) 7));
			g2.setColor(Color.YELLOW);
			
			if(tileClicked == 1) {
				
				
				for(AzulTile a : factories[factoryClicked - 1].getTiles()) {
					if(tileClicked - 1 < factories[factoryClicked - 1].getTiles().size()) {
						if(a.getColor().equals(factories[factoryClicked - 1].getTiles().get(0).getColor())) {
							selectedTiles.add(a);
							for(int i = 0; i < factories[factoryClicked - 1].getTiles().size(); i++) {
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 0) {
									g2.drawRect(586, 131, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 1) {
									g2.drawRect(586, 507, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 2) {
									g2.drawRect(962, 131, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 3) {
									g2.drawRect(962, 507, 376, 376);
								}
							}
							
						}
						}
			}
			//factories[factoryClicked - 1].removeTiles(selectedTiles);	
			}
			else if(tileClicked == 2) {
				for(AzulTile a : factories[factoryClicked - 1].getTiles()) {
					if(tileClicked - 1 < factories[factoryClicked - 1].getTiles().size()) {
						if(a.getColor().equals(factories[factoryClicked - 1].getTiles().get(1).getColor())) {
							selectedTiles.add(a);
							for(int i = 0; i < factories[factoryClicked - 1].getTiles().size(); i++) {
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 0) {
									g2.drawRect(586, 131, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 1) {
									g2.drawRect(586, 507, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 2) {
									g2.drawRect(962, 131, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 3) {
									g2.drawRect(962, 507, 376, 376);
								}
							}
							
						}
						}
			}
			//factories[factoryClicked - 1].removeTiles(selectedTiles);
			}
			else if(tileClicked == 3) {
				for(AzulTile a : factories[factoryClicked - 1].getTiles()) {
					if(tileClicked - 1 < factories[factoryClicked - 1].getTiles().size()) {
						if(a.getColor().equals(factories[factoryClicked - 1].getTiles().get(2).getColor())) {
							selectedTiles.add(a);
							for(int i = 0; i < factories[factoryClicked - 1].getTiles().size(); i++) {
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 0) {
									g2.drawRect(586, 131, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 1) {
									g2.drawRect(586, 507, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 2) {
									g2.drawRect(962, 131, 376, 376);
								}
								if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 3) {
									g2.drawRect(962, 507, 376, 376);
								}
							}
							
						}
						}
			}
				//factories[factoryClicked - 1].removeTiles(selectedTiles);
			}
			else if(tileClicked == 4) {
				for(AzulTile a : factories[factoryClicked - 1].getTiles()) {
					if(tileClicked - 1 < factories[factoryClicked - 1].getTiles().size()) {
					if(a.getColor().equals(factories[factoryClicked - 1].getTiles().get(3).getColor())) {
						selectedTiles.add(a);
						for(int i = 0; i < factories[factoryClicked - 1].getTiles().size(); i++) {
							if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 0) {
								g2.drawRect(586, 131, 376, 376);
							}
							if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 1) {
								g2.drawRect(586, 507, 376, 376);
							}
							if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 2) {
								g2.drawRect(962, 131, 376, 376);
							}
							if(a.equals(factories[factoryClicked - 1].getTiles().get(i)) && i == 3) {
								g2.drawRect(962, 507, 376, 376);
							}
						}
						
					}
					}
			}
				//factories[factoryClicked - 1].removeTiles(selectedTiles);
			}
			
			if(tileClicked >= 1) {
				thickness = 2;
				g.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke((float) thickness));
				g2.drawRect(1521, 651, 300, 100);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
				g.drawString("Next", 1611, 716);
				
			}
			
				
			
			
		}
		else if (whatsHappening.equals("Middle Screen")) {
			rowClicked = -2;
			selectedMiddleTiles = new ArrayList<AzulTile>();
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			g.drawImage(middleScreen,(getWidth() - getHeight())/2,0,getHeight(),getHeight(), null);
			int count1 = 0;
			int count2 = 0;
			Graphics2D g2 = (Graphics2D) g;
			double thickness = 2;
			//Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke((float) thickness));
			g2.drawRect(1521, 213, 300, 100);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
			g.drawString("Back", 1611, 278);
			
			
			g2.setStroke(new BasicStroke((float) 7));
			g2.setColor(Color.YELLOW);
			
			for(AzulTile a : m.getTiles()) {
				g.drawImage(a.getImage(), 574+count1, 122+count2, 105, 105, null);
				if(middleTileClicked != null) {
					if(a.getColor().equals(middleTileClicked.getColor())) {
						g2.drawRect(567 + count1, 115 + count2, 110, 110);
						selectedMiddleTiles.add(a);
					}
				}
				count1+=105;
				if(count1 % 525 == 0) {
					count2+=105;
					count1 = 0;
				}
			}
			if(selectedMiddleTiles != null && selectedMiddleTiles.size() > 0) {
				thickness = 2;
				g.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke((float) thickness));
				g2.drawRect(1521, 651, 300, 100);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
				g.drawString("Next", 1611, 716);
			}
			
			
		}
		if (whatsHappening.equals("End Game")) {
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.BLACK);
			
			AzulPlayer temp;
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
			g.drawString("Game Over!", getWidth()/2-120, getHeight()/3-70);
			for(int i = 0; i < players.length; i++) {
				for(int j = i+1; j < players.length; j++) {
					if(players[i].getScore() < players[j].getScore()) {
						temp = players[i];
				        players[i] = players[j];
				        players[j] = temp;
					}
				}
			}
			g.drawString("1st Place: " + players[0].getName() + "   Score: " + players[0].getScore(), getWidth()/2-270, getHeight()/3);
			g.drawString("2nd Place: " + players[1].getName() + "   Score: " + players[1].getScore(), getWidth()/2-270, getHeight()/3 + 70);
			if (numPlayers >= 3) {
			g.drawString("3rd Place: " + players[2].getName() + "   Score: " + players[2].getScore(), getWidth()/2-270, getHeight()/3 + 140);
			if (numPlayers >= 4) {
			g.drawString("4th Place: " + players[3].getName() + "   Score: " + players[3].getScore(), getWidth()/2-270, getHeight()/3 + 210);
			}
			}
			/*
			for(AzulPlayer a: players) {
				g.drawString(a.getName() + " Score: " + a.getScore(), getWidth()/2-170, getHeight()/3 + cnt);
				cnt += 70;
			}
			*/
			
			//g.drawString("First Place", 100, 100);
			//g.drawRect(100, 100,100, 100);
			/*
			ArrayList<AzulPlayer> inOrder = new ArrayList<>();
			for (AzulPlayer i: players) {
				for (int j = 0; j < inOrder.size(); j ++) {
					System.out.println(inOrder.size());
					if (i.getScore() > inOrder.get(j).getScore()) {
						inOrder.add(i);
						break;
					}
					if (inOrder.size() == 0) {
						inOrder.add(i);
						break;
					}
					
				}
			}
			int place = 1;
			g.drawString("First Place, " + inOrder.get(0).getName(), 100, 100);
			for (int i = 0; i < inOrder.size(); i ++) {
				if (i > 0) {
					if (inOrder.get(i).getScore() < inOrder.get(i - 1).getScore()) {
						place ++;
					}
					
				}
				g.setColor(Color.BLACK);
				//g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
				
				if (place == 1) {
					
				}
				if (place == 2) {
					g.drawString("Second Place, " + inOrder.get(i).getName(), getWidth()/4 + 20, getHeight()/4 + 20);
					
				}
				if (place == 3) {
					g.drawString("Third Place, " + inOrder.get(i).getName(), getWidth()/4 + 20, getHeight()/4 + 20);
	
				}
				else  {
					g.drawString("Fourth Place, " + inOrder.get(i).getName(), getWidth()/4 + 20, getHeight()/4 + 20);
	
				}
			}
			Graphics2D g2d = (Graphics2D) g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f));
			g2d.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
			g.setColor(Color.WHITE);
			*/
			
		}
	}
	public void doit() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		turnIndex++;
		turnIndex = turnIndex % numPlayers;
		test = false;
		repaint();
	}
	public void setHappening(String changeIt) {
		whatsHappening = changeIt;
		repaint();
	}
	public void addListener(AzulMouseListener doIt) {
		addMouseListener(doIt);
	}
	public String getHappening() {
		return whatsHappening;
	}
	public void setFactoryClicked(int x) {
		factoryClicked = x;
	}
	public void setTileClicked(int x) {
		tileClicked = x;
		repaint();
	}
	public AzulTile getTileClicked() {
		return selectedTiles.get(0);
	}
	public ArrayList<AzulTile> getTilesClicked() {
		return selectedTiles;
	}
	public void setRowClicked(int x) {
		rowClicked = x;
		repaint();
	}
	public AzulPlayer getPlayer() {
		return players[turnIndex];
	}
	public void setSelectedTiles(ArrayList<AzulTile> o) {
		selectedTiles = o;
	}
	public void setDrawnTiles(boolean a) {
		drawnTiles = a;
		
	}
	public AzulMiddlePile getMiddlePile() {
		return m;
	}
	public void setMiddleTileClicked(AzulTile a) {
		if (! a.getColor().equals("First")) {
		middleTileClicked = a;
		repaint();
		}
	}
	public void setSelectedMiddleTiles() {
		selectedMiddleTiles = new ArrayList<>();
		//repaint();
	}
	public ArrayList<AzulTile> getSelectedMiddleTiles() {
		return selectedMiddleTiles;
	}
	public AzulTile getMiddleTileClicked() {
		return selectedMiddleTiles.get(0);
	}
	public void drawFactories(Graphics g, AzulFactory f, int x, int y) {
		ArrayList<AzulTile> a = f.getTiles();
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i) != null) {
				if(i == 0) {
					g.drawImage(a.get(i).getImage(), x-50, y-50, 50, 50, null);
				}
				if(i == 1) {
					g.drawImage(a.get(i).getImage(), x-50, y, 50, 50, null);
				}
				if(i == 2) {
					g.drawImage(a.get(i).getImage(), x, y-50, 50, 50, null);
				}
				if(i == 3) {
					g.drawImage(a.get(i).getImage(), x, y, 50, 50, null);
				}
				
			}
		}
		/*
		if(a.get(0) != null) {
			g.drawImage(a.get(0).getImage(), x-50, y-50, 50, 50, null);
		}
		if(a.get(1) != null) {
			g.drawImage(a.get(1).getImage(), x-50, y, 50, 50, null);
		}
		if(a.get(2) != null) {
			g.drawImage(a.get(2).getImage(), x, y-50, 50, 50, null);
		}
		if(a.get(3) != null) {
			g.drawImage(a.get(3).getImage(), x, y, 50, 50, null);
		}
		*/
		
		
		
		
		
			
		
	}
	public void drawMiddle(Graphics g, AzulMiddlePile m) {
		int count1 = 0;
		int count2 = 0;
		for(AzulTile a : m.getTiles()) {
			g.drawImage(a.getImage(), 1620+count1, 180+count2, 50, 50, null);
			count1+=50;
			if(count1 % 250 == 0) {
				count2+=50;
				count1 = 0;
			}
		}
		
	}
	public void drawTiles(Graphics g, AzulPlayer a) {
		int count1 = 0;
		int count2 = 0;
		AzulBoard b = a.getBoard();
		Map<Integer, AzulTile[]> m = b.getBoard();
		Set<Integer> s = m.keySet();
		Iterator<Integer> iter = s.iterator();
		while(iter.hasNext()) {
			int x = iter.next();
			AzulTile[] aa= m.get(x);
			count1 = 0;
			for(AzulTile t : aa) {
				if(t != null) {
				g.drawImage(t.getImage(), 328 - count1, 503 + count2, 70, 70,null);
				count1 += 77; 
				}
			}
			count2 += 76;
		}
		//repaint();
	}
	public void drawLeftBoard(Graphics g, AzulPlayer a) {
		int count1 = 0;
		int count2 = 0;
		AzulBoard b = a.getBoard();
		Map<Integer, AzulTile[]> m = b.getBoard();
		Set<Integer> s = m.keySet();
		Iterator<Integer> iter = s.iterator();
		while(iter.hasNext()) {
			int x = iter.next();
			AzulTile[] aa= m.get(x);
			count1 = 0;
			for(AzulTile t : aa) {
				if(t != null) {
				g.drawImage(t.getImage(), 986 - count1, 812 + count2, 27, 27,null);
				count1 += 32; 
				}
			}
			count2 += 31;
		}
	}
	public void drawMiddleBoard(Graphics g, AzulPlayer a) {
		int count1 = 0;
		int count2 = 0;
		AzulBoard b = a.getBoard();
		Map<Integer, AzulTile[]> m = b.getBoard();
		Set<Integer> s = m.keySet();
		Iterator<Integer> iter = s.iterator();
		while(iter.hasNext()) {
			int x = iter.next();
			AzulTile[] aa= m.get(x);
			count1 = 0;
			for(AzulTile t : aa) {
				if(t != null) {
				g.drawImage(t.getImage(), 1332 - count1, 813 + count2, 27, 27,null);
				count1 += 32; 
				}
			}
			count2 += 31;
		}
	}
	public void drawRightBoard(Graphics g, AzulPlayer a) {
		int count1 = 0;
		int count2 = 0;
		AzulBoard b = a.getBoard();
		Map<Integer, AzulTile[]> m = b.getBoard();
		Set<Integer> s = m.keySet();
		Iterator<Integer> iter = s.iterator();
		while(iter.hasNext()) {
			int x = iter.next();
			AzulTile[] aa= m.get(x);
			count1 = 0;
			for(AzulTile t : aa) {
				if(t != null) {
				g.drawImage(t.getImage(), 1679 - count1, 813 + count2, 27, 27,null);
				count1 += 32; 
				}
			}
			count2 += 31;
		}
	
	}
	public void drawFloor(Graphics g, AzulPlayer a) {
		AzulTile[] floor = a.getBoard().getFloor();
		int count1 = 0;
		for(AzulTile t : floor) {
			if(t != null) {
			g.drawImage(t.getImage(), 13+count1, 941, 64, 64,null);
			count1 += 87;
			}
		}
	}
	public void drawLeftFloor(Graphics g, AzulPlayer a) {
		AzulTile[] floor = a.getBoard().getFloor();
		int count1 = 0;
		for(AzulTile t : floor) {
			if(t != null) {
			g.drawImage(t.getImage(), 859+count1, 989, 25, 25,null);
			count1 += 35;
			}
		}
	}
	public void drawMiddleFloor(Graphics g, AzulPlayer a) {
		AzulTile[] floor = a.getBoard().getFloor();
		int count1 = 0;
		for(AzulTile t : floor) {
			if(t != null) {
			g.drawImage(t.getImage(), 1205+count1, 990, 25, 25,null);
			count1 += 35;
			}
		}
	}
	public void drawRightFloor(Graphics g, AzulPlayer a) {
		AzulTile[] floor = a.getBoard().getFloor();
		int count1 = 0;
		for(AzulTile t : floor) {
			if(t != null) {
			g.drawImage(t.getImage(), 1551+count1, 989, 25, 25,null);
			count1 += 35;
			}
		}
	}
	public void drawWall(Graphics g, AzulPlayer a) {
		AzulTile[][] wall = a.getBoard().getWall();
		 for(int i = 0; i < wall.length; i++) {
			 for(int j = 0; j < wall[i].length; j++) {
				if(wall[i][j] != null) {
					g.drawImage(wall[i][j].getImage(), 447+(j*79), 508+(i*75), 70,70,null);
				}
			 }
		 }
	}
	public void drawLeftWall(Graphics g, AzulPlayer a) {
		AzulTile[][] wall = a.getBoard().getWall();
		 for(int i = 0; i < wall.length; i++) {
			 for(int j = 0; j < wall[i].length; j++) {
				if(wall[i][j] != null) {
					g.drawImage(wall[i][j].getImage(), 1033+(j*32), 813+(i*31), 27,27,null);
				}
			 }
		 }
	}
	public void drawMiddleWall(Graphics g, AzulPlayer a) {
		AzulTile[][] wall = a.getBoard().getWall();
		 for(int i = 0; i < wall.length; i++) {
			 for(int j = 0; j < wall[i].length; j++) {
				if(wall[i][j] != null) {
					g.drawImage(wall[i][j].getImage(), 1379+(j*32), 814+(i*31), 27,27,null);
				}
			 }
		 }
	}
	public void drawRightWall(Graphics g, AzulPlayer a) {
		AzulTile[][] wall = a.getBoard().getWall();
		 for(int i = 0; i < wall.length; i++) {
			 for(int j = 0; j < wall[i].length; j++) {
				if(wall[i][j] != null) {
					g.drawImage(wall[i][j].getImage(), 1724+(j*32), 813+(i*31), 27,27,null);
				}
			 }
		 }
	}
	public void drawPoints(Graphics g, AzulPlayer a) {
		int points = a.getScore();
		g.setColor(Color.BLACK);
		if(points <= 0) {
			g.fillRect(17, 205, 40, 40);
		}
		else {
			g.fillRect(17 + (((points-1)%20) * 41), 205 + (((points+19)/20) * 46), 40, 40);
		}
		
	}
	public void drawLeftPoints(Graphics g, AzulPlayer a) {
		int points = a.getScore();
		int shiftAmt = 0;
		if(points % 2 == 0) {
			shiftAmt = 16;
		}
		g.setColor(Color.BLACK);
		if(points <= 0) {
			g.fillRect(859, 690, 16, 16);
		}
		else {
			g.fillRect(859 + (((points-1)%20)/2) * 33 + shiftAmt, 690 + (((points+19)/20) * 19), 17, 17);
		}
	}
	public void drawMiddlePoints(Graphics g, AzulPlayer a) {
		
		int points = a.getScore();
		int shiftAmt = 0;
		if(points % 2 == 0) {
			shiftAmt = 16;
		}
		g.setColor(Color.BLACK);
		if(points <= 0) {
			g.fillRect(1205, 690, 16, 16);
		}
		else {
			g.fillRect(1205 + (((points-1)%20)/2) * 33 + shiftAmt, 690 + (((points+19)/20) * 19), 17, 17);
		}
	}
	public void drawRightPoints(Graphics g, AzulPlayer a) {
		int points = a.getScore();
		int shiftAmt = 0;
		if(points % 2 == 0) {
			shiftAmt = 16;
		}
		g.setColor(Color.BLACK);
		if(points <= 0) {
			g.fillRect(1550, 690, 16, 16);
		}
		else {
			g.fillRect(1550 + (((points-1)%20)/2) * 33 + shiftAmt, 690 + (((points+19)/20) * 19), 17, 17);
		}
	}
	

}