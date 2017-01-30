package szp.rafael.javaservice;

import szp.rafael.javaservice.discovery.ConsulDiscoverable;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by rafaelszp on 1/25/17.
 */
@ApplicationPath("/api")
@ConsulDiscoverable(contextRoot = "/time-service",
        healthCheckUrl = "/api/times/health")
public class JAXRSActivator extends Application{
}
