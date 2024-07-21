def gv  // * global variable

pipeline {

    agent any  
    environment {
        BUILD_VERSION = '1.0.0'
        BRANCH_NAME = "${env.GIT_BRANCH}" // get the branch name from the default list of environment variables
    }

    stages {
        stage('Initialize') {
            steps {
                script {
                    gv = load 'script.groovy'  // * load the script file
                }
            }
        }

        stage('Build') {
            steps {
               script {
                   gv.buildArtifact(BUILD_VERSION)
               }
            }
        }

        stage('Test') {
            steps {
                script {
                    gv.runTests()
                }
            }
        }

        stage('Deploy') {
            when {
                expression {
                    BRANCH_NAME == 'origin/main'
                }
            }
            steps {
                script {
                    gv.deployProject(BRANCH_NAME)
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline is successful'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
