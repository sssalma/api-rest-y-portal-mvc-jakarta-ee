<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Llista d'articles</title>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/custom-styles.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <!-- Incluye Font Awesome para íconos -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body>
        <div class="container" id="articles-container">
            <c:if test="${not empty alertMessage}">
                <div class="alert alert-${alertMessage.type}" id="alert-message">
                    ${alertMessage.text}
                </div>
            </c:if>

            <!-- Logo grande en el centro de la cabecera -->
            <div class="text-center my-5">
                <img src="${pageContext.request.contextPath}/resources/img/logo.png" 
                     alt="logo" 
                     class="img-fluid rounded-circle shadow-lg" 
                     style="width: 200px; height: 200px;"> 
            </div>

                     
                     
            <div class="d-flex justify-content-between align-items-center mb-4">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <div class="d-flex align-items-center">
                            <span class="h4 mb-0 text-primary">
                                <i class="fas fa-smile-wink mr-2"></i> <!-- decoracio -->
                                Benvingut@, 
                                <a href="${pageContext.request.contextPath}/Web/UserProfile?username=${sessionScope.user}" 
                                   class="text-decoration-none font-weight-bold" id="user-profile-link">
                                    ${sessionScope.user}
                                </a>!
                            </span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/Web/LoginDisplay?id=${article.id}" 
                           class="btn btn-primary btn-sm" id="login-button"> 
                            <i class="fas fa-sign-in-alt mr-2"></i>Inicia Sessió
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Formulario de filtros -->
            <form method="get" action="ArticleDisplay" class="mb-4" id="filter-form">
                <div class="form-row">
                    <div class="col-md-4">
                        <label for="autor">Filtrar por autor</label>
                        <input type="text" name="autor" id="autor" class="form-control" value="${autorSeleccionat}" placeholder="Escribe el nombre del autor">
                    </div>
                    <div class="col-md-4">
                        <label for="topic">Filtrar por tópico</label>
                        <input type="text" name="topic" id="topic" class="form-control" value="${topicSeleccionat}" placeholder="Escribe el nombre del tópico">
                    </div>
                    <div class="col-md-4 align-self-end">
                        <button type="submit" class="btn btn-primary" id="apply-filters-button">Aplicar filtros</button>
                    </div>
                </div>
            </form>

            <!-- Contenidor d'articles -->
            <div id="ContenidorArticles" class="row">
                <%@ include file="/WEB-INF/views/layout/alert.jsp" %>
                <c:forEach var="article" items="${articles}">
                    <div class="col-md-4 mb-4" id="article-${article.id}">
                        <div class="card">
                            <c:choose>
                                <c:when test="${!article.esPublic and empty sessionScope.user}">
                                    <a href="${pageContext.request.contextPath}/Web/LoginDisplay?id=${article.id}">
                                        <img src="${article.imatge}" class="card-img-top img-fluid" alt="${article.titol}" id="article-image-${article.id}">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/Web/ArticleDisplay/${article.id}">
                                        <img src="${article.imatge}" class="card-img-top img-fluid" alt="${article.titol}" id="article-image-${article.id}">
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body">
                                <h5 class="card-title" id="article-title-${article.id}">${article.titol}</h5>
                                <p class="card-text" id="article-summary-${article.id}">${article.resum}</p>
                                <p class="text-muted" id="article-author-${article.id}">Autor: ${article.autor}</p>
                                <p class="text-muted" id="article-date-${article.id}">Publicado: ${article.data}</p>
                                <p class="text-muted" id="article-views-${article.id}">Visitas: ${article.views}</p>
                                <c:if test="${!article.esPublic}">
                                    <span class="private-icon" id="private-icon-${article.id}">&#128274;</span> Privado
                                </c:if>
                                <c:choose>
                                    <c:when test="${!article.esPublic and empty sessionScope.user}">
                                        <a href="${pageContext.request.contextPath}/Web/LoginDisplay?id=${article.id}" class="btn btn-primary mt-2" id="login-to-view-${article.id}">Iniciar sesión para ver detalles</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/Web/ArticleDisplay/${article.id}" class="btn btn-primary mt-2" id="view-details-${article.id}">Ver detalles</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
    </body>
</html>