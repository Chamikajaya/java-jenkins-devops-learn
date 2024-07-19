pipeline {

    agent any  // will run on any available agent

    // environment variables define here will be available to all stages - so it is better to define the variables which are required in many stages of the pipeline
    environment {
        BUILD_VERSION = '1.0.0'
        BRANCH_NAME = "${env.GIT_BRANCH}" // get the branch name from the default list of  environment variables
    }

    stages{

        stage('Initialize') {
            steps {
                echo 'Initializing the project..'
                echo "The branch name is: ${BRANCH_NAME}"
            }
        }
        stage('Build') {
            steps {
                echo "Building the project with version: ${BUILD_VERSION}"  // ! need to use double quotes to use the variable
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the project..'
            }
        }

        stage('Deploy') {
            when 
            {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                echo "This pipeline runs on the branch: ${BRANCH_NAME}"
                echo 'Deploying the project..'
                withCredentials(
                    [
                        usernamePassword(
                            credentialsId: 'docker-hub', 
                            usernameVariable: 'USERNAME_DOCKER_VAR', 
                            passwordVariable: 'PASSWORD_DOCKER_VAR'
                            )
                    ]
                ) {
                    sh "echo 'publishing the docker image to ${USERNAME_DOCKER_VAR}/my-new-image'"
                }
            }
        }

  }

  post {
    always {
        echo 'I will always run'
    }
    success {
        echo 'I will run only if the pipeline is successful'
    }
    failure {
        echo 'I will run only if the pipeline is broken'
    }
    
  }



}