package de.firma.provider;

import de.firma.spi.ServiceInterface;
import de.firma.spi.ServiceA;

@ServiceA
public class ServiceImplementierungA implements ServiceInterface {

   @Override
   public String getName() {
      return ServiceImplementierungA.class.getSimpleName() +
             " aus " + ServiceImplementierungA.class.getModule();
   }

}
