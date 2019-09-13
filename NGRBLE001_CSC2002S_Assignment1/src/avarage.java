import java.util.concurrent.RecursiveTask;

public class avarage extends RecursiveTask<Double> {
    int low;
    int high;
    CloudData myArray;
    float magnitude = 0;
    static final int SEQUENTIAL_CUTOFF=50000;
    
    //CloudData myData = new CloudData();
    int Loc[] = new int[3];
        double myAvX = 0;
        double myAvY = 0;
        boolean desider;
        double result = 0;
        
        float vLocalTotal_x =0;
		  float vLocalTotal_y =0;
        
        
    
    
    avarage(int low,int high,CloudData arr,boolean desider){
       this.high = high;
       this.low = low;
       this.myArray= arr;
       this.desider =desider;
    }

    public Double compute(){
		
        
		  if((high-low) < SEQUENTIAL_CUTOFF) {
			  int ans = 0;
		      for(int i=low; i < high; i++){
				  myArray.locate(i,Loc);
				  int y = Loc[2];
				  int x = Loc[1];
				  int t = Loc[0];
				  if(desider!=true){
					 myAvY+= myArray.advection[t][x][y].y;
					 result = myAvY;   
				  }else{
					myAvX+= myArray.advection[t][x][y].x;
					result = myAvX;				
					  
			      }
			      int count = 0;
               //float vLocalTotal_x =0;
					//float vLocalTotal_y =0;
                    for (int a = Math.max(0,x-1);a<Math.min(myArray.dimx,x+2);a++) {
                        for (int b = Math.max(0, y - 1); b < Math.min(myArray.dimx, y + 2); b++) {
                            vLocalTotal_x+=myArray.advection[t][a][b].x;
							       vLocalTotal_y+=myArray.advection[t][a][b].y;
                            count++;
                        }
                    }
                    vLocalTotal_x /=count; vLocalTotal_y /=count;
                    System.out.println("lotal_avg_x "+vLocalTotal_x+"vLocalTotal_y "+vLocalTotal_y);

                    
                    magnitude = (float)Math.sqrt(vLocalTotal_x*vLocalTotal_x + vLocalTotal_y *vLocalTotal_y );
                    
                    if (magnitude <Math.abs(myArray.convection[t][x][y])){
                        myArray.classification[t][x][y] = 0;
                        System.out.println("class: 0");
                        
                    }
                        
                    else if (magnitude > 0.2 && magnitude >= Math.abs(myArray.convection[t][x][y])){
                        myArray.classification[t][x][y] = 1;
                        System.out.println("class: 1");
                    }    
                    else {

                        myArray.classification[t][x][y] = 2;
                        System.out.println("class: 2");
                    }
			      
				}	
		  }        
        else{
			int mid = (high+low)/2;
				avarage left = new avarage(low,mid,myArray,desider);
				avarage right = new avarage(mid,high,myArray,desider);

				left.fork();
				double riRes = right.compute();
				double lefRes = left.join();
			if(desider==true){
				
				myAvX = (lefRes+riRes);
				result = myAvX;				
			}
			else{
				
				myAvY = (lefRes+riRes);
				result=myAvY;				
			}

        }
        return result;
 
    }
}
