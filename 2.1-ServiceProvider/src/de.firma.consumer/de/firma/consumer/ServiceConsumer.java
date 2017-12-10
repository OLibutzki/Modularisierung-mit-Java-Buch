package de.firma.consumer;

import java.util.Iterator;
import java.util.ServiceLoader;
import de.firma.spi.ServiceInterface;

public class ServiceConsumer {
	
   public static void main(String[] args) {
      Iterator<ServiceInterface> iterator = ServiceLoader.load(ServiceInterface.class).iterator();
      if(!iterator.hasNext()) {
         System.out.println("Keine Implementierung des ServiceInterface vorhanden.");
      }
      while(iterator.hasNext()) {
         System.out.println(iterator.next().getName());
      }
   }
}
