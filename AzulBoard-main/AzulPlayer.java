//import java.util.*;
public class AzulPlayer {
	private int points;
	private AzulBoard board; 
	private String name;
	private boolean isFirstPlayer;
	public AzulPlayer(AzulBoard board, String name) {
		points = 0;
		this.board = board;
		this.name = name;
	}
	public AzulBoard getBoard() {
		return board;
	}
	public void changeScore(int delta) {
		points+=delta;
		if (points < 0) {
			points = 0;
		}
	}
	public int getScore() {
		return points;
	}
	public String getName() {
		return name;
	}
	public boolean compareWith(AzulPlayer a) {
		return a.getName().equals(name);
	}
	public void makeFirstPlayer() {
		isFirstPlayer = true;
	}
	public void stopFirstPlayer() {
		isFirstPlayer = false;
	}
	public boolean isFirstPlayer() {
		return isFirstPlayer;
	}
}
