pipeline {
   //Donde se va a ejecutar el Pipeline
   agent {
       label 'Slave_Induccion'
   }
   
   options {
       buildDiscarder(logRotator(numToKeepStr: '3'))
       disableConcurrentBuilds()
   }
   
//   tools {
//       jdk 'JDK8'
//       gradle 'Gradle'
//   }
   node {
  jdk = tool name: 'JDK8'
  env.JAVA_HOME = "${jdk}"

  echo "jdk installation path is: ${jdk}"

  // next 2 are equivalents
  sh "${jdk}/bin/java -version"

  // note that simple quote strings are not evaluated by Groovy
  // substitution is done by shell script using environment
  sh '$JAVA_HOME/bin/java -version'
}

   stages{
       stage('Checkout') {
           steps{ 
               echo "------------>Checkout<------------"
               checkout([$class: 'GitSCM',
                        branches: [[name: '*/master']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        gitTool:'Git_Centos',
                        submoduleCfg: [],
                        userRemoteConfigs: [[credentialsId:'GitHub_julianmun0z',
                        url:'https://github.com/julianmun0z/reservation.git']]])
           }  
       }
       stage('Unit Tests') {
           steps{
               echo "------------>Unit Tests<------------"
               sh 'gradle test'
           }
       }
       stage('Integration Tests') {
           steps {
               echo "------------>Integration Tests<------------"
           }
       }
       stage('Static Code Analysis') {
           steps{
               echo '------------>Analisis de codigo estatico<------------'
               withSonarQubeEnv('Sonar') {
                   sh "${tool name: 'SonarScanner',type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"}
           }
       }
   
       stage('Build') {
           steps {
               echo "------------>Build<------------"
               sh 'gradle --b build.gradle build -x test'
           
           } 
       }
   }
 
	   post {
	         always {
	             echo 'This will always run'
	         }
	         success {
	             echo 'This will run only if successful'
	          junit 'build/test-results/test/*.xml'

	          }
	         failure {
	 		echo 'This will run only if failed' 
	 		mail (to: 'julian_munoz@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
	 		body: "Something is wrongwith ${env.BUILD_URL}")
	 		}
		 }
	       
  }