pipeline {
    agent any

    // Definir variables de entorno para la conexión a la base de datos y nombres de archivos SQL
    environment {
        DB_SERVER = 'Gadalos'
        DB_USER = 'sa'
        DB_PASSWORD = '982365'
        DB_NAME = 'Colegio'
        CREATE_DB_SCRIPT = 'crear_bd.sql'
        CREATE_TABLE_SCRIPT = 'crear_tabla.sql'
        INSERT_DATA_SCRIPT = 'insertar_datos.sql'
        DELETE_DATA_SCRIPT = 'eliminar_datos.sql'
        SELECT_DATA_SCRIPT = 'seleccionar_datos.sql'
    }

    stages {
        // Establecer la codificación de caracteres al inicio
        stage('Set Encoding') {
            steps {
                script {
                    bat 'chcp 65001'  // Cambia la codificación a UTF-8
                }
            }
        }

        // Etapa 1: Crear la base de datos
        stage('Create Database') {
            steps {
                script {
                    // Ejecutar el script para crear la base de datos
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d master -f 65001 -i ${CREATE_DB_SCRIPT}"
                }
            }
        }

        // Etapa 2: Crear la tabla en la base de datos
        stage('Create Table') {
            steps {
                script {
                    // Ejecutar el script para crear la tabla
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d ${DB_NAME} -f 65001 -i ${CREATE_TABLE_SCRIPT}"
                }
            }
        }

        // Etapa 3: Eliminar todos los datos de la tabla
        stage('Delete Data') {
            steps {
                script {
                    // Ejecutar el script para eliminar todos los datos
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d ${DB_NAME} -f 65001 -i ${DELETE_DATA_SCRIPT}"
                }
            }
        }

        // Etapa 4: Insertar datos en la base de datos
        stage('Insert Data') {
            steps {
                script {
                    // Ejecutar el script para insertar datos
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d ${DB_NAME} -f 65001 -i ${INSERT_DATA_SCRIPT}"
                }
            }
        }

        // Etapa 5: Seleccionar todos los datos de la tabla 'estudiantes'
        stage('Select Data') {
            steps {
                script {
                    // Ejecutar el script para seleccionar todos los datos
                    bat "sqlcmd -S ${DB_SERVER} -U ${DB_USER} -P ${DB_PASSWORD} -d ${DB_NAME} -f 65001 -i ${SELECT_DATA_SCRIPT}"
                }
            }
        }
        
    }
}
