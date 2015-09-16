package samkeeleyong.mscs.week2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
	
	private static final Path FILE_NAME = Paths.get("data.txt");
	private static List<Integer> numList = new ArrayList<>(); 
	
	// run this block at runtime
	static{
		try {
			extractDataFromFile();
		} catch (IOException e) {
			System.out.println("You have failed this city.");
		}
	}
	
	private DataInitializer() {
		
	}
	
	public static int[] getNumbers(){
		if(numList.isEmpty()){
			try{
				extractDataFromFile();
			} catch(IOException e){
				
			}
		}
		
		int[] numbers = new int[numList.size()+1];
		for(int i = 0; i <= numList.size(); i++){
			numbers[i] = numList.get(0);
		}
		return numbers;
	}
	
	private static void extractDataFromFile() throws IOException{
		List<String> lines = Files.readAllLines(FILE_NAME, Charset.defaultCharset());
		System.out.println(lines);
		for(String line: lines){
			for(String strNumber: line.split(",")){
				numList.add(Integer.parseInt(strNumber));
			}
		}
	}
}
