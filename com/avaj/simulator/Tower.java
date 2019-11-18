package com.avaj.simulator;

import com.avaj.simulator.vehicles.Flyable;
import java.util.List;
import java.util.LinkedList;

public abstract class Tower {
	
   private List<Flyable> observers = new LinkedList<>();
   private boolean observerRemoved = false; //To verify if an aircraft has just been unregistered
	
   public void register(Flyable flyable) {
		if (!observers.contains(flyable)) {
         observers.add(flyable);
		   System.out.printf("Tower says: %s registered to weather tower.\n", flyable.toString());
      }
	}
	
   public void unregister(Flyable flyable) {
		observers.remove(flyable);
      observerRemoved = true; //An aircraft has just been removed
		System.out.printf("Tower says: %s unregistred from weather tower.\n", flyable.toString());
	}
	
   protected void conditionsChanged() {
      for (int i = 0; i < observers.size(); i++) {
         if (observerRemoved && i > 0) { //if an aircraft has just been unregistered, then account for an index shift on the observer List.
            i--;
            observerRemoved = false; //restore observer
         }
         observers.get(i).updateConditions();
      }
      System.out.println(); //Skip a new line (on the output file/console) after each weather change.
   }
}
