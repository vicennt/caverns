package caverns.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProgramFunctions {
	
	private int numCaverns;
	private String[] data;


 	public ProgramFunctions(int numCaverns, String[] data) {
 		this.numCaverns = numCaverns;
 		this.data = data;
 	}
	public int numberOfCaverns(String[] data) {
		return Integer.parseInt(data[0]);
	}
	
	public int[][] getIncidenceMatrix() {
		int[][] incidenceMatrix = new int[numCaverns][numCaverns];	
		int startPosition = (numCaverns * 2) + 1;
		for(int i = 0; i < numCaverns; i++) {
			for (int j = 0; j < numCaverns; j++) {
				incidenceMatrix[i][j] = Integer.parseInt(data[startPosition]);
				startPosition++;
			}
		}	
		return incidenceMatrix;
	}
	
	public ArrayList<CaveNode> getCaves() {
		ArrayList<CaveNode> caves = new ArrayList<CaveNode>();
		int caveID = 1;
		for(int i = 1; i < ((numCaverns * 2) + 1); i = i + 2) {
				CaveNode c = new CaveNode(Integer.parseInt(data[i]), 
						Integer.parseInt(data[i + 1]), caveID);
				caves.add(c);
				caveID++;
		}
		return caves;
	}
	

	public ArrayList<CaveNode> reconstructPath(Map <CaveNode, CaveNode>  cameFrom, CaveNode current){
		ArrayList<CaveNode> path = new ArrayList<CaveNode>();
		path.add(current);
		while(cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			path.add(current);
		}
		return path;
	}
	
	public ArrayList<CaveNode> aStar(CaveNode origenNode, CaveNode objectiveNode) {
		ArrayList<CaveNode> open = new ArrayList<CaveNode>(); // Open
		ArrayList<CaveNode> closed = new ArrayList<CaveNode>();	// Close
		Map <CaveNode, CaveNode> cameFrom = new HashMap<CaveNode, CaveNode>(); // Most efficient parent
		Map <CaveNode, Double> gScore = new HashMap<CaveNode, Double>(); // Weight from start to this node
		Map <CaveNode, Double> fScore = new HashMap<CaveNode, Double>(); // f(n) = g(n) + h(n)
		open.add(origenNode); // Add origen node to open list  
		gScore.put(origenNode, 0.0); // gScore of first node is zero
		fScore.put(origenNode, (double) origenNode.manhattanDistanceObjective(objectiveNode)); // g(n) = 0 so only the euristic value
		while(!open.isEmpty()) {
			CaveNode current = open.get(0); // get the node with the lowest fScore
			if(current.equals(objectiveNode)) { // We found a goal
				return reconstructPath(cameFrom, current);
			}
			open.remove(current); // Remove current node from open list
			closed.add(current); // Add current node to close list
			for(CaveNode neighbor : current.getNeighbours()) {
				if(closed.contains(neighbor)) {
					continue;
				}		
				// Calculate function f(n) = g(n) + h(n) 
				double tentative_gScore = gScore.get(current) + 
						current.manhattanDistanceObjective(neighbor);
				if(!open.contains(neighbor)) {
					open.add(neighbor);
				}else if(tentative_gScore >= gScore.get(neighbor)) {
					continue; // Not good path
				}
				// Is better 
				cameFrom.put(neighbor, current);
				gScore.put(neighbor, tentative_gScore);
				fScore.put(neighbor, gScore.get(neighbor) + neighbor.manhattanDistanceObjective(objectiveNode));
			}
		}		
		return null;
	}
}
