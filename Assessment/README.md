# Steps to run this assessment

1] Take the clone of this repo

2] Please use Intellij Idea IDE to run this code

3] I have integrated with (https://www.exchangerate-api.com/)
and this is final url along with my apikey : (https://v6.exchangerate-api.com/v6/6440e9dd70cf08ed1a23b8a6/latest/USD)

4] As tomcat server is already embedded,directly run with coverage for tests report and summary

5] We can also test this code with postman but please make sure to use following things :

    Auth Type :Basic Auth 
    Username  : user  
    Password  : password


6] Please take this curl as a reference to test assessment on Postman  :
        `curl --location 'http://localhost:8123/api/calculate' \
        --header 'Content-Type: application/json' \
        --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
        --data '{
        "id":"3121",
        "items":[{
        "id":"1",
        "category":"GROCERIES",
        "price":"90.00"
        }],
        "totalAmount":"90.00",
        "user":{
        "id":"17985374",
        "name":"Nikhil",
        "type":"EMPLOYEE",
        "customerTenureInYears":"1"
        },
        "originalCurrency":"INR",
        "targetCurrency":"USD"
        }'`





