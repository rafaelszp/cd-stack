package szp.rafael.javaservice.timeservice;

import org.wildfly.swarm.jaxrs.JAXRSArchive;
import szp.rafael.javaservice.discovery.ConsulDiscoverable;

/**
 * Created by rafaelszp on 1/25/17.
 */
public class App {


    public static void main(String... args) throws Exception {

        AppBuilder appBuilder = new AppBuilder();
        HTTPSCertificate cert = new HTTPSCertificate("keystore.jks", "changeit", "time-service");
        JAXRSArchive deployment= appBuilder
                .addPackage(App.class.getPackage())
                .addPackage(ConsulDiscoverable.class.getPackage())
                .withDefaultResources()
                .createDeployment()
                .createContainer()
                .withSSL(cert)
                .getDeployment();

        deployment.addAsManifestResource("META-INF/services/javax.enterprise.inject.spi.Extension",
                "services/javax.enterprise.inject.spi.Extension");

        appBuilder.startAndDeploy();
    }
}
