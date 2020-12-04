# Task 1 - Trader tax
## Solution Description
This is a REST service using Spring. It accepts a JSON body in POST request of style:
```json
{
     "traderId": 1,
     "playedAmount": 5,
     "odd": 1.5
   }
```
It outputs possible return amount depending on which endpoint it's being called on. There are 4 endpoints:
- /taxGeneral/byAmount
- /taxGeneral/byRate
- /taxWinnings/byAmount
- /taxWinnings/byRate

Calling first endpoint  with presented input JSON structure would give us this response body:
 ```json
{
  "possibleReturnAmount": 7.5,
  "possibleReturnAmountBefTax": 7.5,
  "possibleReturnAmountAfterTax": 5.5,
  "taxRate": 0.1,
  "taxAmount": 2.0
}
```
Each trader has their own taxRate and taxAmount displayed in response body. For testing purposes there are 3 traders which can be shown on endpoint ```/traders```.
```json
[
  {
    "name": "First",
    "taxAmount": 2.0,
    "taxRate": 0.1,
    "id": 1
  },
  {
    "name": "Second",
    "taxAmount": 4.0,
    "taxRate": 0.2,
    "id": 2
  },
  {
    "name": "Third",
    "taxAmount": 6.0,
    "taxRate": 0.3,
    "id": 3
  }
]
```

## Usage
In root directory you'll find a file named ```requests.http``` which test running endpoints. After running command in terminal, check endpoints using postman or similar software.
### Run following commands
```shell
./gradlew bootRun
```

