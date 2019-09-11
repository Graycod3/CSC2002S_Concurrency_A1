import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.text.*;


public class Wind {

        //int[] ind_store;
        static int [] ind_s = new int[3];
        

        public static void main(String[] args){
         int low = 5;
         int high = 8;
         boolean desider = true;
         CloudData arr;
         float magnitude = 0;
         
         CloudData cl = new CloudData();
         avarage avg = new avarage(low, high, cl,false);
         cl.readData("simplesample_input.txt");
         //System.out.println(cl.dim()+" ");
         
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
          
          System.out.println(" hflg;pfwphuw igh "+avg.result);
          

         
         float L_Total = 0;
         int neighbour = 0;
         float L_Total_x =0;
			float L_Total_y =0;
         for(int k = Math.max(0,ind_s[1]-1); k<Math.min(cl.dimx, ind_s[1]+2);k++){
            
               for(int m = Math.max(0,ind_s[2]-1); k<Math.min(cl.dimy, ind_s[2]+2);k++){
                  cl.locate(m, ind_s);
                  L_Total_x  = L_Total+ cl.advection[ ind_s[0] ][ k][ m ].x;
                  L_Total_y  = L_Total+ cl.advection[ ind_s[0] ][ k][ m ].y;
                  neighbour++;
               //System.out.println("local x_component: "+L_Total);
               //System.out.println("local xy_components--outside loop: "+L_Total_x+" "+L_Total_y);
               }
           //System.out.println("local x_component: "+L_Total);
               L_Total_x /=neighbour;
               L_Total_y /=neighbour;
               System.out.println("local xy_components--outside loop: "+L_Total_x+"   "+L_Total_y);
               
               magnitude = (float)Math.sqrt(L_Total_x*L_Total_x + L_Total_y *L_Total_y );
                    
                    if (magnitude <Math.abs(cl.convection[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ])){
                        cl.classification[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ] = 0;
                   }     
                    else if (magnitude > 0.2 && magnitude >= Math.abs(cl.convection[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ])){
                        cl.classification[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ] = 1;
                    }    
                    else {
                        cl.classification[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ] = 2;
                    }
			      System.out.println("classification: "+cl.classification[ ind_s[0] ][ ind_s[1] ][ ind_s[2] ]);
               
         
         }
         //L_Total_x /=neighbour;
         //L_Total_y /=neighbour;
        
         System.out.println("local xy_components--outside loop: "+L_Total_x+"   "+L_Total_y);
         
         

         
         
         
         
  }

   
   }
