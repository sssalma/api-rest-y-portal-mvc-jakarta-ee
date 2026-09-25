<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${article.titol}</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/custom-styles.css" rel="stylesheet">
    <style>
        /* Personalización adicional */
        body {
            font-family: 'Quicksand', sans-serif;
            background-color: #f8f9fa; /* Mantener el color de fondo */
        }

        .container {
            margin-top: 3rem;
        }

        h2 {
            font-size: 2.5rem;
            margin-bottom: 1.5rem;
        }

        .top-right {
            text-align: right;
            margin-bottom: 1rem;
        }

        .top-right a {
            font-size: 1.25rem;
            color: #6a1b9a;
            text-decoration: none;
        }

        .top-right span {
            font-size: 1.25rem;
        }

        .img-circle {
            max-width: 80%; /* Aumentar el tamaño de la imagen */
            height: auto;
            border-radius: 50%;
        }

        .row {
            margin-top: 2rem;
        }

        .row p {
            font-size: 1.25rem;
        }

        .btn-secondary {
            font-size: 1.25rem;
            margin-top: 1.5rem;
        }

        .mt-4 {
            margin-top: 2rem;
        }

        /* Estilo para el logo */
        .logo-container {
            text-align: center;
            margin-bottom: 2rem;
        }

        .logo {
            width: 150px; /* Tamaño del logo */
            height: 150px;
            border-radius: 50%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Ajuste para la imagen del artículo */
        .article-image {
            max-width: 100%;
            height: auto;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <!-- Logo -->
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo" class="logo">
        </div>

        <!-- Mensaje de bienvenida o botón de inicio de sesión -->
        <div class="top-right">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <span>Benvingut, <a href="${pageContext.request.contextPath}/Web/UserProfile?username=${sessionScope.user}">
                            ${sessionScope.user}
                        </a>!</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/Web/LoginDisplay?id=${article.id}" class="btn-login">Inicia Sessió</a>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Títol -->
        <h2 class="text-center">${article.titol}</h2>

        <!-- Imagen del artículo -->
        <div class="text-center mt-4">
            <img src="${article.imatge}" class="article-image" alt="Imagen del artículo">
        </div>

        <!-- Informació -->
        <div class="row mt-4">
            <div class="col-md-6 offset-md-3">
                <p><strong>Autor:</strong> ${article.autor.nom}</p>
                <p><strong>Fecha:</strong> ${article.data}</p>
                <p><strong>Vistas:</strong> ${article.views2}</p>
                <p><strong>Topics:</strong> ${article.topics}</p>
            </div>
        </div>

        <div class="mt-4">
            <p>${article.text}</p>
        </div>

        <!-- Botón para volver atrás -->
        <a href="${pageContext.request.contextPath}/Web/ArticleDisplay" class="btn btn-secondary mt-4">Volver atrás</a>
    </div>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>