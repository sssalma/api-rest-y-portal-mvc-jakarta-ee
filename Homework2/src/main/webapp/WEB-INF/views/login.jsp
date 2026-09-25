<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f8e1f4;
            color: #5e345e;
            font-family: 'Lora', serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            padding: 0;
        }
        .main-content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
        }
        .login-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 350px;
            text-align: center;
        }
        .login-logo img {
            width: 120px;
            height: auto;
            margin-bottom: 15px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn-primary {
            background-color: #b65fcf;
            border: none;
            width: 100%;
        }
        .btn-primary:hover {
            background-color: #9230b0;
        }
        .btn-secondary {
            width: 100%;
        }
        footer {
            width: 100%;
            text-align: center;
            padding: 10px 0;
            background-color: #f8e1f4;
            margin-top: auto; /* Asegura que el footer se quede en la parte inferior */
        }
    </style>
</head>
<body>
    <div class="main-content">
        <div class="login-container">
            <div class="login-logo">
                <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="logo">
            </div>
            <h2>Iniciar sesión</h2>
            <%@ include file="/WEB-INF/views/layout/alert.jsp" %>
            <c:if test="${not empty alertMessage}">
                <div class="alert alert-${alertMessage.type}">
                    ${alertMessage.text}
                </div>
            </c:if>
            <form id="login-form" method="post" action="${pageContext.request.contextPath}/Web/LoginDisplay">
                <input type="hidden" name="id" value="${param.id}">
                <div class="form-group">
                    <label for="username">Usuario:</label>
                    <input type="text" id="username" name="username" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Iniciar sesión</button>
            </form>
            <a href="${pageContext.request.contextPath}/Web/ArticleDisplay" class="btn btn-secondary mt-3">Volver atrás</a>
        </div>
    </div>
    <footer>
        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
    </footer>
</body>
</html>