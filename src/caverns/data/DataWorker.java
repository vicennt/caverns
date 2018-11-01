package caverns.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataWorker {
	
	public String[] readFile(String fileNamePath) {   
        File file = new File(fileNamePath);
        Scanner inputStream;
        String[] data = null;
        try{
            inputStream = new Scanner(file);
            String aux = "";
            while(inputStream.hasNext()){
                aux += inputStream.next();
            }
            data = aux.split(",");
            inputStream.close();
            return data;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public int numberOfCaverns(String[] data) {
		return Integer.parseInt(data[0]);
	}
	
	public int[][] obtainIncidenceMatrix(String[] data, int numCaverns) {
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
	
	public int[][] obtainCoordenateMatrix(String[] data, int numCaverns) {
		int[][] coordenateMatrix = new int[numCaverns][2];	
		int startPosition = 1;
		for(int i = 0; i < numCaverns; i++) {
			for (int j = 0; j < 2; j++) {
				coordenateMatrix[i][j] = Integer.parseInt(data[startPosition]);
				startPosition++;
			}
		}
		return coordenateMatrix;
	}
	
	public double[][] obtainMatrixEdgesWeight(int[][] incidenceMatrix, int[][] coordenateMatrix, int numCaverns){
		double[][] edgesWeightMatrix = new double[numCaverns][numCaverns];	
		for(int i = 0; i < numCaverns; i++) {
			for(int j = 0; j < numCaverns; j++) {
				if(incidenceMatrix[i][j] == 1) { // Edge between node i and node j
					int x1 = coordenateMatrix[i][0];
					int x2 = coordenateMatrix[j][0];
					int y1 = coordenateMatrix[i][1];
					int y2 = coordenateMatrix[j][1];
					double weighEdge = Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
					edgesWeightMatrix[i][j] = round(weighEdge, 2);
				}
			}
		}
		return edgesWeightMatrix;	
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
}
