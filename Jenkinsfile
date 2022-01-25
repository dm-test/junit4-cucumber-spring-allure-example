pipeline {
    options {
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '20', artifactNumToKeepStr: '20'))
    }
    agent { label 'autotests' }
    parameters {
        choice(
                name: 'LOG_LEVEL',
                choices: ['DEBUG', 'INFO', 'WARN', 'ERROR'],
                description: 'Passing the root log level'
        )
        string(
                name: 'THREADS_COUNT',
                defaultValue: '1',
                description: 'Passing the execution threads count'
        )
        string(
                name: 'CUCUMBER_TAGS',
                defaultValue: '',
                description: 'Passing the tags to be executed'
        )
    }
    stages {
        stage('Clone project') {
            steps {
                git branch: 'master', url: 'https://github.com/dm-test/junit4-cucumber-spring-allure-example.git'
            }
        }
        stage('Run tests') {
            environment {
                ALLURE_ENDPOINT = "https://allure.example.com"
                ALLURE_TOKEN = credentials('allureToken')
                ALLURE_PROJECT_ID = 1
                ALLURE_TIMEOUT = "2h"
            }
            steps {
                script {
                    sh "allurectl job-run plan --endpoint $ALLURE_ENDPOINT --output-file testplan.json"
                    def cucumberTags = params.CUCUMBER_TAGS
                    if (fileExists('testplan.json')) {
                        def testPlanStr = readFile(file: 'testplan.json')
                        def testPlan = readJSON text: testPlanStr
                        def tests = testPlan.tests
                        def idList = []
                        tests.each {
                            def tag = String.format("@allure.id:%s", it."id")
                            idList.add(tag)
                        }
                        cucumberTags = idList.join(" or ")
                    }
                    sh "allurectl watch --endpoint $ALLURE_ENDPOINT -- mvn clean test -DlogLevel=${params.LOG_LEVEL} -DthreadsCount=${params.THREADS_COUNT} ${cucumberTags.isEmpty() ? "" : "-Dcucumber.filter.tags='$cucumberTags'"} -s settings.xml"
                }
            }
            post {
                always {
                    allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                }
            }
        }
    }
    post {
        cleanup {
            cleanWs()
        }
    }
}