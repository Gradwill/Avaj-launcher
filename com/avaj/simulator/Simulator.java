package com.avaj.simulator;
import com.avaj.simulator.vehicles.AircraftFactory;
import com.avaj.simulator.vehicles.Flyable;
import java.util.Scanner;
import java.io.*;

public class Simulator {
   //Register and log all the aircrafts into the Weather Tower, based on the input (scenario file), and return number of weather changes
   public static int registerCrafts(WeatherTower tower, File input) {
      int cycle = 0;
      String[] attribute; // Attributes of format <TYPE> <NAME> <LONGITUDE> <LATITUDE> <HEIGHT>
      String line = null;
      Flyable flyable;
      try (Scanner sc = new Scanner(input)) {
         //Get and verify the first line (Number of cycles) of the input file
         if (sc.hasNextLine())  
	         try {
               cycle = Integer.parseInt(sc.nextLine().trim());
               if (cycle < 0) {
                  System.err.println("Error: First line of input file \'" + input.getName() + "\' Should be a Positive Integer");
                  System.exit(0);
               }
            } catch (NumberFormatException e) {
               System.err.println("Error on input file " + "\'" + input.getName() + "\'" + ": first Line should be an Integer");
               System.exit(0);
            }
         //Then go through and verify all the other lines of the file
         while (sc.hasNextLine()) {
            attribute = (line = sc.nextLine().trim()).split(" ");
            if (attribute.length != 5) 
               throw new IndexOutOfBoundsException(); 
            flyable = AircraftFactory.newAircraft(attribute[0], attribute[1], Integer.parseInt(attribute[2]),
               Integer.parseInt(attribute[3]), Integer.parseInt(attribute[4]));
            flyable.registerTower(tower);
         }
      } catch (NumberFormatException e) {
         System.err.println("Error in line \'" + line + "\': elements 3, 4, 5 should be Numbers");
         System.exit(0);
      } catch (IndexOutOfBoundsException e) {
         System.err.println("Error in line \'" + line + "\': line should have five elemets of format <string> <string> <integer> <integer> <integer>");
         System.exit(0);
      } catch (FileNotFoundException e) {
         System.err.println(e);
         System.exit(0);
      }
      return cycle; //Return the number of weather change cycles parsed from the input file. 
   }
            
   public static void main(String[] args) {
      WeatherTower weatherTower = new WeatherTower();
      File input;
      PrintStream console = System.out; //The standard system output stream (Console)
      int cycle = 0;
      if (args.length == 1)
         try (PrintStream output = new PrintStream(new BufferedOutputStream(new FileOutputStream("com\\avaj\\simulation.txt")), true)) {
            input = new File(args[0]);
            //Change standard system output (Console) to a output simulation.txt file. 
            System.setOut(output);
            cycle = registerCrafts(weatherTower, input);
            System.out.println("\n      -------All Aircrafts Registered-------\n");
            while (cycle-- > 0)
               weatherTower.changeWeather();
         } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(0);
         }
      else {
         System.out.println("Command line argument should be one (input secnario file)");
         System.exit(0);
      }
      //Restore the output to standard system output (Console).
      System.setOut(console);
      System.out.println("Success...");
   }
}
      
      