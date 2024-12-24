
# Kithub

Kithub is a RESTful API for a conceptual platform selling sports kits, designed to manage and provide information about football/soccer kits for sale. It is built with Spring Boot as it provides a quick, reliable and scalable backend for e-commerce applications.






## Endpoints

### Public Endpoints

#### Get all products

```http
  GET /api/v1/products
```

| Query Parameters | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nameOrDescription` | `string` |  |
|  `category` | `string` | |
|`supplier` | `string` | |

#### Get product by ID

```http
  GET /api/v1/products/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of item to fetch |


#### Get all categories

```http
  GET /api/v1/categories
```


#### Get category by ID

```http
  GET /api/v1/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of category to fetch |

#### Create a new user

```http
  POST /api/v1/users/create_user
```

_Sample Request Body:_
```
{
    "username" : "someUsername",
    "password" : "samplePassword"
}
```
