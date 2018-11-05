package caverns.logic;

import caverns.data.DataWorker;

public class MainClass {
	public static void main(String[] args) {
		String fileNamePath = "./data/small_example.cav";
		DataWorker fw = new DataWorker();
		ProgramFunctions pf;
		String[] data;
		int numCaverns;
		
		System.out.println("------- Coursework Artificial Intelligence -------");
		System.out.println("# Map information");	

		data = fw.readFile(fileNamePath);
		numCaverns = Integer.parseInt(data[0]);
		pf = new ProgramFunctions(numCaverns, data);
		System.out.println("- Number of caverns: " + numCaverns );
		System.out.println("- Incidence Matrix: " );
		System.out.println(" " );
		int[][] incidenceMatrix = pf.getIncidenceMatrix();
		for(int x = 0; x < incidenceMatrix.length; x++) {
			for(int y = 0; y < incidenceMatrix.length; y++) {
				System.out.print("["+ incidenceMatrix[x][y] +"]");
			}
			System.out.println("\n");
		}
		System.out.println("- Coordenates Matrix: ");
		System.out.println(" " );
		int[][] coordenateMatrix = pf.getCoordenateMatrix();
		for(int x = 0; x < coordenateMatrix.length; x++) {
			for(int y = 0; y < 2; y++) {
				System.out.print("["+ coordenateMatrix[x][y] +"]");
			}
			System.out.println("\n");
		}	
		System.out.println("- Euclidean Matrix: ");
		System.out.println(" " );
		double[][] euclideanMatrix = pf.getEuclideanMatrix();
		for(int x = 0; x < euclideanMatrix.length; x++) {
			for(int y = 0; y < euclideanMatrix.length; y++) {
				System.out.print("["+ euclideanMatrix[x][y] +"]");
			}
			System.out.println("\n");
		}
		
		System.out.println("- Heuristic Manhattan vector: ");
		System.out.println(" " );
		int[] manhattanDistances = pf.getManhattanDistances();
		for(int i = 0; i < manhattanDistances.length; i++ ) {
			System.out.print("["+ manhattanDistances[i] +"]");
		}
	}
}
