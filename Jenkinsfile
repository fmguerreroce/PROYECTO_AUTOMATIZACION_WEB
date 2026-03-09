pipeline {
    agent any

    //Trigger automático con webhook de GitHub
    triggers {
        githubPush() // configurar webhook en el repo
    }

    //Parámetros para ejecución selectiva de features y tags
    parameters {
        choice(
            name: 'FEATURE_FOLDER',
            choices: [
                '',
                'features',

            ],
            description: 'Seleccione carpeta de features (vacio = todas)'
        )

        choice(
            name: 'TAGS',
            choices: [
                '',
                '@SHOP',
                '@VALIDAR_PRECIO_TALLAS',
                '@VALIDAR_PROCESO_TOTAL',
                '@DATOS_PARAMETRIZADOS_CP-POSITIVOS_CP-NEGATIVOS'

            ],
            description: 'Seleccione el tag de Cucumber (vacio = todos)'
        )
    }

    stages {

        //Checkout del código
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/fmguerreroce/SerenityBDD.git'
            }
        }

        //Limpiar y ejecutar pruebas
        stage('Clean & Test') {
            steps {
                script {
                    // Comando base Maven
                    def command = "mvn clean verify"

                    // Si se especifica carpeta, la agregamos
                    if (params.FEATURE_FOLDER && params.FEATURE_FOLDER != '') {
                        command += " -Dcucumber.features=src/test/resources/features/${params.FEATURE_FOLDER}"
                    }

                    //Si se especifica tag, lo agregamos
                    if (params.TAGS && params.TAGS != '') {
                        command += " -Dcucumber.filter.tags=${params.TAGS}"
                    }

                    // Ejecuta el comando en Windows (bat)
                    bat command
                }
            }
        }

        //Generación de reporte Serenity
        stage('Generate Serenity Report') {
            steps {
                bat "mvn serenity:aggregate"
            }
        }

        //Publicación del reporte HTML en Jenkins
        stage('Publish Serenity Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: 'Serenity Report'
                ])
            }
        }
    }

    //Post-actions para notificaciones
    post {
        success {
            echo "Build y pruebas completadas correctamente"
        }
        failure {
            echo "Build o pruebas fallaron"
        }
    }
}