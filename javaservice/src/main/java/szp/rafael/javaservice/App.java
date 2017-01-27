package szp.rafael.javaservice;

import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * Created by rafaelszp on 1/25/17.
 */
public class App {


    public static void main(String... args) throws Exception {

        AppBuilder appBuilder = new AppBuilder();
        HTTPSCertificate cert = new HTTPSCertificate("keystore.jks", "changeit", "time-service");
        JAXRSArchive deployment= appBuilder
                .addPackage(App.class.getPackage())
                .withDefaultResources()
                .createDeployment()
                .createContainer()
                .withSSL(cert)
                .getDeployment();
        appBuilder.startAndDeploy();
    }
}
