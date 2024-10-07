package cscd210lab4;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CSCD210Lab4
{
   public static final double RES_BASE = 25.00;
   public static final double BUS_BASE = 1500.00;
   
   public static void main(String [] args)
   {
      //Declaring variables
      int beginningMeterValue, endingMeterValue;
      char customerCode;
      double billResidential, billBusiness, finalGallons, beginningMeterValue1, endingMeterValue1;
      
      
      //Building objects
      Scanner kb = new Scanner(System.in);
      DecimalFormat fmt = new DecimalFormat("#,##0.0");
      DecimalFormat fmt1 = new DecimalFormat("#,##0.00");
      
      //Prompts
      System.out.println("Please enter the beginning meter value: ");
      beginningMeterValue = kb.nextInt();
      kb.nextLine();
      
      System.out.println("Please enter the ending meter value: ");
      endingMeterValue = kb.nextInt();
      kb.nextLine();
      
      System.out.println("Please enter the customer's code: ");
      customerCode = kb.nextLine().strip().charAt(0);
      
      //Initial outputs
      System.out.println("");//spacing to make outputs presentable
      System.out.println("You have entered " + customerCode + " as the customer code.");
      System.out.println("You have entered " + (beginningMeterValue) + " as the beginning meter value.");
      System.out.println("You have entered " + (endingMeterValue) + " as the ending meter value.");
      
      //Formatting for future use. 
      //Since these values are ints, and the meter vlaue has to be divided by 10 for the right output-
      //We have to turn them into doubles so that the results end up right when subraction occurs
      beginningMeterValue1 = ((double) beginningMeterValue/10);
      endingMeterValue1 = ((double) endingMeterValue/10);
      
      //Calculation and Ouptut of Gallons
      if (endingMeterValue1 < beginningMeterValue1) //Deals with overrolling
      {
         finalGallons = (100000000.00 - beginningMeterValue1 + endingMeterValue1);
         System.out.println("You have used " + fmt.format(finalGallons) +  " gallons.");
      }  
      else if (endingMeterValue1 > beginningMeterValue1) //Deals with no overrolling
      {
         finalGallons = (endingMeterValue1 - beginningMeterValue1);
         System.out.println("You have used " + fmt.format(finalGallons) +  " gallons.");
      }
      
      
      //Calculate and Output Bill
      if (customerCode == 'r' || customerCode == 'R') //Caliculates Residential Code Bill
      { 
         if (endingMeterValue1 < beginningMeterValue1) //Deals with overrolling 
         {
            finalGallons = (100000000.0 - beginningMeterValue1 + endingMeterValue1);
            billResidential = RES_BASE + (0.0003 * finalGallons);
            System.out.println("Your bill is $" + fmt1.format(billResidential) + ".");
         }
         else 
         {
            finalGallons = (endingMeterValue1 - beginningMeterValue1);
            billResidential = RES_BASE + (0.0003 * finalGallons);
            System.out.println("Your bill is $" + fmt1.format(billResidential) + ".");
         }
      } 
      
      else if (customerCode == 'b' || customerCode == 'B')//Calculates Business Code Bill
         { 
            if (endingMeterValue1 < beginningMeterValue1) //Deals with overrolling 
            {
               finalGallons = (100000000.0 - beginningMeterValue1 + endingMeterValue1);
               
               if((finalGallons) < 999.9)//Deals with under 999.9 gallons
               {
                  billBusiness = BUS_BASE + (0.00002 * finalGallons);
                  System.out.println("Your bill is $" + fmt1.format(billBusiness) + ".");
               }
               else if((finalGallons) > 999.9)//Deals with over 999.9 gallons
               { 
                  billBusiness = BUS_BASE + (0.00002 * 999.9) + (0.00001 * ((finalGallons)-999.9));
                  System.out.println("Your bill is $" + fmt1.format(billBusiness) + ".");
               } 
            }
            else if (endingMeterValue1 > beginningMeterValue1)
            {
               finalGallons = endingMeterValue1 - beginningMeterValue1;
               if((finalGallons) < 999.9)//Deals with under 999.9 gallons
               {
                  billBusiness = BUS_BASE + (0.00002 * finalGallons);
                  System.out.println("Your bill is $" + fmt1.format(billBusiness) + ".");
               }
               else 
               {
                  finalGallons = (endingMeterValue1 - beginningMeterValue1);
                  billBusiness = BUS_BASE + (0.00002 * 999.9) + (0.00001 * ((finalGallons)-999.9));
                  System.out.println("Your bill is $" + fmt1.format(billBusiness) + ".");
               }
            }
         }
         else if (customerCode != 'b' || customerCode != 'B' || customerCode != 'r'|| customerCode != 'R')//Deals with illegitimate customer codes
            System.out.println("Your entered an illegitimate customer code. Enter either an 'r', 'R', 'b', or 'B'.");
            
   }// end main

}// end class