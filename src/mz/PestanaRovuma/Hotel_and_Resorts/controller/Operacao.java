package mz.PestanaRovuma.Hotel_and_Resorts.controller;

import java.util.StringTokenizer;

import java.util.Calendar;

public class Operacao {

	public Operacao() {
		
	}
	
	public String dataActual() {
		 Calendar date = Calendar.getInstance();

		    int ano = date.get(Calendar.YEAR);
		    int mes = date.get(Calendar.MONTH)+1;
		    int dia = date.get(Calendar.DAY_OF_MONTH);
		    
		    return dia+"-"+mes+"-"+ano;
	}
	
	
	public int diferencaDatas(String dataInicio, String dataFim, String separador) {
		int res = 0;
		
		StringTokenizer stInicio = new StringTokenizer(dataInicio, separador);
		StringTokenizer stFim = new StringTokenizer(dataFim, separador);
		
		int diaI = Integer.parseInt(stInicio.nextToken());
		int diaF = Integer.parseInt(stFim.nextToken());
		
		int mesI = Integer.parseInt(stInicio.nextToken());
		int mesF = Integer.parseInt(stFim.nextToken());
		
		int anoI = Integer.parseInt(stInicio.nextToken());
		int anoF = Integer.parseInt(stFim.nextToken());
	
    if (anoI == anoF) {
    
        int mF =0;
        int mI = 0;
               
        	int bissextoI =0;
		int bissextoF = 0;
		
        if (anoF%4 == 0)  
			bissextoI = 1;
			
		if (anoI%4 == 0)  
			bissextoI = 1;
               
            //mes inicial
            	switch (mesI) {
		
		    case 1 : {mI = diaI;  break;   }
		    case 2 :    {
		    	      if (bissextoI == 1) 
		    	    	  mI = 31 + diaI;
		    	      else
		    	    	  mI = 31 + diaI;
                          break;	
                          }	    
		    case 3 : { if (bissextoI == 1) 
  	    	              mI = 60+diaI;
  	                  else
  	    	              mI = 59+diaI; 
  	    	              break; }
		    case 4 :  {if (bissextoI == 1) 
	                     mI = 91+diaI;
                     else
	                     mI = 90+ diaI; 
	                     break;}
		    case 5 :  { if (bissextoI == 1) 
	                     mI = 121+diaI;
                      else
	                     mI = 120+diaI; 
	                     break;}
		    case 6 : { if (bissextoI == 1) 
	                     mI = 152+diaI;
                      else
	                     mI = 151+diaI; 
	                     break;}
		    case 7 :  {if (bissextoI == 1) 
	                     mI = 182+diaI;
                      else
	                     mI = 181+diaI;   
                         break;} 
		    case 8 :  {if (bissextoI == 1) 
	                     mI = 213+diaI;
                      else
	                     mI = 212+diaI; 
	                     break;
	                     }
		    case 9 : { if (bissextoI == 1) 
	                     mI = 244+diaI;
                      else
	                     mI = 243+diaI; 
	                     break;}
	                     
		    case 10 : { if (bissextoI == 1) 
	                     mI = 274+diaI;
                       else
	                     mI = 273+diaI; 
	                     break;}
		    case 11 : { if (bissextoI == 1) 
	                     mI = 305+diaI;
                       else
	                     mI = 304+diaI; 
	                     break; }
		    case 12 :  {if (bissextoI == 1) 
	                     mI = 335+diaI;
                       else
	                     mI = 334+diaI; 
	                     break;}
		
		}
               
               
           // mes final
               
          	switch (mesF) {
		
		    case 1 : {mF = diaF;  break;   }
		    case 2 :    {
		    	      if (bissextoF == 1) 
		    	    	  mF = 31 + diaF;
		    	      else
		    	    	  mF = 31 + diaF;
                          break;	
                          }	    
		    case 3 : { if (bissextoF == 1) 
  	    	              mF = 60+diaF;
  	                  else
  	    	              mF = 59+diaF; 
  	    	              break; }
		    case 4 :  {if (bissextoF == 1) 
	                     mF = 91+diaF;
                     else
	                     mF = 90+ diaF; 
	                     break;}
		    case 5 :  { if (bissextoF == 1) 
	                     mF = 121+diaF;
                      else
	                     mF = 120+diaF; 
	                     break;}
		    case 6 : { if (bissextoF == 1) 
	                     mF = 152+diaF;
                      else
	                     mF = 151+diaF; 
	                     break;}
		    case 7 :  {if (bissextoF == 1) 
	                     mF = 182+diaF;
                      else
	                     mF = 181+diaF;   
                         break;} 
		    case 8 :  {if (bissextoF == 1) 
	                     mF = 213+diaF;
                      else
	                     mF = 212+diaF; 
	                     break;
	                     }
		    case 9 : { if (bissextoF == 1) 
	                     mF = 244+diaF;
                      else
	                     mF = 243+diaF; 
	                     break;}
	                     
		    case 10 : { if (bissextoF == 1) 
	                     mF = 274+diaF;
                       else
	                     mF = 273+diaF; 
	                     break;}
		    case 11 : { if (bissextoF == 1) 
	                     mF = 305+diaF;
                       else
	                     mF = 304+diaF; 
	                     break; }
		    case 12 :  {if (bissextoF == 1) 
	                     mF = 335+diaF;
                       else
	                     mF = 334+diaF; 
	                     break;}
		
		}
		
		res = mF- mI;
      
    }else {
    	int bissextoI =0;
		int bissextoF = 0;
		
		int difAno = 0; 
		
		 int bis = 0;
		 for (int l = anoI+1; l < anoF; l++ ) {
			 if (l % 4 == 0) {
				 bis++;
			 }
		 }
		 
		 difAno = (anoF -anoI -1)*(bis+365);
		
//	Difetenca dos anos
//		 if (difAno != 1) 
//				 difAno = difAno*366;			
//		else 
//			// fazer algo para difAno =1
//			
		
		if (anoF%4 == 0)  
			bissextoI = 1;
			
		if (anoI%4 == 0)  
			bissextoI = 1;
			
				
				
				
		
		int mF = 0;
	    int mI = 0;
		
	    int dI=0;
	    int dF= 0;
	    
		
		switch (mesF) {
		
		    case 1 : {mF = diaF;  break;   }
		    case 2 :    {
		    	      if (bissextoF == 1) 
		    	    	  mF = 31 + diaF;
		    	      else
		    	    	  mF = 31 + diaF;
                          break;	
                          }	    
		    case 3 : { if (bissextoF == 1) 
  	    	              mF = 60+diaF;
  	                  else
  	    	              mF = 59+diaF; 
  	    	              break; }
		    case 4 :  {if (bissextoF == 1) 
	                     mF = 91+diaF;
                     else
	                     mF = 90+ diaF; 
	                     break;}
		    case 5 :  { if (bissextoF == 1) 
	                     mF = 121+diaF;
                      else
	                     mF = 120+diaF; 
	                     break;}
		    case 6 : { if (bissextoF == 1) 
	                     mF = 152+diaF;
                      else
	                     mF = 151+diaF; 
	                     break;}
		    case 7 :  {if (bissextoF == 1) 
	                     mF = 182+diaF;
                      else
	                     mF = 181+diaF;   
                         break;} 
		    case 8 :  {if (bissextoF == 1) 
	                     mF = 213+diaF;
                      else
	                     mF = 212+diaF; 
	                     break;
	                     }
		    case 9 : { if (bissextoF == 1) 
	                     mF = 244+diaF;
                      else
	                     mF = 243+diaF; 
	                     break;}
	                     
		    case 10 : { if (bissextoF == 1) 
	                     mF = 274+diaF;
                       else
	                     mF = 273+diaF; 
	                     break;}
		    case 11 : { if (bissextoF == 1) 
	                     mF = 305+diaF;
                       else
	                     mF = 304+diaF; 
	                     break; }
		    case 12 :  {if (bissextoF == 1) 
	                     mF = 335+diaF;
                       else
	                     mF = 334+diaF; 
	                     break;}
		
		}
		
		
		switch (mesI) {
		
	    case 12 : {  mI = 31 - diaI;  break; }
	    case 11 : { mI = 61 - diaI;  break; }
	    case 10 : { mI = 92 - diaI;  break; }
	    case 9 :  { mI = 122 - diaI;  break; }
	    case 8 :  { mI = 153 - diaI;  break; }
	    case 7 :  { mI = 184 -diaI;   break; }
	    case 6 :  { mI = 214 - diaI;   break; }
	    case 5 :  { mI = 245 - diaI;   break; }
	    case 4 :  { mI = 275 - diaI;   break; }
	    case 3 :  { mI = 306 - diaI;    break; }
	    case 2 :  {if (bissextoI == 1) 
                     mI = 335 - diaI;
                   else
                     mI = 334- diaI; 
                     break ;}
	    case 1 :  {if (bissextoI == 1) 
                     mI = 366 - diaI;
	    
                   else
                     mI = 365 - diaI; 
                     break; }
	
	}
		
		res = (mF +mI)+ difAno;
		}
		   
		
		
		return res;
	}
}
