package com.avaj.simulator.vehicles;
import com.avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
   private WeatherTower weatherTower;
   
   Helicopter (String name, Coordinates coordinates) {
      super(name, coordinates);
   }
   
   @Override
   public void updateConditions() {
      switch (weatherTower.getWeather(this.coordinates)) {
         case "SUN": System.out.println(this + ": This is hot.");
                     this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
                     this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                     break;
         case "RAIN": System.out.println(this + ": Damn! it's raining.");
                      this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
                      break;
         case "FOG": System.out.println(this + ": Nothing is really clear with the Fog");
                     this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
                     break;
         case "SNOW": System.out.println(this + ": My rotor is going to freeze!");
                      this.coordinates.setHeight(this.coordinates.getHeight() - 12);
                      break;
      }
      if (this.coordinates.getHeight() == 0) {
         System.out.println(this + " landing");
         this.weatherTower.unregister(this);
      }
   }
   
   @Override
	public void registerTower(WeatherTower weatherTower)  {
      this.weatherTower = weatherTower;
      this.weatherTower.register(this);
   }
   
   public String toString() {
      return "Helicopter#"+name+"("+id+")";
   }
}