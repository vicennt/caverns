package caverns.logic;

import java.util.ArrayList;

import caverns.data.DataWorker;

public class MainClass {
	public static void main(String[] args) {
		String fileNamePath = "./data/generated500-2.cav";
		DataWorker fw = new DataWorker();
		ProgramFunctions pf;
		ArrayList<CaveNode> caves;
		int[][] incidenceMatrix;
		int[][] coordenateMatrix;
		double[][] euclideanMatrix;
		String[] data;
		int numCaverns;
		System.out.println("------- Coursework Artificial Intelligence -------");
		System.out.println("Reading data.....");
		data = fw.readFile(fileNamePath);
		numCaverns = Integer.parseInt(data[0]);
		pf = new ProgramFunctions(numCaverns, data);
		caves = pf.getCaves();
		incidenceMatrix = pf.getIncidenceMatrix();
		coordenateMatrix = pf.getCoordenateMatrix();	
		euclideanMatrix = pf.getEuclideanMatrix(incidenceMatrix, coordenateMatrix);
		
		
		System.out.println("Creating the graph.....");
		for (int j = 0; j < incidenceMatrix.length; j++) 
			for (int k = 0; k < incidenceMatrix.length; k++) 
				if (incidenceMatrix[j][k] == 1) 
					caves.get(k).addCave(caves.get(j)); // opposite index							
		System.out.println("Calling A* method.....");	
		ArrayList<CaveNode> result =  pf.aStar(caves.get(0), caves.get(caves.size() - 1));
		System.out.print("[RESULT] The best path is: ");
		double sum = 0;
		int[] ids;
		if(result != null) {
			int count = 0;
			ids = new int[result.size()];
			for(int i = result.size()- 1; i >= 0; i--) {
				System.out.print(result.get(i).getNumCave() + "  ");
				ids[count] = result.get(i).getNumCave() - 1; // Saving the index 
				count++;
			}
			System.out.println(" ");
			System.out.println("Calculating path length........");	
			for (int i = 0; i < ids.length; i++) {
				if((i+1) < ids.length)
					sum+= euclideanMatrix[ids[i]][ids[i+1]];
			}
			System.out.println("[RESULT] Length: " + sum);
		}else {
			System.out.println("No path!");
		}
	}
}
