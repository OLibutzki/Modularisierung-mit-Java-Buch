package de.firma.provider;

import de.firma.spi.ServiceInterface;
import de.firma.spi.ServiceB;

@ServiceB
public class ServiceImplementierungB implements ServiceInterface {
	
   @Override
   public String getName() {
      return ServiceImplementierungB.class.getSimpleName() +
             " aus " + ServiceImplementierungB.class.getModule();
   }
   
}
