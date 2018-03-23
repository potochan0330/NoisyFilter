package noise_filtering;

import java.io.PrintWriter;
import java.util.ArrayList;


public class filter_noise {
	public static void main(String[] args) throws Exception {
		read_file mag= new read_file(); 

		ArrayList<Double> mdata = mag.open_file();
		ArrayList<Double> result = new ArrayList<Double>();
		int size = 15; //SMA size
		int id=0;
		
		int count=0;
		for(int i=0;i<mdata.size();i++){
			count++;
		}
		
		for(int i=0;i<count;i++){
			double sample =0;
			if(id>size-2){
				for(int j=id-size+1;j<id;j++){
					sample+=result.get(j);
				}
				sample+=mdata.get(id);
				result.add(sample/size);
			}
			else{
				result.add(mdata.get(id));
			}
			id++;
		}
		PrintWriter writer = new PrintWriter("filtered_data.txt");
		int last=0;	
		int step=0;
		ArrayList<Double> mark = new ArrayList<Double>();

		for(int i=0;i<id;i++){
			boolean bol=false;
			if(i<count-1 && i>size){
				//System.out.println("check");
				if(result.get(i)>result.get(i-1)&&result.get(i)>result.get(i+1)){
					for(int j=i;j>size;j--){
						if(result.get(j)<result.get(j-1)&&result.get(j)<result.get(j+1)){
							if(result.get(i)-result.get(j)>0.45&&j>last){
								last=j;
								step++;
								bol=true;
								break;
							}
						}
					}
				}
			}
			if(bol){
				mark.add(result.get(i));
			}
			else{
				mark.add(0.0);
			}
			
			writer.println(mdata.get(i)+"\t"+result.get(i)+"\t"+mark.get(i));
		}
		writer.close();

		System.out.println("Number of records : "+count);
		System.out.println("Number of filtered records : "+id);
		System.out.println("Count steps : "+step);
	}

}
