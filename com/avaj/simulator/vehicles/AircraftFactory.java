package com.avaj.simulator.vehicles;

public abstract class AircraftFactory {
   public static Flyable newAircraft (String type, String  name, int longitude, int latitude, int height) {
      switch (type.toLowerCase()) {
         case "helicopter": return new Helicopter (name, new Coordinates(longitude, latitude, height));
         case "jetplane": return new JetPlane (name, new Coordinates(longitude, latitude, height));
         case "baloon": return  new Baloon(name, new Coordinates(longitude, latitude, height)); 
         default:
            throw new IllegalArgumentException (String.format("No aicraft type \'%s\'", type));
      }
   }         
}