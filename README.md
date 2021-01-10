# api-integration
Camel ESB 구현

VM Option : -Dspring.profiles.active=local -Djasypt.encryptor.password=tom

계좌정보 조회
http://localhost:8080/api/bank/accounts?org_code=200&search_timestamp=2021010101&next_page=0&limit=100
Header :   user_id	/  chotom73

Multicast 
http://localhost:8080/api/multicast
Header :   user_id	/  chotom73
Body : {"key1": "test", "name" : "choyunjae"}

hello
http://localhost:8080/api/hello
Header :   user_id	/  chotom73
Header :   orgCode	/  333
Body : {"key1": "test", "name" : "choyunjae"}


http://localhost:8080/api/hell02/122/order
Header :   user_id	/  chotom73
Body : {"key1": "test", "name" : "choyunjae"}
