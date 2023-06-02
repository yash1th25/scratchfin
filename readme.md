# Create and Deploy an API

## Task 1: API

To verify the competency in writing code.

You will find a [swagger spec](https://gitlab.scratchpay.com/-/snippets/42/raw/main/swagger.yaml) defining an API that you must create. It is a simple user system with a single endpoint. Please use the specification and write an API in the language of your choosing. Keep in mind that you will need to store the user information.

### Process

* Created springboot application and pushed it to github (https://github.com/yash1th25/scratchpay) 

* To run it in your local, you can download it by cloning the directory to your local using git clone
  
  `gh repo clone yash1th25/scratchpay`

* As a pre-req make sure to install and configure a database. Configure the database properties under src/main/resources/application.properties. 
  In my case I took a postgres docker image and deployed it on my environment
   
  Below are the command to run the docker image and execute the sql scripts to create schemas on postgres
  1. To download the postgres image from dockerhub
     
     `docker pull postgres`
      
  2. Run the postgres container as below 
     
     `docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres`
     
     --name - Assign a name to the container
     
     -e - to set the environmental variables
     
     -d - Run container in background and print container ID
     
     -p - Publish a container’s port(s) to the host
     
   
  3. Go to the python_database_scripts folder and run the below command to copy the userdb.sql file to the postgres container
     
     `docker cp userdb.sql postgresdb:/`
   
  4. To execute the script in the docker container
     
     `docker exec -it postgresdb psql -U postgres -f userdb.sql`

* To build the code, use the below commands
  
  `./mvnw clean install`

* To run the application
  
  `java -jar ./target/restapi-0.0.1-SNAPSHOT.jar`

* To hit the below APIs, you can use something like postman to test.

  POST - http://localhost:8080/v1/users (To create the users)
  
  ![postman](https://github.com/yash1th25/scratchpay/assets/135289833/90f6ef39-5048-4047-9498-bc86541e70b5)
  
  GET - http://localhost:8080/v1/users (To fetch all user details)
  
  GET - http://localhost:8080/v1/users/{id} (To fetch particular user)

## Task 2: Local Setup

This step is to verify your competency in setting up a development workflow.

Create a local environment that can be used to verify the code. Please keep in mind that a large number of projects are done with various setups (Unix and Windows based), so on any local setup please take into consideration this limitation (i.e. make sure it works across platforms). There is a [CSV](https://gitlab.scratchpay.com/-/snippets/42/raw/main/data.csv) file containing data that should be loaded in order to test your setup. Please think of a creative way to make loading the data easier.

### Process

As per the requirement, I have to create a local environment for testing and it should work on all platforms. Below are the steps performed to achieve it.

I wrote python scripts to load the csv into database, which makes the process of executing the test cases easy.

##Manual creation
1. To work my application on various platform, create a Docker container using below command. We can change the platform as per your requirement.
    Manual creation: `docker build -t restapi . --platform=linux/arm64`
                  
##Automation   
2. We can also create the image by using the workflow `.github/workflows/maven.yml` by changing the envirnoment variable platform as per your architecture and OS
add screenshot
 
3. The tag of the image is printed in the console logs of workflow.
 
 add screenshot
4. Now pull the image using docker pull yasharitha123.jfrog.io/docker/restapi:<image tag> and run it using below command
  
  docker  container run --name restapi  -e SQL_HOST=<give the ip address of the postgress> -e SQL_DB_NAME=userdb -e SQL_USERNAME=postgres -e SQL_PASSWORD=admin -d -p 8080:8080 yasharitha123.jfrog.io/docker/restapi:<image tag>
  
Note: IP address of the postgres is obtained by docker inspect <containerID>
  
  
  
 
        
 



## Task 3: Containerization and Orchestration

This step is to test your understanding of containerization, automation and orchestration.

Please containerize your application and create the required manifests/configuration to deploy your application to a Kubernetes cluster. Please use best practices when setting this up (treat it as if it were going into production). Part of the process should include the automation of loading the data into the storage that you have chosen.

### Process
* Created dockerfile to build the image and pushed image into the jFrog repository, and deployed it into the AKS cluster using helm charts
* Created helm charts and related deployment files which includes deployment files for running the application and postgres database. Also, created the respective service files under helm charts.
* To fetch the image from the Jfrog repository, I added a secret.yaml which is under Kubernetes folder.
* To add the database properties, I created a configmap which has the hostname and database name. (hostname is nothing but postgres service name)
* In kubernetes folder, there is a secretdb.yaml file which is used to override the database credentials both username and password, so that we are not sharing the sensitive information directly in the deployment files.

#### Below are the commands to manually deploy the application in AKS
Prerequisties: Make sure Azure kubernetes cluster is installed
  1. To login to the Cluster

     `az aks get-credentials --resource-group <resource-group-name> --name <aks-cluster-name>`
     
  2. Go to kuberbetes directory, deploy secrets and configmaps
     
     `kubectl create -f secret.yaml -n <namespace>` 
     
     `kubectl create -f secretdb.yaml -n <namespace>`
     
  3. Once after creating the image go to the kubernetes/helm folder, and change the image name in the values.yaml
  4. Deploy helm charts using below commands
     
     `helm install helm kubernetes/helm -n <namespace> --set imagename=<image_name>`
     
  5. Execute the database scripts by executing below commands
     `kubectl  cp python_database_scripts/userdb.sql  $(kubectl get pods -n <namespace> -o=name | grep postgres | sed "s/^.\{4\}//"):/ -n <namespace>`
           `kubectl  exec  -it $(kubectl get pods -n <namespace> -o=name | grep postgres | sed "s/^.\{4\}//") bash -n <namespace>  -- /bin/bash -c "psql -U postgres --file userdb.sql`
  6. Now run python scripts by replacing the value of API_URL and CSV_URL  using below command
      API_URL=<restapi-svc ipaddress:8080/users> CSV_URL=<path of csv file >python python_database_scripts/testscripts.py 
  
 #### Instead of deploying manually, I created a workflow to excute the above tasks.
  
  In the below screenshot, make sure to update the credentials or secrets as per your environment. Since I used the Azure for my deployment I have provided the required clientkey,   clientsecret, tenantId, etc, and same for the JFrog repository (docker_username, docker_registry, docker_password).
  
  <img width="1148" alt="githubsecret" src="https://github.com/yash1th25/scratchpay/assets/135289833/576a866d-2aa8-4274-97a1-622d75f1f4d8">
  
  Navigate to .github.workflows/cloud.yaml and change envirnoment variables if require 
  
  ![cloud_env](https://github.com/yash1th25/scratchpay/assets/135289833/9f13b437-fb04-4694-af49-7cb6a77da897)
  
  Go to the actions in the github and click on cloud deployment. Now run the workflow as shown below.
  
  ![cloud_deploy](https://github.com/yash1th25/scratchpay/assets/135289833/73ff72fe-ab39-4f7c-a3c8-9a41a17fcfa4)
  
  By executing this workflow project is excuted and image is build which is part of CI process. 
  In addition to that, pods and secrets are deployes in the kubernetes cluster.
  
  Now click on databasecreationscripts, which creates the schema into the postgress database.
  ![databasescripts](https://github.com/yash1th25/scratchpay/assets/135289833/a7dfe91b-8800-468e-8196-d803861e8e8b)
  
  
  
     
