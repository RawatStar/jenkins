pipeline {
   agent none
   
   stages{
   stage ("1. Clone the git repo") {
      agent {
         label 'slave1'
      }
      steps {
         git branch: 'master', url: 'https://github.com/RawatStar/website.git'
      }
    }
    
    stage ("2. Remove all running container") {
      agent {
         label 'slave1'
      }
      steps {
         sh 'sudo docker rm -f $(sudo docker ps -a -q)'
      }
    }
    
    stage ("3. Build Docker image") {
      agent {
         label 'slave1'
      }
      steps{
         sh "sudo docker build /home/ubuntu/jenkins/workspace/website_job/ -t website"
      }
   }
   
    stage ("4. Running website on port 82") {
      agent {
         label 'slave1'
      }
      steps {
         sh "sudo docker run -it -d -p 82:80 website"
      }
    }
  }
}