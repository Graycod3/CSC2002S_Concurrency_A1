import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.text.*;
import java.util.concurrent.ForkJoinPool;

public class WindAvg{

         static long startTime = 0;
	
	      private static void tick(){
		      startTime = System.currentTimeMillis();
	      }
	      private static float tock(){
		      return (System.currentTimeMillis() - startTime) / 1000.0f; 
	      }
	      static final ForkJoinPool fjPool = new ForkJoinPool();
         
         static void run_wind(int s,int t,int x,int y, CloudData arr){
	            fjPool.invoke(new Wind( s, t,x, y, arr));
	       }
          
         static int [] ind_s = new int[3];
         
          public static void main(String[] args){
        /* int low;
         int high;
         boolean desider = true;
         CloudData arr;
         float magnitude = 0;
         
         //recenly added code
         //static final int SEQUENTIAL_CUTOFF=50000;
         
         CloudData cl = new CloudData();
         avarage avg = new avarage(low, high, cl,true);
         cl.readData("simplesample_input.txt");
         System.out.println(cl.dim()+" ");
         
         float x_v = 0;
         float y_v = 0;
         
        double myAvX = 0;
        double myAvY = 0;
        */
         float x_v = 0;
         float y_v = 0;
         //int low;
         //int high;
         
         CloudData cl = new CloudData();
         cl.readData("simplesample_input.txt");
         

        
         // use alternate for loop in java
         for (int k=0;k<cl.dim();k++){
               
                  cl.locate(k, ind_s);
                  x_v= x_v+ cl.advection[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ].x;
                  y_v= y_v + cl.advection[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ].y;
                  //cl.convection[ ind[0] ][ ind[1] ][ ind[2] ];
                  System.out.println(x_v+" "+y_v);
                  System.out.println(cl.convection[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ]);
       
         }
         System.out.println(new DecimalFormat("#.#######").format(x_v/12)+
         " "+new DecimalFormat("#.######").format(y_v/12));
          System.out.println("dim() "+ cl.dim());
          
        tick();
		  run_wind(0,cl.dimt,cl.dimx,cl.dimy,cl);
		  float time = tock();
		  System.out.println("Run took "+ time +" seconds");

  
      
      }






}