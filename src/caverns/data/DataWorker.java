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

}
