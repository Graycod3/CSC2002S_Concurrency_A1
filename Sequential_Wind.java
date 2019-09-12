import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.text.*;


public class Sequential_Wind{

   static int [] ind_s = new int[3];

   public static void main(String[] args){

      CloudData cl = new CloudData();
      cl.readData("largesample_input.txt");
      
      float x_v = 0;
      float y_v = 0;
         
         

        
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
          
        
      float L_Total = 0;
      
	   for(int t =0; t<cl.dimt;t++){
         int count=0;
         for(int x=0;x<cl.dimx;x++){
            for(int y=0;y<cl.dimy;y++){
                 
                                    
                  float L_Total_x =0;
		            float L_Total_y =0;
                  int neighbour = 0;
                  for(int k = Math.max(0, x-1); k<Math.min(cl.dimx, x+2);k++){
                     for(int m = Math.max(0, y-1);m<Math.min(cl.dimx, y+2);m++){
   	                     L_Total_x+=cl.advection[t][k][m].x;

	                        L_Total_y+=cl.advection[t][k][m].y;
	                     
	                        neighbour++;
}
}

      float xv = L_Total_x/neighbour;
      float yv = L_Total_y/neighbour;
      float MAGN =(float)Math.sqrt(xv*xv+yv*yv);
      if(count == (cl.dimx*cl.dimy)-1){
      System.out.print(classify(MAGN, cl.convection[t][x][y])+" ");
      System.out.println();}
      else{
      System.out.print(classify(MAGN, cl.convection[t][x][y])+" ");}
      count++;
}

}
}
}

      static int classify(float x, float y){
            
            float u = y;
            if(y<0){ u= -1*y;}
            
            if(u>x){return 0;}
            
            else if(x>0.2 && x>=u){return 1;}
            
            else{return 2;}
      
      }

}