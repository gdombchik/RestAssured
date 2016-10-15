Feature:  To test the JSONPlaceholder REST API using RestAssured.

@restAssured
Scenario:  Test the REST methods.
	Given Test the Get Rest method.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |
  | resource  | users/ |
  | id  | 1 |
  
 @restAssuredScenarioOutline
    Scenario Outline: Post Rest Request
    Given Post Rest Request by <value>
    | Field | Value |
	  | url  | http://jsonplaceholder.typicode.com/ |
	  | resource  | posts/ |
	  | userID  | 1 |
	  | id  | 101 |
	  | title  | new title |
	  | body  | new body |
	  | content type  | application/json |
	  | json schema  | posts-schema.json |

    # Post Rest Request by Object Mapping or JSON String
    Examples: 
      | value |
      | Object Mapping |
      | JSON String |