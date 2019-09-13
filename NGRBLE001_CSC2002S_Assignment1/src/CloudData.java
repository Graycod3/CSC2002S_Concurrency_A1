//package cloudscapes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class CloudData {

	Vector [][][] advection; // in-plane regular grid of wind vectors, that evolve over time
	float [][][] convection; // vertical air movement strength, that evolves over time
	int [][][] classification; // cloud type per grid point, evolving over time
	int dimx, dimy, dimt; // data dimensions

	// overall number of elements in the timeline grids
	int dim(){
		return dimt*dimx*dimy;
	}
	
	// convert linear position into 3D location in simulation grid
	void locate(int pos, int [] ind)
	{
		ind[0] = (int) pos / (dimx*dimy); // t
		ind[1] = (pos % (dimx*dimy)) / dimy; // x
		ind[2] = pos % (dimy); // y
	}
	
	// read cloud simulation data from file
	void readData(String fileName){ 
		try{ 
			Scanner sc = new Scanner(new File(fileName), "UTF-8").useLocale(Locale.US);
			
			// input grid dimensions and simulation duration in timesteps
			dimt = sc.nextInt();
			dimx = sc.nextInt(); 
			dimy = sc.nextInt();
			
			// initialize and load advection (wind direction and strength) and convection
			advection = new Vector[dimt][dimx][dimy];
			convection = new float[dimt][dimx][dimy];
			for(int t = 0; t < dimt; t++)
				for(int x = 0; x < dimx; x++)
					for(int y = 0; y < dimy; y++){
						advection[t][x][y] = new Vector();
						advection[t][x][y].x = sc.nextFloat();
						advection[t][x][y].y = sc.nextFloat();
						convection[t][x][y] = sc.nextFloat();
                  
                  // highlight these
                  /*if (Math.abs(convection[t][x][y]) > magnitude(advection[t],x,y)){
							System.out.print("0");
						}
						// code 1 - uplift value <= local average
						else if(Math.abs(convection[t][x][y]) <=  magnitude(advection[t],x,y)){
							System.out.print("1");
						}
						// ... or code 1 - uplift value >= 0.2
						else if (magnitude(advection[t],x,y) >= 0.2){
							System.out.print("1");
						}
						// exceptions code 2
						else
							System.out.print("2");*/
					}
			
			classification = new int[dimt][dimx][dimy];
			sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input zeName");
			e.printStackTrace();
		}
	}
   
   
      
	
	// write classification output to file
	void writeData(String fileName, Vector wind){
		 try{ 
			 FileWriter fileWriter = new FileWriter(fileName);
			 PrintWriter printWriter = new PrintWriter(fileWriter);
			 printWriter.printf("%d %d %d\n", dimt, dimx, dimy);
			 printWriter.printf("%f %f\n", wind.x, wind.y);
			 
			 for(int t = 0; t < dimt; t++){
				 for(int x = 0; x < dimx; x++){
					for(int y = 0; y < dimy; y++){
						printWriter.printf("%d ", classification[t][x][y]);
					}
				 }
				 printWriter.printf("\n");
		     }
				 
			 printWriter.close();
		 }
		 catch (IOException e){
			 System.out.println("Unable to open output file "+fileName);
				e.printStackTrace();
		 }
       
       
	}
   
   public float magnitude(Vector[][] wind,int x,int y){
		float avgX = 0.0f;
		float avgY = 0.0f;
      float mag = 0.0f;
		int n = 0;
		for ( int i = Math.max(0, x-1); i < Math.min(dimx, x + 2); i++){
			for ( int j = Math.max(0, y-1);j < Math.min(dimy, y + 2); j++){
				avgY += wind[x][y].x;
				avgX += wind[x][y].y;
				n++;
			}
			
		}
      
       avgY /=n;
       avgX/=n;
       System.out.println("");
       System.out.println("local xy_components--outside loop: "+avgX+"   "+avgY);
       mag = (float)Math.sqrt(avgX*avgX + avgY *avgY );
       return mag;
      }
}
