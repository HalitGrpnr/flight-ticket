# flight-ticket application
rest api for flight-ticket

- AIRPORT

1) get all airports

GET /airports

2) get airport by name

GET /airports/name/{name}

3) get airport by id

GET /airports/{id}

4) add airport

POST /airports

Airport JSON body: 
{
	"name": "airportName"
}

5) delete airport

DELETE /airports/{id}

6) update airport

PUT /airports

Airport JSON body:
{
  "id": "airportId",
  "name": "airportName"
}

---------------------------------------------------------------------------------------------------------------------

- COMPANY

1) get all companies

GET /companies

2) get company by name

GET /companies/name/{name}

3) get company by id

GET /companies/{id}

4) add company

POST /companies

Company JSON body:
{
	"name": "companyName"
}

5) delete company

DELETE /companies/{id}

6) update company

PUT /companies

Company JSON body:
{
	"id": "companyId",
	"name": "companyName",
	"flights": "companyFlights"
}

---------------------------------------------------------------------------------------------------------------------

- ROUTE

1) get all routes

GET /routes

2) get route by id 

GET /routes/{id}

3) get route by departure

GET /routes/departure/{name}

4) get route by destination

GET /routes/destination/{name}

5) add route 

POST /routes

Route JSON body:
{ 
  "departure": { 
    "name": "departureName"
  },
  "destination": { 
    "name": "destinationName"
  }
}

6) delete route 

DELETE /routes/{id}

7) update route 

PUT /routes

Route JSON body:
{
	"id": "routeId",
	"departure": {
		"id": "departureId",
		"name": "departureName"
	},
	"destination": {
		"id": "destinationId",
		"name": "destinationName"
	}
}

---------------------------------------------------------------------------------------------------------------------

- FLIGHT

1) get all flights

GET /flights

2) get flights by company name 

GET /flights/company/{name}

3) get flight by id 

GET /flights/{id}

4) add flight

POST /flights

Flight JSON body:
{
	"route": {
		"id": "routeId"
	},
	"company": {
		"name": "companyName"
	},
	"price": "flightPrice",
	"seatNumber": "seatNumber"
}

5) delete flight

DELETE /flights/{id}

6) update flight

PUT /flights

Flight JSON body:
{
	"id": "flightId",
	"route": {
		"id": "routeId",
		"departure": {
			"id": "departureId",
			"name": "departureName"
		},
		"destination": {
			"id": "destinationId",
			"name": "destinationName"
		}
	},
	"date": "flightDate",
	"price": "flightPrice",
	"seatNumber": "seatNumber"
}

---------------------------------------------------------------------------------------------------------------------

- TICKET

1) get all tickets

GET /tickets

2) get ticket by id 

GET /tickets/{id}

3) add ticket 

POST /tickets

Ticket JSON body:
{
	"flight": {
		"id": "flightId"
	},
	"creditCardNumber": "creditCardNumber",
	"status": "ticketStatus"
}

4) delete ticket

DELETE /tickets/{id}

5) update ticket

PUT /tickets

Ticket JSON body:
{
  "id": "ticketId",
  "flight": {
    "id": "flightId",
    "route": {
      "id": "routeId",
      "departure": {
        "id": "departureId",
        "name": "departureName"
      },
      "destination": {
        "id": "destinationId",
        "name": "destinationName"
      }
    },
    "date": "flightDate",
    "price": "flightPrice",
    "seatNumber": "seatNumber"
  },
  "creditCardNumber": "creditCardNumber",
  "status": "ticketStatus"
}
