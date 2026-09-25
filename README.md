# Portal de artículos técnicos — API REST y cliente MVC en Jakarta EE

Portal de publicación de artículos técnicos construido en dos entregas complementarias
sobre **Jakarta EE**: primero el servicio REST que expone los datos, después la
aplicación web MVC que los consume.

> Prácticas de *Aplicacions i Serveis Web* (Grup 57) — Grau en Enginyeria Informàtica, URV.

## Estructura

| Carpeta | Entrega | Qué es |
|---|---|---|
| `Homework1/` | 1 | **Servicio REST** con JAX-RS + JPA, desplegado en GlassFish |
| `Homework2/` | 2 | **Aplicación web MVC** con Jakarta MVC + JSP que consume el servicio |
| `Memoria.pdf` | — | Memoria conjunta de ambas entregas |

## Homework 1 — Servicio REST

API REST sobre el modelo de dominio del portal, con persistencia JPA.

**Modelo** (`model/entities/`): `Article`, `Topic`, `Usuari` y un `ArticleDTO` que
desacopla la representación expuesta por la API de la entidad persistida.

**Servicios** (`service/`): facades REST (`ArticleFacadeREST`, `UsuariFacadeREST`)
construidos sobre un `AbstractFacade<T>` genérico que factoriza el CRUD común, como
EJB stateless con un `EntityManager` inyectado.

**Autenticación** (`authn/`): filtro `RESTRequestFilter` que intercepta las peticiones
con prioridad `Priorities.AUTHENTICATION` y valida credenciales **HTTP Basic** contra la
base de datos. La anotación `@Secured` marca qué recursos requieren autenticación, de
modo que el filtro solo actúa donde corresponde.

Construcción con Ant (`build.xml`) y despliegue en **GlassFish**; los recursos JDBC se
declaran en `web/WEB-INF/glassfish-resources.xml` y la unidad de persistencia en
`src/conf/persistence.xml`.

## Homework 2 — Aplicación web MVC

Cliente web del portal, ya con **Maven** y **Jakarta MVC**.

- **Controladores** (`controller/`): `ArticleController`, `LoginController`,
  `UserController`.
- **Servicios** (`service/`): `ArticleServiceImpl` y `UserServiceImpl` tras sus
  interfaces, inyectados por CDI.
- **Modelo** (`model/`): `Article`, `ArticleID`, `Autor`, `Topic`, `Credential`,
  `AlertMessage` y `SignUpAttempts` — esta última registra los intentos de alta.
- **Vistas** (`WEB-INF/views/`): JSP con Bootstrap — listado y detalle de artículos,
  login, registro con confirmación, perfil de usuario y una página de error 404.
- **Configuración externalizada**: `Config.properties` leído mediante un
  `PropertyProducer` CDI con su `@Property` y una `PropertyException` propia, en vez de
  constantes repartidas por el código.

## Cómo compilar y desplegar

Ambas entregas necesitan un **GlassFish 7** (o compatible con Jakarta EE 10) y una base
de datos relacional registrada como recurso JDBC.

**Homework 1** (Ant / NetBeans):

```bash
cd Homework1
ant dist          # genera el .war en dist/
```

**Homework 2** (Maven):

```bash
cd Homework2
mvn clean package # genera el .war en target/
```

Despliega los `.war` resultantes en GlassFish. Antes de arrancar, revisa la unidad de
persistencia (`persistence.xml`) de cada módulo y el `Config.properties` del Homework 2
para apuntar a tu base de datos y a la URL del servicio REST.

## Stack

Java · Jakarta EE 10 · JAX-RS · JPA · EJB · CDI · Jakarta MVC · JSP · Bootstrap · GlassFish · Ant · Maven
