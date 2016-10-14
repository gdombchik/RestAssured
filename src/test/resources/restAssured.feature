Feature:  To test the JSONPlaceholder REST API using RestAssured.

@restAssured
Scenario:  Test the REST methods.
	When Test the Get Rest method.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |
  | resource  | users/ |
  | id  | 1 |
  Then Test the Put Rest method by Object Mapping.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |
  | resource  | posts/ |
  | userID  | 1 |
  | id  | 101 |
  | title  | new title |
  | body  | new body |
  Then Test the Put Rest method by Response Body as String.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |
  | resource  | posts/ |
  | userID  | 1 |
  | id  | 101 |
  | title  | new title |
  | body  | new body |