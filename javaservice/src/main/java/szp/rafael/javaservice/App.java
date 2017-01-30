package szp.rafael.javaservice;

import org.wildfly.swarm.jaxrs.JAXRSArchive;
import szp.rafael.javaservice.discovery.ConsulDiscoverable;

import java.io.File;

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

        deployment.addAsManifestResource(new File("src/main/webapp/META-INF/services/javax.enterprise.inject.spi.Extension"),
                "services/javax.enterprise.inject.spi.Extension");

        appBuilder.startAndDeploy();
    }
}
