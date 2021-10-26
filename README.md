# Death Star

Project realised during Le Campus Numérique's Java slot.
**DEATH STAR** is the central API responsible making the liaison between the Front (Skywalker), the Vehicles(Rogue Squadron) and the Clients(Rebels).
Death Star features and goal:
- Receive API call from the front
- Fetches available vehicles corresponding to the wanted dates
- Validates some conditions for the possibility of booking a car
- Book a vehicle for a duration and a price

The goal is to create a car booking app with a microservice architecture with Java SpringBoot.  

We defined 4 services:
- **SKYWALKER**: responsible to serve views - [here](https://github.com/Luc-DuboisP/skywalker)
- **ROGUE SQUADRON**: API serving vehicles list - [here](https://github.com/Lauric-h/rogue-squadron)
- **REBELS**: API for clients - [here](https://github.com/Lauric-h/rebels)
- **DEATH STAR**: API booking and central API calling the other two - You're here!
  
  
![](https://i.imgur.com/xshOHu2.png)

### Tech used
- Java SpringBoot
- MySQL
- OpenAPI 3.0
- Docker
