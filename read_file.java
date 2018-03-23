package noise_filtering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class read_file {

	public ArrayList<Double> open_file() throws Exception{		
		File file = new File("Assignment2-AccData.txt");	//read data from txt
		Scanner scan = new Scanner(file);	
		
		ArrayList<Double> t = new ArrayList<Double>();	//used for storing time
		ArrayList<Double> m = new ArrayList<Double>();	//used for storing magnitude 
		int line_num = 1;	//used for count the line
		while(scan.hasNextLine()){
			String txt=scan.nextLine();
			if(line_num !=1){	//don't read the first line
				String[] temp_txt = txt.split("\t");				
				t.add(Double.parseDouble(temp_txt[0]));
				Double temp_mag = 0.0;
				for(int i=1;i<temp_txt.length;i++){
					temp_mag += Double.parseDouble(temp_txt[i])*Double.parseDouble(temp_txt[i]);
				}
				m.add(Math.sqrt(temp_mag));
			}
			line_num++;
		}
		System.out.println("Finsih reading the file.");
		System.out.printf("Number of sample: %d \n", t.size());
		System.out.printf("Sample rate: %f sensor data collected per second \n", t.size()/((t.get(t.size()-1)-t.get(0))/1000));
		return m;
	}
	
	public static void main(String []args){	
	}
}
