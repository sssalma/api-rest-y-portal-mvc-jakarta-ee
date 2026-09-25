<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database SQL Load</h2>
        <%
            String dbname = "homework1";
            String schema = "ROOT";
            Connection con = null;
            Statement stmt = null;

            try {
                // Cargar el driver de la base de datos
                Class.forName("org.apache.derby.jdbc.ClientDriver");

                // Conexión a la base de datos
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
                stmt = con.createStatement();

                // Datos para insertar
                String[] data = new String[] {
                    "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob')",
                    "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'joana', 'joana')",
                    "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'salma', 'salma')",
                    "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'mery', 'mery')"
                    
                };
                
                // Insertar datos
                for (String datum : data) {
                    if (stmt.executeUpdate(datum) <= 0) {
                        out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                        return;
                    }
                    out.println("<pre> -> " + datum + "</pre>");
                }
                
                //recupero els id dels credencials
                    List<String> idCredencials = new ArrayList<String>();
                    ResultSet result0 = stmt.executeQuery("SELECT ID FROM CREDENTIALS");
                    while (result0.next()){
                    idCredencials.add(String.valueOf(result0.getInt("ID")));
                    }
                    out.println("IDs Credencials: ");
                    for(String j : idCredencials){
                    out.println("<pre> -> " + j + "</pre>");
                    }
                
                //DADES TOPIC
                String dataTopic[] = new String[]{
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Bitcoin')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Computer Science')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Blockchains')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'JavaScript')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'WebAssembly')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Artificial Intelligence')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Cybersecurity')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Data Science')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Cloud Computing')",
                    "INSERT INTO " + schema + ".TOPIC VALUES (NEXT VALUE FOR TOPIC_GEN, 'Software Architecture')"
            };
                for (String datum : dataTopic){
                    if (stmt.executeUpdate(datum) <= 0) {
                        out.println("<span class='error'>Error inserting topic: " + datum + "</span>");
                        return;
                    }
                    out.println("<pre> -> " + datum + "</pre>");
                }
                
                    //recupero els id dels topics
                    List<String> idTopics = new ArrayList<String>();
                    ResultSet result = stmt.executeQuery("SELECT ID FROM TOPIC");
                    while (result.next()){
                    idTopics.add(String.valueOf(result.getInt("ID")));
                    }
                    out.println("IDs Topics: ");
                    for(String s : idTopics){
                    out.println("<pre> -> " + s + "</pre>");
            }
            
               //DADES USUARI
               String dataUsuari[]=new String[]{
                    "INSERT INTO " + schema + ".USUARI VALUES (NEXT VALUE FOR USUARI_GEN,'Salma Jadiani', " + idCredencials.get(2) +")",
                    "INSERT INTO " + schema + ".USUARI VALUES (NEXT VALUE FOR USUARI_GEN,'Mery Gomez', " + idCredencials.get(3) +")",
                    "INSERT INTO " + schema + ".USUARI VALUES (NEXT VALUE FOR USUARI_GEN,'Miquel Ferrer', " + idCredencials.get(0) +")",
                    "INSERT INTO " + schema + ".USUARI VALUES (NEXT VALUE FOR USUARI_GEN,'Joana Hernandez', " + idCredencials.get(1) +")"
            };
            
                for (String datum : dataUsuari){
                    if (stmt.executeUpdate(datum) <= 0) {
                        out.println("<span class='error'>Error inserting usuari:  " + datum + "</span>");
                        return;
                    }
                    out.println("<pre> -> " + datum + "</pre>");
                }
                
                
                    //recupero els id dels usuaris
                    List<String> idUsuaris = new ArrayList<String>();
                    ResultSet result2 = stmt.executeQuery("SELECT ID FROM USUARI");
                    while (result2.next()){
                    idUsuaris.add(String.valueOf(result2.getInt("ID")));
                    }
                    out.println("IDs Usuaris: ");
                    for(String j : idUsuaris){
                    out.println("<pre> -> " + j + "</pre>");
                    }

                //DADES ARTICLE
                String dataArticle[] = new String[]{
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 1, '/Homework2/resources/img/JPAimg.png', 'Introducción a JPA', 'JPA (Java Persistence API) es una especificación de Java que facilita la gestión de datos relacionales en aplicaciones Java. En este artículo, exploraremos cómo configurar JPA en un proyecto, cómo definir entidades y cómo realizar operaciones CRUD. Además, veremos ejemplos prácticos de consultas JPQL y cómo optimizar el rendimiento de tu aplicación.', 'Cómo usar JPA en aplicaciones Java.', 12000, " + idUsuaris.get(3) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 0, '/Homework2/resources/img/Bitcoinimg.png', 'El pasado de la moneda del futuro.', 'Bitcoin, la primera criptomoneda, fue creada en 2009 por una persona o grupo bajo el seudónimo de Satoshi Nakamoto. Este artículo profundiza en la historia de Bitcoin, desde su creación hasta su adopción masiva. También discutiremos cómo funcionan las carteras digitales, los tipos de carteras (frías y calientes), y cómo almacenar tus Bitcoins de manera segura.', 'En este artículo se habla sobre las carteras digitales y la historia del Bitcoin.', 80, " + idUsuaris.get(1) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 0, '/Homework2/resources/img/WebAssemblyimg.png', 'WebAssembly y su evolución en el desarrollo web.', 'WebAssembly (Wasm) es una tecnología que permite ejecutar código de alto rendimiento en navegadores web. En este artículo, exploraremos cómo WebAssembly está cambiando el desarrollo web, permitiendo a los desarrolladores usar lenguajes como C, C++ y Rust para crear aplicaciones web rápidas y eficientes. También veremos casos de uso prácticos y cómo integrar WebAssembly en proyectos existentes.', 'En este artículo se habla sobre WebAssembly y cómo esta tecnología está revolucionando el desarrollo web.', 1000, " + idUsuaris.get(1) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 1, '/Homework2/resources/img/AIimg.png', 'IA y el futuro del desarrollo', 'La Inteligencia Artificial (IA) está transformando la forma en que desarrollamos software. En este artículo, discutiremos cómo la IA se utiliza en el desarrollo de software, desde la generación automática de código hasta la detección de errores. También exploraremos herramientas populares como GitHub Copilot y cómo la IA está impulsando la automatización en la industria tecnológica.', 'Explorando el impacto de la IA en el desarrollo de software.', 5000, " + idUsuaris.get(2) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 0, '/Homework2/resources/img/Cybersecurityimg.png', 'Ciberseguridad en la era digital', 'En un mundo cada vez más conectado, la ciberseguridad es más importante que nunca. Este artículo cubre las principales amenazas cibernéticas, como el phishing, el ransomware y los ataques DDoS. También proporcionaremos consejos prácticos para proteger tus datos en línea, incluyendo el uso de contraseñas seguras, la autenticación de dos factores y la importancia de mantener tus sistemas actualizados.', 'Principales amenazas y cómo protegerte en línea.', 3000, " + idUsuaris.get(1) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 0, '/Homework2/resources/img/DataScienceimg.jpg', 'Ciencia de Datos: La nueva revolución', 'La ciencia de datos es una disciplina que combina estadísticas, programación y conocimiento del dominio para extraer insights valiosos de los datos. En este artículo, exploraremos los fundamentos de la ciencia de datos, incluyendo el proceso de limpieza de datos, el análisis exploratorio y la creación de modelos predictivos. También discutiremos cómo la ciencia de datos está transformando industrias como la salud, las finanzas y el marketing.', 'Introducción a la ciencia de datos y su impacto en la industria.', 4500, " + idUsuaris.get(3) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 1, '/Homework2/resources/img/CloudComputingimg.png', 'Computación en la nube: Lo que debes saber', 'La computación en la nube ha revolucionado la forma en que las empresas almacenan y procesan datos. En este artículo, discutiremos los diferentes modelos de servicio en la nube (IaaS, PaaS, SaaS) y cómo elegir el adecuado para tu negocio. También cubriremos las ventajas de la nube, como la escalabilidad y la reducción de costos, así como los desafíos, como la seguridad y la dependencia del proveedor.', 'Ventajas y desafíos de migrar servicios a la nube.', 7000, " + idUsuaris.get(0) + ")",
    "INSERT INTO " + schema + ".ARTICLE VALUES (NEXT VALUE FOR ARTICLE_GEN, CURRENT_DATE, 0, '/Homework2/resources/img/SoftwareArchitectureimg.png', 'Diseño de software: Principios esenciales', 'El diseño de software es fundamental para crear aplicaciones escalables y mantenibles. En este artículo, exploraremos los principios clave de la arquitectura de software, como el principio de responsabilidad única (SRP), la inversión de dependencias (DI) y el patrón MVC. También veremos ejemplos prácticos de cómo aplicar estos principios en proyectos reales.', 'Patrones y principios clave para arquitecturas escalables.', 6000, " + idUsuaris.get(2) + ")"
};
                
                for (String datum : dataArticle){
                    if (stmt.executeUpdate(datum) <= 0) {
                        out.println("<span class='error'>Error inserting article:   " + datum + "</span>");
                        return;
                    }
                    out.println("<pre> -> " + datum + "</pre>");
                }
                    
                    //recupero els id dels articles
                    List<String> idArticles = new ArrayList<String>();
                    ResultSet result3 = stmt.executeQuery("SELECT ID FROM ARTICLE");
                    while (result3.next()){
                    idArticles.add(String.valueOf(result3.getInt("ID")));
                    }
                    out.println("IDs Articles:  ");
                    for(String k : idArticles){
                    out.println("<pre> -> " + k + "</pre>");
                    }
                
                    
                 //DADES RELACIO TOPIC_ARTICLE
                 // DADES RELACIO TOPIC_ARTICLE
String dataRelacio[] = new String[]{
    // Artículo 1: JPA
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(0) + "," + idTopics.get(1) + ")", // Computer Science
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(0) + "," + idTopics.get(9) + ")", // Software Architecture

    // Artículo 2: Bitcoin
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(1) + "," + idTopics.get(0) + ")", // Bitcoin
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(1) + "," + idTopics.get(2) + ")", // Blockchains

    // Artículo 3: WebAssembly
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(2) + "," + idTopics.get(4) + ")", // WebAssembly
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(2) + "," + idTopics.get(3) + ")", // JavaScript

    // Artículo 4: Inteligencia Artificial
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(3) + "," + idTopics.get(5) + ")", // Artificial Intelligence
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(3) + "," + idTopics.get(1) + ")", // Computer Science

    // Artículo 5: Ciberseguridad
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(4) + "," + idTopics.get(6) + ")", // Cybersecurity
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(4) + "," + idTopics.get(1) + ")", // Computer Science

    // Artículo 6: Ciencia de Datos
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(5) + "," + idTopics.get(7) + ")", // Data Science
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(5) + "," + idTopics.get(1) + ")", // Computer Science

    // Artículo 7: Computación en la Nube
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(6) + "," + idTopics.get(8) + ")", // Cloud Computing
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(6) + "," + idTopics.get(9) + ")", // Software Architecture

    // Artículo 8: Arquitectura de Software
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(7) + "," + idTopics.get(9) + ")", // Software Architecture
    "INSERT INTO " + schema + ".TOPIC_ARTICLE VALUES (" + idArticles.get(7) + "," + idTopics.get(1) + ")"  // Computer Science
};
                
                for (String datum : dataRelacio){
                    if (stmt.executeUpdate(datum) <= 0) {
                        out.println("<span class='error'>Error inserting relacio d'article amb topic: " + datum + "</span>");
                        return;
                    }
                    out.println("<pre> -> " + datum + "</pre>");
                }
                    
                    
                    
            } catch (Exception e) {
                out.println("<span class='error'>Error: " + e.getMessage() + "</span>");
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    out.println("<span class='error'>Error closing resources: " + e.getMessage() + "</span>");
                }
            }
        %>

        <button onclick="window.location = '<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
