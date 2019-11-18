package com.avaj.weather;

import com.avaj.simulator.vehicles.Coordinates;
import java.util.Random;

public class WeatherProvider {
   private static String[] weather;
   private static WeatherProvider weatherProvider;
   
   private WeatherProvider() {
      weather = new String[]{"RAIN", "FOG", "SUN", "SNOW"};
   }
   
   private static class WeatherProviderHolder {
      private static final WeatherProvider weatherProvider = new WeatherProvider();
   }
   
   public static WeatherProvider getProvider() {
      return WeatherProviderHolder.weatherProvider;
   }
   
   public String getCurrentWeather(Coordinates coordinates) {
      int seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
      return (weather[seed % 4]);
   }
}