pipeline {
    agent any

    // Definir variables de entorno para la conexión a la base de datos y nombres de archivos SQL
    environment {
        DB_SERVER = 'Gadalos'
        DB_USER = 'su'
        DB_PASSWORD = '982365'
        DB_NAME = 'Colegio'
        CREATE_DB_SCRIPT = 'crear_bd.sql'
        CREATE_TABLE_SCRIPT = 'crear_tabla.sql'
        INSERT_DATA_SCRIPT = 'insertar_datos.sql'
    }

    stages {
        // Etapa 1: Crear la base de datos
        stage('Create Database') {
            steps {
                script {
                    // Ejecutar el script para crear la base de datos
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d master -i ${CREATE_DB_SCRIPT}"
                }
            }
        }

        // Etapa 2: Crear la tabla en la base de datos
        stage('Create Table') {
            steps {
                script {
                    // Ejecutar el script para crear la tabla
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d ${DB_NAME} -i ${CREATE_TABLE_SCRIPT}"
                }
            }
        }

        // Etapa 3: Insertar datos en la base de datos
        stage('Insert Data') {
            steps {
                script {
                    // Ejecutar el script para insertar datos
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d ${DB_NAME} -i ${INSERT_DATA_SCRIPT}"
                }
            }
        }
    }
}
