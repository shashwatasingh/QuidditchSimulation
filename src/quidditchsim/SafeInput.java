package quidditchsim;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ssingh
 */
import java.util.*;

public  class SafeInput {
    
    public static String getNonZeroLenString(Scanner pipe, String prompt)
   {
       String retString = "";  // Set this to zero length. Loop runs until it isn’t
       do
       {
           System.out.print("\n" +prompt + ": "); // show prompt add space
           retString = pipe.nextLine();
       }while(retString.length() == 0);
       
       return retString;
       
   }

     public static int  getRangedInt(Scanner pipe, String prompt, int low, int high)
   {
       int result = -1;  //  -1 if the input is invalid
       
           do{
           System.out.print(prompt + "["+low+"-"+high+"]: "); // show prompt add space
           if(pipe.hasNextInt()){
               int temp = pipe.nextInt();
               pipe.nextLine(); 
               
                if(temp >=low && temp<=high){
                    result = temp;
                }
           }
           else{
                result =  -1;
           }
           }while(result == -1);
      
       return result;
       
   }

      public static double  getRangedDouble(Scanner pipe, String prompt, double low, double high)
   {
       double result = -1;  // -1 if input is invalid
       
           do{
           System.out.print(prompt + "["+low+"-"+high+"]: "); // show prompt add space
           if(pipe.hasNextDouble()){
               double temp = pipe.nextDouble();
               pipe.nextLine(); 
               
                if(temp >=low && temp<=high){
                    result = temp;
                }
           }
           else{
                result =  -1;
           }
           }while(result == -1);
      
       return result;
       
   }
      
      public static boolean getYNConfirm(Scanner pipe, String prompt)
      {
          String choice = "F";
          boolean result = false;
            while(!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")){
                System.out.println(prompt+" [Y/N]:");
                choice = pipe.nextLine();
                if(choice.equalsIgnoreCase("Y")){
                    result = true;
                }
                else {
                    result = false;
                }
            };
            
         return result;   
      }
      
      public static void prettyHeader(String msg)
      {
          int msgCharCount = msg.length();
          char[] msgArr = msg.toCharArray();
          int blankSpaceTotal = (60 - msgCharCount - 6);
          int blankSpaceEachSide = blankSpaceTotal/2;
          int msgcharIndex = 0;
        for(int i=0; i< 3; i++)
        {
             for(int j =0; j< 60; j++){
             if(i == 1){
                 if(j < 3 || j > 56){
                   System.out.print("*");
                 }
                 else if(j > (blankSpaceEachSide + 3) && msgcharIndex < msgCharCount){
                 System.out.print(msgArr[msgcharIndex]);
                 msgcharIndex++;
                }
                 else{
                   System.out.print(" ");
                 }
             }
             else{
               System.out.print("*");
             }
             
             } 
             System.out.print("\n");
        }
      }
      
      
      
}
