package caverns.logic;

import java.util.ArrayList;

public class CaveNode {
	
	private int numCave;
	private int coordenateX;
	private int coordenateY;
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
	public String toString() {
		String myNeigbours = "";
		for(int i = 0; i < nextCaves.size(); i++) {
			myNeigbours += "[Neigbour " + nextCaves.get(i).getNumCave()+ "]";
		}
		return "[ID: " +this.numCave + "] " + myNeigbours;
	}
	
	
	
	
	
}
