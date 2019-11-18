package com.avaj.simulator.vehicles;

public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;
	
   Coordinates(int longitude, int latitude, int height) {
      if (height < 0)
         height = 0;
      else if (height > 100)
         height = 100;
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}
	
   public int getLongitude() {
		return longitude;
	}
   
   public void setLongitude(int longitude) {
      this.longitude = longitude;
   }
	
   public int getLatitude() {
		return latitude;
	}
   
   public void setLatitude(int latitude) {
      this.latitude = latitude;
   }
	
   public int getHeight() {
		return height;
	}
   
   public void setHeight(int height) {
      if (height > 100)
         height = 100;
      else if (height < 0)
         height = 0;
      this.height = height;
   }
   public String toString() {
      return longitude + " " + latitude + " " + height;
   }
}	
