package szp.rafael.javaservice.discovery;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import org.jboss.logging.Logger;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.ws.rs.ApplicationPath;
import java.net.InetAddress;


/**
 * Created by rafaelszp on 1/27/17.
 */


public class DiscoverableExtension implements Extension {

    private static final Logger log = Logger.getLogger(DiscoverableExtension.class);
    private ConsulClient client;

    public <T> void processAnnotatedType(@Observes final ProcessAnnotatedType pat) {
        AnnotatedType<T> at = pat.getAnnotatedType();

        if(!at.isAnnotationPresent(ConsulDiscoverable.class) && !at.isAnnotationPresent(ApplicationPath.class)) {
            return;
        }

        ConsulDiscoverable consulDiscoverable = at.getJavaClass().getAnnotation(ConsulDiscoverable.class);
        ApplicationPath path = at.getJavaClass().getAnnotation(ApplicationPath.class);

        String consulUrl = System.getProperty("consul.url","localhost");
        log.infof("Connecting to consul client on {%s}",consulUrl);
        client = new ConsulClient(consulUrl);
        log.infof("swarm.context.path: {%s}",consulDiscoverable.contextRoot());
        log.infof("swarm.port.offset: {%s}",System.getProperty("swarm.port.offset"));
        log.infof("application.name: {%s}",System.getProperty("application.name"));
        registerService(consulDiscoverable.contextRoot(),consulDiscoverable.healthCheckUrl());
    }

    protected void registerService(String contextRoot, String healthCheckUrl){
        try {
            String contextPath = contextRoot.replaceAll("^\\/","");
            if(contextPath.length()==0){
                throw new IllegalArgumentException("swarm.context.path NAO PODE SER NULO");
            }
            int offset = System.getProperty("swarm.port.offset")==null?0:Integer.valueOf(System.getProperty("swarm.port.offset"));
            String hostname = InetAddress.getLocalHost().getHostName();
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String id = contextPath +"_"+hostname+"_"+ipAddress;
            NewService newService = new NewService();
            newService.setId(id);
            newService.setAddress(InetAddress.getLocalHost().getHostAddress());
            newService.setName(contextPath);
            int port = 8080 + offset;
            newService.setPort(port);

            NewService.Check serviceCheck = new NewService.Check();
            String http = "http://" + InetAddress.getLocalHost().getHostAddress()+ ":" + port +contextRoot+healthCheckUrl;
            log.infof("Registering {%s} on consul",http);
            serviceCheck.setHttp(http);
            serviceCheck.setInterval("10s");
            serviceCheck.setTimeout("15s");
            serviceCheck.setDeregisterCriticalServiceAfter("61s");
            newService.setCheck(serviceCheck);

            client.agentServiceRegister(newService);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
        }
    }

}
