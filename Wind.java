import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.text.*;
import java.util.concurrent.RecursiveTask;

public class Wind extends RecursiveTask<Double>{
         public static int low;
         public static int high;
         boolean desider = true;
         int dimt, dimx,  dimy, strtp;
         CloudData arr;
         float magnitude = 0;
         double result = 0;
         
         Wind(int strt,int t , int x, int y,CloudData arr){
            strtp = strt;
            dimt = t;
            dimx= x;
            dimy = y;
         }

         
         //recenly added code
         //static final int SEQUENTIAL_CUTOFF=50000;
         
         //CloudData cl = new CloudData();
         
         //avarage avg = new avarage(low, high, cl,true);
         //cl.readData("simplesample_input.txt");
         //System.out.println(cl.dim()+" ");
         
         float x_v = 0;
         float y_v = 0;
         
        double myAvX = 0;
        double myAvY = 0;
        

        //int[] ind_store;
        static int [] ind_s = new int[3];
        //double myAvX = 0;
        //double myAvY = 0;
        
        // compute method
        
         //recenly added code
        static final int SEQUENTIAL_CUTOFF=50000;
         
        CloudData cl = new CloudData();
        //avarage avg = new avarage(low, high, cl,true);
        
        
        protected Double compute(){
         
         if((dimt*dimy) < SEQUENTIAL_CUTOFF){// unclosed bracket
                 
                int stp = strtp;
                 
				 
               classify();
               
            
            }
             
            else{   //8
			         int mid = (high+low)/2;
				      Wind left = new Wind( 0, dimt/2 , dimx, dimy, arr);
				      Wind right = new Wind( 0, dimt/2 , dimx, dimy, arr);

				      left.fork();
				      double riRes = right.compute();
				      double lefRes = left.join();
                  
			         if(desider==true){//9
			      	   myAvX = (lefRes+riRes);
			      	   result = myAvX;				
			         }//9
			         else{//10
				         myAvY = (lefRes+riRes);
				         result=myAvY;				
			         }//10
               }  //8 
               return result;
                  
            }// END COMPUTTE
        
          
           public void classify(){
            
            for(int t = 0; t < cl.dimt; t++){
				 for(int x = 0; x < cl.dimx; x++){
					for(int y = 0; y < cl.dimy; y++){
               
                        float L_Total = 0;
                        int neighbour = 0;
                        float L_Total_x =0;
			               float L_Total_y =0;
                        
                        for(int k = Math.max(0,ind_s[1]-1); k<Math.min(cl.dimx, ind_s[1]+2);k++){//3
                              for(int m = Math.max(0,ind_s[2]-1); k<Math.min(cl.dimy, ind_s[2]+2);k++){//4
                                 //cl.locate(m, ind_s);
                                   L_Total_x  = L_Total+ cl.advection[ ind_s[0] ][ k][ m ].x;
                                   L_Total_y  = L_Total+ cl.advection[ ind_s[0] ][ k][ m ].y;
                                    neighbour++;
                              }//3
                        }//4
                        
                      L_Total_x /=neighbour;
                      L_Total_y /=neighbour;
                      System.out.println("local xy_components--outside loop: "+L_Total_x+"   "+L_Total_y);
               
                      magnitude = (float)Math.sqrt(L_Total_x*L_Total_x + L_Total_y *L_Total_y );
                    
                     if (magnitude <Math.abs(cl.convection[t][x][y])){//5
                           cl.classification[t][x][y] = 0;
                           System.out.println("class: 0");   
                     }//5
                     else if (magnitude > 0.2 && magnitude >= Math.abs(cl.convection[t][x][y])){//6
                        cl.classification[t][x][y] = 1;
                        System.out.println("class: 1");
                    }    //6
                    else {   //7

                        cl.classification[t][x][y] = 2;
                        System.out.println("class: 2");
                    }//7
                }
              } 
            }  
         }// CLASSIFY END
        
        
        /*
        

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
        
         float x_v = 0;
         float y_v = 0;
         //int low;
         //int high;
         
         CloudData cl = new CloudData();
         cl.readData("simplesample_input.txt");
         Wind avg = new Wind(low, high, cl,true);

        
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
          
          compute();
          
          
 
          
            
   // end main
  
      
      }*/  
   
 }
