package caverns.logic;

import java.util.ArrayList;

public class CaveNode implements Comparable<CaveNode> {
	
	private int numCave;
	private int coordenateX;
	private int coordenateY;
	private double gScore;
	private ArrayList<CaveNode> nextCaves;
	
	public CaveNode(int coordenateX, int coordenateY, int numCave) {
		this.coordenateX = coordenateX;
		this.coordenateY = coordenateY;
		this.numCave = numCave;
		nextCaves = new ArrayList<CaveNode>();
	}
	
	public int getNumCave() {
		return numCave;
	}
	public void setNumCave(int numCave) {
		this.numCave = numCave;
	}
	public int getCoordenateX() {
		return coordenateX;
	}
	public void setCoordenateX(int coordenateX) {
		this.coordenateX = coordenateX;
	}
	public int getCoordenateY() {
		return coordenateY;
	}
	public void setCoordenateY(int coordenateY) {
		this.coordenateY = coordenateY;
	}
	
	public double getgScore() {
		return gScore;
	}
	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	public void addCave(CaveNode c) {
		nextCaves.add(c);
	}
	
	public ArrayList<CaveNode> getNeighbours(){
		return nextCaves;
	}
	
	public int manhattanDistanceObjective(CaveNode nodeObjective) {		
		return Math.abs((this.coordenateX - nodeObjective.coordenateX)) + 
				Math.abs((this.coordenateY - nodeObjective.coordenateY));
	}
	
	public double euclideanDistance(CaveNode n) {
		return Math.sqrt(Math.pow((n.coordenateX - this.coordenateX),2) + 
				Math.pow((n.coordenateY - this.coordenateY),2));
	}
	
	@Override
	public int compareTo(CaveNode o) {
		if(this.gScore == o.gScore) 
			return 0;
		else if(this.gScore < o.gScore)
			return -1;
		else 
			return 1;
	}
	@Override
	public String toString() {
		String myNeigbours = "";
		for(int i = 0; i < nextCaves.size(); i++) {
			myNeigbours += "[Neigbour " + nextCaves.get(i).getNumCave()+ "]";
		}
		return "[ID: " +this.numCave + "] [gScore: " + this.gScore + " ] " + myNeigbours;
	}
	
	
	
	
	
}
