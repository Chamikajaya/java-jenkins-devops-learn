def gv  // * global variable that will be used to store the script file

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
        
        stage('Test') {
           
            steps {
                script {
                    gv.runTests(BRANCH_NAME)
                }
            }
        }

        stage('Build') {
             when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
               script {
                   gv.buildArtifact(BUILD_VERSION)
               }
            }
        }

        
        stage('Deploy') {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {

                    //  prompts the user to select a deployment environment &  user's choice is stored in env.DEPLOY_ENV.
                    // env.DEPLOY_ENV: This syntax explicitly adds the variable to the Jenkins environment variables. It will be available globally throughout the pipeline and in any subsequent steps or stages.  -->
                    env.DEPLOY_ENV = input message: "Select the environment to deploy", 
                                    ok: 'Deploy',
                                    parameters: [
                                        choice(name: 'ENV', choices: ['dev', 'stage', 'prod'], description: 'Select environment')
                                    ]
                    gv.deployProject(BRANCH_NAME, env.DEPLOY_ENV )
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
