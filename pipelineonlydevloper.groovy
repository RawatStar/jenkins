pipeline {
   agent none
   
   stages{
   stage ("1. Clone the git repo") {
      agent {
         label 'slave1'
      }
      steps {
         git branch: 'devloper', url: 'https://github.com/RawatStar/website.git'
      }
    }
    
    stage ("2. Remove all old image") {
      agent {
         label 'slave1'
      }
      steps {
         sh 'sudo docker rmi dev_website'
      }
    }
    
    stage ("3. Build Docker image") {
      agent {
         label 'slave1'
      }
      steps{
         sh "sudo docker build /home/ubuntu/jenkins/workspace/Job_1/ -t dev_website"
      }
   }
 }
}
