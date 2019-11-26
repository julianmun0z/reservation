pipelineJob("Ceiba-Estacionamiento(julian.munoz)") {
	description()
	keepDependencies(false)
	definition {
		cpsScm {
			scm {
				git {
					remote {
						github("julianmun0z/reservation", "https")
						credentials("GitHub_julian")
					}
					branch("*/master")
				}
			}
			scriptPath("Jenkinsfile")
		}
	}
	disabled(false)
	configure {
		it / 'properties' / 'jenkins.model.BuildDiscarderProperty' {
			strategy {
				'daysToKeep'('-1')
				'numToKeep'('3')
				'artifactDaysToKeep'('-1')
				'artifactNumToKeep'('-1')
			}
		}
	}
}