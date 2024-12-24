# Kithub

Kithub is a RESTful API for a conceptual platform selling sports kits, designed to manage and provide information about football/soccer kits for sale. It is built with Spring Boot as it provides a quick, reliable and scalable backend for e-commerce applications.

## Endpoints
The endpoints allow to perform CRUD operations:
### Public endpoints
|Method |URL                   |Description                 |Sample Request Body |
|-------|----------------------|----------------------------|--------------------|
|**GET**| /api/v1/products      | Get a list of all the products|  |
|**GET**| /api/v1/products?nameOrDescription=''    | Get a list of all the products with its name or description containing the searched query string|  |
|**GET**| /api/v1/products?category=''    | Get a list of all the products with its category containing the searched query string|  |
|**GET**| /api/v1/products?supplier=''    | Get a list of all the products with its supplier as the searched query string|  |
|**GET**| /api/v1/products/{id} | Get a product by its ID of type UUID | |
|**GET**| /api/v1/categories | Get a list of all the category types| |
|**GET**| /api/v1/categories/{id} | Get a category by its ID| |
|**POST**| /api/v1/users/create_user  | Add user with basic role | |

### Admin endpoints [authenticated]
|Method |URL                   |Description                 |Sample Request Body |
|-------|----------------------|----------------------------|--------------------|
|**POST**| /api/v1/products    | Add a product              |   |
|**PUT**| /api/v1/products/{id}    | Update a product with given ID  |   |
|**DELETE**| /api/v1/products/{id}   | Delete a product with given ID  |   |

|**POST**| /api/v1/categories    | Add a category              |   |
|**PUT**| /api/v1/categories/{id}    | Update a category with given ID  |   |
|**DELETE**| /api/v1/categories/{id}   | Delete a category with given ID  |   |

|**GET**| /api/v1/users | Get a list of all the users | |
|**GET**| /api/v1/users/{id} | Get a user by its ID | |
|**POST**| /api/v1/users    | Add a user              |   |
|**PUT**| /api/v1/users/{id}    | Update a user with given ID  |   |
|**DELETE**| /api/v1/users/{id}   | Delete a user with given ID  |   |

|**POST**| /api/v1/users/create_admin | Add an admin user ||

