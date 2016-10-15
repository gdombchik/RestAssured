Feature:  To test the JSONPlaceholder REST API using RestAssured.

@getRequest
Scenario:  Test the Get REST method.
	Given Test the Get REST method.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |
  | resource  | users/ |
  | id  | 1 |
  
 @postRequest
 Scenario Outline: Test the Post REST method.
    Given Test the Post REST method by <value>
    | Field | Value |
	  | url  | http://jsonplaceholder.typicode.com/ |
	  | resource  | posts/ |
	  | userID  | 1 |
	  | id  | 101 |
	  | title  | new title |
	  | body  | new body |
	  | content type  | application/json |
	  | json schema  | posts-schema.json |

    # Post REST Request by Object Mapping or JSON String
    Examples: 
      | value |
      | Object Mapping |
      | JSON String |