package caverns.logic;

import java.util.ArrayList;

import caverns.data.DataWorker;

public class MainClass {
	public static void main(String[] args) {
		String fileNamePath = "./data/100_caves.cav";
		DataWorker fw = new DataWorker();
		ProgramFunctions pf;
		ArrayList<CaveNode> caves;
		int[][] incidenceMatrix;
		String[] data;
		int numCaverns;
		System.out.println("------- Coursework Artificial Intelligence -------");
		System.out.println("Reading data.....");
		data = fw.readFile(fileNamePath);
		numCaverns = Integer.parseInt(data[0]);
		pf = new ProgramFunctions(numCaverns, data);
		caves = pf.getCaves();
		incidenceMatrix = pf.getIncidenceMatrix();
		System.out.println("Creating the graph.....");
		for (int j = 0; j < incidenceMatrix.length; j++) 
			for (int k = 0; k < incidenceMatrix.length; k++) 
				if (incidenceMatrix[j][k] == 1) 
					caves.get(k).addCave(caves.get(j));								
		System.out.println("Calling A* method.....");	
		ArrayList<CaveNode> result =  pf.aStar(caves.get(0), caves.get(caves.size() - 1));
		System.out.print("[RESULT] The best path is: ");
		for(int i = result.size()- 1; i >= 0; i--) 
			System.out.print(result.get(i).getNumCave() + "  ");		
	}
}
