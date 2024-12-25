
# Kithub

Kithub is a RESTful API for a conceptual platform selling sports kits, designed to manage and provide information about football/soccer kits for sale. It is built with Spring Boot as it provides a quick, reliable and scalable backend for e-commerce applications.






## Endpoints

### Public Endpoints

#### Get all products

```
  GET /api/v1/products
```

| Query Parameters | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nameOrDescription` | `string` | Filter products by text containing in the name or description. |
|  `category` | `string` | Filter products by category name.|
|`supplier` | `string` |	Filter products by the supplier's name. |

#### Get product by ID

```
  GET /api/v1/products/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of item to fetch |


#### Get all categories

```
  GET /api/v1/categories
```


#### Get category by ID

```
  GET /api/v1/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of category to fetch |

#### Create a new user

```
  POST /api/v1/users/create_user
```

> * _Sample Request Body:_
```JSON
{
    "username" : "someUsername",
    "password" : "samplePassword"
}
```


### Admin Endpoints (authenticated)

#### Add a product

```
  POST /api/v1/products
```
> * _Sample Request Body:_
```JSON
 {
        "productName": "Real Madrid Away Kit 2024/25",
        "description": "Away kit of the reigning European champions",
        "price": 1400.00,
        "quantity":60,
        "supplier":"Adidas",
        "category": {
            "categoryId": 1
        },
        "regions": ["ZA","BD", "AU","CA","US"]
    }
```
#### Update a product

```
  PUT /api/v1/products/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of item to update |

> * _Sample Request Body:_
```JSON
 {
        "productName": "Real Madrid Away Kit 2024/25",
        "description": "Away kit of the reigning European champions",
        "price": 1200.00,
        "quantity":40,
        "supplier":"Adidas",
        "category": {
            "categoryId": 1
        },
        "regions": ["ZA","AU","CA","US"]
    }
```

#### Delete a product

```
  DELETE /api/v1/products/${id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of item to delete |


#### Increment product quantity

```
  PATCH /api/v1/products/${id}?increase={amount}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of product to update |
| `amount`|`int`|**Required**. Amount to increment the quantity of the product by|

#### Decrement product quantity

```
  PATCH /api/v1/products/${id}?decrease={amount}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of product to update |
| `amount`|`int`|**Required**. Amount to decrement the quantity of the product by|


#### Add a category

```
  POST /api/v1/categories
```
> * _Sample Request Body:_
```JSON
    {
        "categoryName" : "shoes"
    }
```

#### Update a category

```
  PUT /api/v1/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of category to update |

> * _Sample Request Body:_
```JSON
    {
        "categoryName" : "footwear"
    }
```


#### Delete a category

```
  DELETE /api/v1/categories/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of category to delete |


#### Get all users

```
  GET /api/v1/users
```


#### Get user by ID

```
  GET /api/v1/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of user to fetch |

#### Create a new Admin

```
  POST /api/v1/users/create_admin
```

> *  _Sample Request Body:_
```
{
    "username" : "someUsername",
    "password" : "samplePassword"
}
```

#### Update user

```
  PUT /api/v1/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of user to update |

> *  _Sample Request Body:_
```JSON
{
    "username" : "someUsername",
    "password" : "samplePassword",
    "role" : "BASIC"
}
```

#### Delete user

```
  PUT /api/v1/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of user to delete |
