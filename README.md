# Glofox Backend

## Description and architecture

Glofox-backend is a backend api for studio classes management. It allows a studio owner to create a class for their studio and a studio member to book classes.

Since the use cases were very simple I opted for a plain old monolith. With the current, simplest architecture, all the endpoints are treated in the same controller, which in turn calls only one service that uses only one repository. Although this approach may result simple, should the need arise for a more distributed architecture, an event driven system, or even a plain microservices architecture, could be considered by segregating the domains.

## Considerations

- Only a studio owner can create classes for that studio, so the ownership is checked at a service level. The same goes for member and bookings, the service checks if the member belongs to the studio in which the class is in before allowing them to book a class.
- In order to do the above I assumed that the name for the member of a studio is already present in some manner in the HTTP request to book a class, so they can be identified. To simulate this I’ve decided to include the member name as a path variable. The same applies to the owners.
- All the business logic is kept in the service, therefore all the controller does is to map the dto to a model and call the service. At the same time, the only thing that a repository does is return contents from the list that acts like persistence layer. There’s no business logic whatsoever in the controller or the repository.

## Future

Whereas

## Testing

wip

## Running the application

wip
