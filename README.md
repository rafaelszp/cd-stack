# cd-stack
Continuous Delivery Stack

## Build instructions

**First of all:** Setup $DOCKER_IP env variable.

### Manual
1. Execute the following commands:
    
        cd javaservice
        mvn clean package -DskipTests
        sh build.sh
        cd ..
        
2. From **cd-stack** directory execute:

        cd consul
        sh build.sh
        cd ..

3. From **cd-stack** directory execute:

        cd nginx
        sh build.sh
        cd .. 
        
4. From **cd-stack** directory:

        sh run.sh
        

### Automagically

1. Just run

        sh cleanup-and-run.sh
        
        
## (Optional)

<font color="red">WARNING!</font> This will remove:
- all stopped containers
- all volumes not used by at least one container
- all networks not used
        
        docker system prune -f
