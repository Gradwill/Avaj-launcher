package com.avaj.simulator.vehicles;

import com.avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
   private WeatherTower weatherTower;
   
   Baloon (String name, Coordinates coordinates) {
      super(name, coordinates);
   }
   
   @Override
   public void updateConditions() {
      switch (weatherTower.getWeather(this.coordinates)) {
         case "SUN": System.out.println(this + ": Let's enjoy a good weather and enjoy some pics.");
                     this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
                     this.coordinates.setHeight(this.coordinates.getHeight() + 4);
                     break;
         case "RAIN": System.out.println(this + ": Damn you rain! you messed up my baloon.");
                      this.coordinates.setHeight(this.coordinates.getHeight() - 5);
                      break;
         case "FOG": System.out.println(this + ": Foggy, not so clear.");
                     this.coordinates.setHeight(this.coordinates.getHeight() - 3);
                     break;
         case "SNOW": System.out.println(this + ": It's snowing! damn we gonna crash");
                      this.coordinates.setHeight(this.coordinates.getHeight() - 15);
                      break;
      } 
      if (this.coordinates.getHeight() == 0) {
         System.out.println(this + " landing.");
         this.weatherTower.unregister(this);
      }
   }
   
   @Override
   public void registerTower(WeatherTower weatherTower)  {
      this.weatherTower = weatherTower;
      this.weatherTower.register(this);
   }
   
   public String toString() {
      return "Baloon#"+name+"("+id+")";
   }
}