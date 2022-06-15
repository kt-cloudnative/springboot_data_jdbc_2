def PROJECT_NAME = "sample-api"
def SVC_NAME = "icis-samp-ppon"
def gitLabOrigin = "gitlab.dspace.kt.co.kr/sa-guide/sample-projects/${PROJECT_NAME}.git"
def gitLabUrl = "https://${gitLabOrigin}"
def imgRegistry =  "https://nexus.dspace.kt.co.kr"
def opsBranch = "dev"
def credentialsId = "SA-CICD-TEST"
def NEXUS_URL = 'https://nexus.dspace.kt.co.kr'
def gitLabAccessToken = "8XsBxG_DDHv5si9vCfAS"
def DATETIME_TAG = ""
///////////////////////
pipeline {

    agent {
        docker {
            image 'shclub/build-tool:v1.0.0'
            args '-u root:root -v /var/run/docker.sock:/var/run/docker.sock'
            registryUrl 'https://nexus.dspace.kt.co.kr'
            registryCredentialsId 'icistr-sa-nexus'
            reuseNode true
        }
    }

    stages {

        stage('Build') {
            steps {
                script{
                    DATETIME_TAG = new Date().format('yyyyMMddHHmmss')
                    docker.withRegistry(NEXUS_URL, "icistr-sa-nexus") {
                        configFileProvider([configFile(fileId: 'icis-tr-maven_setting', variable: 'maven_settings', targetLocation: '/home/jenkins/.m2/settings.xml')]) {
                            sh  """
                                chmod 777 ./mvnw
                                       skaffold build -p ${opsBranch} -t ${DATETIME_TAG}
                            """
                        }

                    }
                }
            }
        }

        stage('GitOps update') {
                           steps{
                                        print "======kustomization.yaml tag update====="
                                        script{
                                                     sh """
                    cd ~
                    git clone https://gitlab-ci-token:${gitLabAccessToken}@${gitLabOrigin}
                                                                  cd ./${PROJECT_NAME}/gitops
                                                                  kustomize edit set image nexus.dspace.kt.co.kr/argocd/${SVC_NAME}:${DATETIME_TAG}
                                                                  git config --global user.email "sa-admin@icistr.com"
                                                                  git config --global user.name "sa-admin"
                                                                  git add .
                                                                  git commit -am "update image tag ${DATETIME_TAG}"
                                                                 git push -u origin ${opsBranch}
                                                     """
                                        }
                                        print "git push finished !!!"
                           }
             }

    }
}
