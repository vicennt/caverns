package caverns.logic;

public class ProgramFunctions {
	
	private int numCaverns;
	private String[] data;
	
	private int[][] coordenateMatrix;
	private int[][] incidenceMatrix;
	private double[][] euclideanMatrix;
 	private int[] manhattanDistances;


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
	
	public int[][] getCoordenateMatrix() {
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
	
	public double[][] getEuclideanMatrix(){
		euclideanMatrix = new double[numCaverns][numCaverns];	
		for(int i = 0; i < numCaverns; i++) {
			for(int j = 0; j < numCaverns; j++) {
				if(incidenceMatrix[i][j] == 1) { // Edge between node i and node j
					int x1 = coordenateMatrix[i][0];
					int x2 = coordenateMatrix[j][0];
					int y1 = coordenateMatrix[i][1];
					int y2 = coordenateMatrix[j][1];
					double weighEdge = Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
					euclideanMatrix[i][j] = round(weighEdge, 2);
				}
			}
		}
		return euclideanMatrix;	
	}

	
	public int[] getManhattanDistances() {
		manhattanDistances = new int[numCaverns];
		int coordenateXobjective = coordenateMatrix[numCaverns - 1][0];
		int coordenateYobjective = coordenateMatrix[numCaverns - 1][1];
		for(int i = 0; i < numCaverns; i++) {
				int auxManhattan = Math.abs((coordenateMatrix[i][0] - coordenateXobjective)) + 
						Math.abs((coordenateMatrix[i][1] - coordenateYobjective));
				manhattanDistances[i] = auxManhattan;
		}
		return manhattanDistances;
	}
	
	private static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision); 
	    return (double) Math.round(value * scale) / scale;
	}
	
}
