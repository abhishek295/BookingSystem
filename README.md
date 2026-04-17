# BookingSystem
Ticket Booking System using Java Spring Boot, REST APIs via dedicated controllers. The system supports ticket booking management including event creation, seat holds, booking confirmation,and cancellation, backed by an in-memory H2 DB. It implements controls such as temporary seat reservation to prevent double booking and automatic expiry of holds.


Each of the required endpoints (APIs) are available in the relevant controllers BookingController and EventController classes. 
For db I have used an in-memory db H2. 

There is a BookingService class which does the required operations after being called upon by the controller.  
 
Constraints for avoiding double booking, expiring holds after a certain period and soft delete a booking , have been implemented. 

 

Sample curl calls below 

To create an event  

curl --location 'http://localhost:8080/events/create' \ 

--header 'Content-Type: application/json' \ 

--data '{ 

"name": "Drama", 

"location": "MG Road", 

"totalSeats": 100, 

"availableSeats": 100, 

"date": "2026-05-10T18:30:00"  

}' 

 

To create a hold 

curl --location 'http://localhost:8080/api/holds' \ 

--header 'Content-Type: application/json' \ 

--data '{ 

"eventId" : 1, 

"seats" : 8, 

"userId" : 121 

 

}' 

 

To confirm a booking, given a hold id 

curl --location --request POST 'http://localhost:8080/api/bookings/confirm?holdId=9' 

 

To cancel a booking 

curl --location --request POST 'http://localhost:8080/api/bookings/cancel?bookingId=11' 

 

To get details of an event 

curl --location 'http://localhost:8080/events/get?eventId=1' 

 

 
 

 
 

 
