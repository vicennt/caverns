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
	}
}
