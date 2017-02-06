package szp.rafael.javaservice.discovery;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by rafaelszp on 1/27/17.
 */

@Qualifier
@Retention(RUNTIME)
@Target({ElementType.TYPE})
public @interface ConsulDiscoverable{
    String healthCheckUrl();
    String contextRoot();
}
