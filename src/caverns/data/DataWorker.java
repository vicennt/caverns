package caverns.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	
	public void writeFile(String text, String fileNamePath) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileNamePath, "UTF-8");
			writer.println(text);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}
	
	
	
}
