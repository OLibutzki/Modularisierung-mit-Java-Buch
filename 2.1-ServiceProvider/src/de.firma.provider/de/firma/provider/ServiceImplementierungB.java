package de.firma.provider;

import de.firma.spi.ServiceInterface;

public class ServiceImplementierungB implements ServiceInterface {
	
   @Override
   public String getName() {
      return ServiceImplementierungB.class.getSimpleName() +
             " aus " + ServiceImplementierungB.class.getModule();
   }
   
}
