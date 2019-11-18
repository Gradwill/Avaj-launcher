package com.avaj.simulator;

import com.avaj.weather.WeatherProvider;
import com.avaj.simulator.Tower;
import com.avaj.simulator.vehicles.Coordinates;

public class WeatherTower extends Tower {
   
   public String getWeather(Coordinates coordinates) {
      return WeatherProvider.getProvider().getCurrentWeather(coordinates);
   }
   
   public void changeWeather() {
     this.conditionsChanged();
   }
}