<a id="readme-top"></a>
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="https://gtwsdscawhrmyhwjcuhl.supabase.co/storage/v1/object/public/movie_catalog_resources/1485231.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Movie Catalog Management API</h3>
  <p align="center">
    Spring Boot 3, Spring Security, JWT, JPA, Swagger UI
    <br />
    <a href="https://movie-catalog-api-ky7o.onrender.com/swagger-ui/index.html"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://movie-catalog-api-ky7o.onrender.com">Live Demo</a>
    ·
    <a href="https://api.jsonbin.io/v3/b/66f0a0afad19ca34f8aae3ea">Postman Collection</a>
  </p>
</div>

<!-- GETTING STARTED -->
### Users for testing
| Name        | User Name   | Password   | Role  |
|-------------|-------------|------------|-------|
| John Doe    | john_doe    | johnDoe24  | admin |
| Alice Brown | alice_brown | alice      | user  |

### Features
- [x] Multiple users can access to the endpoints
- [x] Admin and User roles are supported
- [x] Users can add, list and remove movie's rating
- [x] User Authentication with JWT token
- [x] Only admin can manage movies(add, update, delete)
- [x] Dynamic search implemented users can search by any movie's field, can order, paginate and filter the results
- [x] An endpoint to register new users is exposed
- [x] An endpoint to upload movie's poster is implemented
- [x] Swagger UI is implemented
- [x] Spring Security is implemented
- [x] Runtime and Custom Exceptions handled
- [x] Cache is implemented for search movie
- [x] Docker compose is implemented(only for server running no database for now)

#### Advanced Movie Search Example
```json
{
  "page":1,
  "maxResults":10,
  "sort":"asc",
  "sortBy":"title",
  "filters":[
    {
      "field":"synopsis",
      "value":"mystery",
      "operator":"LIKE"
    },
    {
      "field":"duration",
      "value":120,
      "operator":"EQUALS",
      "condition":"AND"
    }
  ]
}    
```
### Running Locally
With Maven:
`mvn spring-boot:run`

With Docker:
`docker compose --env-file .env up --build`.

The application will be available at http://localhost:8080

