// * We can put the logic related to the pipeline in this file :)

def buildArtifact(buildVersion) {
   echo "Building the project version - ${buildVersion}"
}

def runTests() {
   echo 'Running the tests..'
}

def deployProject(branchName) {

    echo "This pipeline runs on the branch: ${branchName}"
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



return this