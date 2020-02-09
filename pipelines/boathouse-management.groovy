pipeline {
    agent none
    stages {
        stage('Build') { 
            agent 
            { 
                label 'vm-slave' 
            } 
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                            userRemoteConfigs: [[url: 'https://github.com/CadenGjy/boat-house.git']]])  
                sh 'cd /management/web'                                      
                sh 'docker build -f ./management/web/Dockerfile -t tool.devopshub.cn:2020/idcps/management:test .'
            }
        }
        stage('Dev') { 
            agent 
            { 
                label 'vm-slave' 
            }
            steps {
                sh 'echo hello world! Dev!'
            }
        }
        stage('Test') { 
            agent 
            { 
                label 'vm-slave' 
            }
            
            steps {
                input("Please confirm to deploy test env?")
                sh 'echo hello world! Test!'
            }
        }
        stage('Production') { 
            agent 
            { 
                label 'vm-slave' 
            }
            steps {
                input("Please confirm to deploy production env?")
                sh 'echo hello world! Deploy!'
            }
        }
    }
}