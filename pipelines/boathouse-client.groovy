pipeline {
    agent none
    stages {
        stage('Build') { 
            agent 
            { 
                node{
                    label 'vm-slave' 
                    customWorkspace '/client/web'
                }

            } 
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                            userRemoteConfigs: [[url: 'https://github.com/CadenGjy/boat-house.git']]])                
                sh 'docker build -f ./client/web/Dockerfile -t tool.devopshub.cn:2020/idcps/client:${env.BUILD_ID} .'
            }
        }
        stage('Dev') { 
            agent 
            { 
                label 'vm-slave' 
            }
            steps {
                sh 'docker logins tool.devopshub.cn:2020 -u admin -p "admin"'
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