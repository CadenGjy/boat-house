pipeline {
  agent none
  stages {
    stage('build') {
      agent 
      { 
          label 'vm-slave' 
      } 
      steps {
        sh 'docker build -f ./client/web/Dockerfile -t boat-house_client:test.'
      }
    }

  }
}