module de.firma.provider {
   requires transitive de.firma.spi;
   provides de.firma.spi.ServiceInterface
       with de.firma.provider.ServiceImplementierungA, de.firma.provider.ServiceImplementierungB;
}
