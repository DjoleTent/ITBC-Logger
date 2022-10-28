## Project Name:  ITBC-Logger
About: ITBC-Logger application is about creating logs by Client and determine diffrent approach to data.

Technologies:
- Java version 17
- H2 Database
- Spring Boot version 2.7.4
  (Dependencies: Spring WEB, Spring Data JPA, Spring H2 database, Spring Lombok)

(All queries used in this application are in _SQL(H2) Query Help_ file)

## Client
List of endpoints for Client:
1. Register
    - HTTP Method: `POST`
    - Endpoint URL: `/api/clients/register`
    - Request body:
        ```json
        {
            "username": "string",
            "password": "string",
            "email": "string"
        }
        ```
    - Responses:
        - 201 - Registered
        - 400 - Bad Request
            - email must be valid
            - username at least 3 characters
            - password at least 8 characters and one letter and one number
        - 409 - Conflict
            - username already exists
            - email already exists

2. Login (account ->   email or username)
    - HTTP Method: `POST`
    - Endpoint URL: `/api/clients/login`
    - Request body:
        ```json
        {
    
            "account": "string", 
            "password": "string"
        }
        ```
    - Responses: ( uuid* || JWT || username  -> token)
        - 200 - OK
      

            ```json
            {
                "token": "string" 
            }
            ```
        - 400 - Bad Request
            - Email/Username or password incorrect

3. Create log
    - HTTP Method: `POST`
    - Endpoint URL: `/api/logs/create`
    - Request body:
        ```json
        {
            "message": "string",
            "logType": 0
        }
        ```
    - Request headers:
        - `Authorization` - token
    - Responses:
        - 201 - Created
        - 400 - Bad Request
            - Incorrect logType
        - 401 - Unauthorized
            - Incorrect token
        - 413 - Payload too large
            - Message should be less than 1024

4. Search logs
    - HTTP Method: `GET`
    - Endpoint URL: `/api/logs/search`
    - Request params:
        - `dateFrom` - date
        - `dateTo` - date
        - `message` - string
        - `logType` - int
    - Request headers:
        - `Authorization` - token
    - Responses:
        - 200 - OK
            ```json
            [
              {
                "message": "string",
                "logType": 0,
                "createdDate": "date"
              }  
            ]
            ```
        - 400 - Bad request
            - Invalid dates
            - Invalid logType
        - 401 - Unauthorized
            - Incorrect token

<div style="page-break-after: always;"></div>

## Admin

List od endpoints for Admin:

1. Get all clients
    - HTTP Method: `GET`
    - Endpoint URL: `/api/clients`
    - Request headers:
        - `Authorization` - token (Admin token)
    - Responses:
        - 200 - OK
            ```json
            [
              {
                "id": "uuid",
                "username": "string",
                "email": "string",
                "logCount": 0
              }  
            ]
            ```
        - 401 - Unauthorized
            - Correct token, but not admin
        - 403 - Forbidden
            - Incorrect token

2. Change client password
    - HTTP Method: `PATCH`
    - Endpoint URL: `/api/clients/{clientId}/reset-password`
    - Request body:
        ```json
        {
            "password": "string"
        }
        ```
    - Request headers:
        - `Authorization` - token (Admin token)
    - Responses:
        - 204 - No content
        - 401 - Unauthorized
            - Correct token, but not admin
        - 403 - Forbidden
            - Incorrect token


 Author: [**Djordje** **Djordjevic**](https://www.linkedin.com/in/djordje-djordjevic-3a3b791b3/) [_ITBootcamp_](https://itbootcamp.rs/) trainee