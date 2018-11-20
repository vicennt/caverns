package caverns.logic;

import java.util.ArrayList;

import caverns.data.DataWorker;

public class MainClass {
	public static void main(String[] args) {	
		// Cheking if the args are correct
		String fileName = "";
		String fileNamePath = "";
		if(args.length != 1) {
			System.out.println("The number of args is not correct!");
			System.exit(0);
		}else {
			String[] fileIn = args[0].split("\\.");
			if(!fileIn[1].equals("cav")) {
				System.out.println("The input file has diferent extension, you need to introduce a .cav file");
				System.exit(0);
			}else {
				fileNamePath = args[0];
				fileName = fileIn[0];
			}
		}
	
		DataWorker fw = new DataWorker();
		ProgramFunctions pf;
		ArrayList<CaveNode> caves;
		int[][] incidenceMatrix;
		int[][] coordenateMatrix;
		double[][] euclideanMatrix;
		String[] data;
		int numCaverns;
		System.out.println("------- Coursework Artificial Intelligence ---------");
	    System.out.println("--------------- Student 40411929 -------------------");
		System.out.println("Reading data.....");
		System.out.println("Filename: " + fileNamePath);
		data = fw.readFile(fileNamePath);
		numCaverns = Integer.parseInt(data[0]);
		pf = new ProgramFunctions(numCaverns, data);
		caves = pf.getCaves();
		incidenceMatrix = pf.getIncidenceMatrix();
		coordenateMatrix = pf.getCoordenateMatrix();	
		euclideanMatrix = pf.getEuclideanMatrix(incidenceMatrix, coordenateMatrix);	
		//Reading incidence matrix with the opposite index in order to get the neighbours
		System.out.println("Creating the graph.....");
		for (int j = 0; j < incidenceMatrix.length; j++) 
			for (int k = 0; k < incidenceMatrix.length; k++) 
				if (incidenceMatrix[j][k] == 1) 
					caves.get(k).addCave(caves.get(j)); // opposite index							
		System.out.println("Calling A* method.....");	
		ArrayList<CaveNode> result =  pf.aStar(caves.get(0), caves.get(caves.size() - 1));
		String path = "";
		double sum = 0;
		int[] ids;
		if(result != null) {
			int count = 0;
			ids = new int[result.size()];
			// Getting all the caves indexs
			for(int i = result.size()- 1; i >= 0; i--) {
				path += result.get(i).getNumCave() + "  ";
				ids[count] = result.get(i).getNumCave() - 1; // Saving the index 
				count++;
			}
			for (int i = 0; i < ids.length; i++) {
				if((i+1) < ids.length)
					sum+= euclideanMatrix[ids[i]][ids[i+1]];
			}
			System.out.println("[RESULT] The best path is: " + path);
			System.out.println("[RESULT] Length: " + round(sum, 2));			
		}else {
			System.out.println("No path!");
			path += "No path";
		}
		// Writing the result path into file
		fw.writeFile(path, fileName + ".csn");
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision); 
	    return (double) Math.round(value * scale) / scale;
	}
	
}
