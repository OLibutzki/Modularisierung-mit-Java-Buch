package de.firma.consumer;

import java.util.Iterator;
import java.util.ServiceLoader;
import de.firma.spi.ServiceInterface;
import de.firma.spi.ServiceA;
import de.firma.spi.ServiceB;

public class ServiceConsumer {
	
   public static void main(String[] args) {
		ServiceLoader<ServiceInterface> services = ServiceLoader.load(ServiceInterface.class);
		
		ServiceInterface serviceA = ServiceConsumer.getServiceByAnnotation(services, ServiceA.class);
		System.out.println(serviceA.getName());
		
		ServiceInterface serviceB = ServiceConsumer.getServiceByAnnotation(services, ServiceB.class);
		System.out.println(serviceB.getName());
   }
   
	private static ServiceInterface getServiceByAnnotation(ServiceLoader<ServiceInterface> services, Class clazz) {
		return services.stream().filter(provider -> provider.type().isAnnotationPresent(clazz))
				.map(ServiceLoader.Provider::get).findFirst().get();
	}
	
}
