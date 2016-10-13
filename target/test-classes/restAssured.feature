Feature:  To test the JSONPlaceholder REST API using RestAssured.

Scenario:  Test the REST methods.
	When Test the Get Rest method.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |
  | resource  | users/ |
  | id  | 1 |
	Then Test the Put Rest method.
	| Field | Value |
  | url  | http://jsonplaceholder.typicode.com/ |