Feature: To test the JSONPlaceholder REST API using RestAssured.

  @getRequest
  Scenario: Test the Get REST method.
    Given Test the Get REST method.
      | Field    | Value                                |
      | url      | http://jsonplaceholder.typicode.com/ |
      | resource | users/                               |
      | id       |                                    1 |
      | name     | Leanne Graham			     						 	|
      | username | Bret								     						 	|
      | email		 | Sincere@april.biz	     						 	|
      | address.street		 | Kulas Light	     						 	|
      | address.suite		 | Apt. 556	     						 	|
      | address.city		 | Gwenborough	     						 	|
      | address.zipcode		 | 92998-3874	     						 	|
      | address.geo.lat		 | -37.3159	     						 	|
      | address.geo.lng		 | 81.1496	     						 	|
      | phone							 | 1-770-736-8031 x56442	     						 	|
      | website						 | hildegard.org	     						 	|
      | company.name			 | Romaguera-Crona	     						 	|
      | company.catchPhrase			 | Multi-layered client-server neural-net	     						 	|
      | company.bs			 | harness real-time e-markets	     						 	|

  @postRequest
  Scenario Outline: Test the Post REST method.
    Given Test the Post REST method by <value>
      | Field        | Value                                |
      | url          | http://jsonplaceholder.typicode.com/ |
      | resource     | posts/                               |
      | userID       |                                    1 |
      | id           |                                  101 |
      | title        | new title                            |
      | body         | new body                             |
      | content type | application/json                     |
      | json schema  | posts-schema.json                    |

    # Post REST Request by Object Mapping or JSON String
    Examples: 
      | value          |
      | Object Mapping |
      | JSON String    |

  @putRequest
  Scenario: Test the Put REST method.
    Given Test the Put REST method.
      | Field        | Value                                |
      | url          | http://jsonplaceholder.typicode.com/ |
      | resource     | posts/                               |
      | userID       |                                    1 |
      | id           |                                    1 |
      | title        | new title                            |
      | body         | new body                             |
      | content type | application/json                     |
      | json schema  | posts-schema.json                    |

  @deleteRequest
  Scenario: Test the Delete REST method.
    Given Test the Delete REST method.
      | Field        | Value                                |
      | url          | http://jsonplaceholder.typicode.com/ |
      | resource     | posts/                               |
      | id           |                                    1 |
      | content type | application/json                     |
