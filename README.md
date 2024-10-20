# package deliveries

Idea:
The idea was to implement a prototype of a system to track packages and calcutlate their paths across the graph of city
Ideally the implementation would be decoupled, concerns isolated, interface driven to make new features like redis be easy to implement
The functionality is made with spring JPA, MVC, Jackson includes Spring security for authorization
There is a JUnit test to see if the search algorithm works in isolation
And a spring integration test to see if all components like listener and such work when the spring container is ran

---
Notable features:
- REST API that queries the DB for packages belonging to the user
- Listener callback class that intercepts all persist and update calls to the jpa
  - notably it intercepts all calls including the console and the spring container
  - its used for calculating the path across the graph instantly upon update or persist of new entity
- Searching implemented using a spin on Dijkstras algorithm
  - dijkstras algorithim that is made to stop upon reaching from point a to point b instead of complete search
  - supports weighted graphs

---
Problems and Solutions:
- during calculation entity table is put on a pessimistic lock to prevent exceptions
  - due to asynchronous nature of spring container there could be exceptions if JPA returns an empty path hence the lock
- leave the apect oriented programming ideas for another branch
  - first solution included the listner being autowired the components via load time weaving
  - instead of abandoning that idea it still exists in another branch
  - instead i opted to make the listener a component to autowire beans instead of autowireing them with ltw
- first implementation included calculation of all paths dynamically 
  - but recalculating the path multiple times would be inefficient
  - instead the calculation is upon request
