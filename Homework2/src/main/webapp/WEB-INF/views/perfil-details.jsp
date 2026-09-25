<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${Autor.nom}</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/custom-styles.css" rel="stylesheet">
    <style>
        /* Personalización adicional */
        body {
            font-family: 'Quicksand', sans-serif;
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 2rem;
        }

        h1 {
            font-size: 2.5rem;
            margin-bottom: 1.5rem;
        }

        .profile-info p {
            font-size: 1.25rem;
            margin: 0.5rem 0;
        }

        .btn {
            font-size: 1.25rem;
            margin-top: 1rem;
        }

        .img-fluid {
            max-width: 150px;
            height: auto;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <!-- Logo en la parte superior -->
    <div class="text-center mb-4">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo" class="img-fluid" />
    </div>

    <h1 class="text-center">Mi perfil</h1>

    <div class="container text-center">
        <div class="profile-info">
            <p><strong>Identificatiu:</strong> ${autor.id}</p>
            <p><strong>Nom:</strong> ${autor.nom}</p>
        </div>
        <a href="${pageContext.request.contextPath}/Web/ArticleDisplay" class="btn btn-secondary">Volver a los artículos</a>
    </div>

    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</body>
</html>
