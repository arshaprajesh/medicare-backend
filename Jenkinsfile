pipeline {
     agent any

     environment {
         IMAGE = "globalmedicare-backend"
         TAG = "latest"
         REGISTRY = "docker.io/arshabk"
     }
     stages {
         stage('Clone') {
             steps {
                 git branch: 'feature/addAppointment', url: 'https://github.com/arshaprajesh/medicare.git'
                 echo 'Repository cloned. Working from root directory.'
             }
         }
         /*stage('Ensure KIND Cluster Exists') {
             steps {
                 echo 'Checking for existing KIND cluster...'
                 sh '''
                      if kind get clusters | grep -q "kind"; then
                          echo "KIND cluster already exists. Continuing..."
                      else
                          echo "No cluster found. Creating new KIND cluster..."
                          kind create cluster
                          echo "KIND cluster created successfully."
                      fi
                 '''
             }
         }*/
         stage('Build') {
             steps {
                 sh './mvnw clean package -DskipTests'
                 echo 'JAR built successfully.'

             }
         }
         stage('Docker build'){
             steps {
                 sh 'docker build -t $REGISTRY/$IMAGE:$TAG .'
                 echo 'image successfully created.'

             }
         }
         stage('Docker push'){
             steps {
                 withCredentials([usernamePassword(credentialsId: 'dockerhub_creds',
                 usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                 sh 'echo $PASS | docker login -u $USER --password-stdin'
                 sh 'docker push $REGISTRY/$IMAGE:$TAG'
                 echo 'login successfully.'

                 }

             }
         }
         stage('deploy to kubernetes'){
             steps {
                 sh 'kubectl apply -f k8s/'
                 echo ' successfully deployed'

             }
         }
     }
}