
# Stock-Price-Candle-Analysis---Spring-Boot-Project
## Description of the app
This app is a web application that provides a service for analyzing stock price movement.

### Sample code data
   {
      "lastTradeTime": "2023-07-21T10:30:00",
      "quotationLot": 100,
      "tradedQty": 5000,
      "openInterest": 200,
      "open": 100.5,
      "high": 102.0,
      "low": 100.0,
      "close": 101.0
    }

## Api Endpoints

* CustomerContoller:-
1. Register Customer
   Endpoint:- http://localhost:8089/customers (POST)
   Description:- This is for registering for an application.

2. Login
   Endpoint:- http://localhost:8089/customers/signIn (GET)
   Description:- This is authenticating the user.
* StockController:-
1. Save Candles to DB
   Save Stock Candle:
   HTTP Method: POST
   URL: URL: http://localhost:8089/api/stock-candles
   Description:- This is for storing data from JSON files to DB.

2. Get Stock Candle by ID:
   HTTP Method: GET
   URL: http://localhost:8089/api/stock-candles/{id}

3. Get All Stock Candles:
   HTTP Method: GET
   URL: http://localhost:8089/api/stock-candles/

4. Delete Stock Candle by ID:
   HTTP Method: DELETE
   URL: http://localhost:8089/api/stock-candles/{id}

5. Get Orb Candle Time:
   HTTP Method: GET
   URL: http://localhost:8089/api/stock-candles/orb-candle-time?minutes={minutes}

8. Generate Candles with Intervals:
   HTTP Method: GET
   URL: http://localhost:8089/api/stock-candles/generate-candles?intervalInMinutes={intervalInMinutes}

## TechStacks Used 
* SpringBoot
* Spring Data Japa
* Spring Security
* H2 Database
* JWT

## Features 

* Reading data from JSON file and mapping it to the specific object.
* Getting Opening Range Breakout (ORB) for a given time interval.
* Combining multiple candle data into a single unit and analysing it.
