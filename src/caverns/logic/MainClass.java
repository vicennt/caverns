package caverns.logic;

import caverns.data.DataWorker;

public class MainClass {
	public static void main(String[] args) {
		System.out.println("------- Coursework Artificial Intelligence -------");
		System.out.println("# Map information");	
		DataWorker fw = new DataWorker();
		//String fileNamePath = args[1];
		String fileNamePath = "./data/small_example.cav";
		String[] data = fw.readFile(fileNamePath);
		int numCaverns = fw.numberOfCaverns(data);
		System.out.println("- Number of caverns: " + numCaverns );
		System.out.println("- Incidence Matrix: " );
		System.out.println(" " );
		int[][] incidenceMatrix = fw.obtainIncidenceMatrix(data, numCaverns);
		for(int x = 0; x < incidenceMatrix.length; x++) {
			for(int y = 0; y < incidenceMatrix.length; y++) {
				System.out.print("["+ incidenceMatrix[x][y] +"]");
			}
			System.out.println("\n");
		}	
	}
}
