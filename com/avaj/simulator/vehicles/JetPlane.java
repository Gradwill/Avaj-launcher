package com.avaj.simulator.vehicles;

import com.avaj.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
   private WeatherTower weatherTower;
   
   JetPlane (String name, Coordinates coordinates) {
      super(name, coordinates);
   }
   
   @Override
   public void updateConditions() {
      switch (weatherTower.getWeather(this.coordinates)) {
         case "SUN": System.out.println(this + ": The sun is so bright today.");
                     this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
                     this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                     break;
         case "RAIN": System.out.println(this + ": It's raining! better watch out for lightnings.");  
                      this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
                      break;
         case "FOG": System.out.println(this + ": It's so foggy not clear out here.");
                     this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
                     break;
         case "SNOW": System.out.println(this + ": OMG! winter is coming!");
                      this.coordinates.setHeight(this.coordinates.getHeight() - 7);
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
      return "JetPlane#"+name+"("+id+")";
   }
}