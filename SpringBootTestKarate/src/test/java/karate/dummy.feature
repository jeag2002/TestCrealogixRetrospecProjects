Feature: Test API Rest
	
	Background:
		* url baseUrl
		
	Scenario: Get List 
		
		Given path 'api/users'
		When method GET
		Then status 200
		And match $.length() == 2
		
	Scenario Outline: Get Element of List
		
		Given path 'api/users/<id>'
		When method GET
		Then status 200
		And match $ contains {id:<id_return>}
		
		Examples:
		| id | id_return |
		| 0  | 0         |
		| 1  | 1         |
	
	