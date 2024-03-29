# Glofox Backend

## Introduction

Glofox is a saas platform for boutiques, studios, and gyms which allows business owners to manage their courses, classes, members, memberships etc,. There are a couple of stories outlined below which you have to implement.

### Story - Create Classes

As a studio owner i want to create classes for my studio so that my members can attend classes
Acceptance Criteria

- Implement an API to create classes(`/classes`). Assume this api doesn't need to have any authentication to start with.

- Few bare minimum details we need to create classes are - `class name`, `start_date`, `end_date`, `capacity`. For now, assume that there will be only one class per given day. Ex: If a class by name pilates starts on 1st Dec and ends on 20th Dec, with capacity 10, that means Pilates has 20 classes and for each class the maximum capacity of attendance is 10.

- No need to save the details in any database. Maintain an in memory array or a file to save the info. (If you want to use the database, that's fine as well).

- Use Restful standards and create the api endpoint with proper success and error responses.

### Story - Book for a class

As a member of a studio, I can book for a class, so that I can attend a class.
Acceptance Criteria

- Implement an API endpoint (`/bookings`). Assume this api doesn't need to have any authentication to start with.

- Few bare minimum details we need for reserving a class are - name(name of the member who is booking the class), date(date for which the member want to book a class)

- No need to save the details in DB. If you can maintain the state in an in memory array or a file is good to start with. But no constraints if you want to use a database to save the state.

- Use REST standards and think through basic api responses for success and failure.

- No need to consider the scenario of overbooking for a given date. Ex: 14th Dec having a
  capacity of 20 , but the number of bookings can be greater than 20.

## What we look for

Just because you take an easy approach won’t set you back in our eyes. Easy might just mean you’re busy - we understand. Either way, we look at the same areas:

- Approach taken

- Architecture

- Code structure and clarity

- Performance

- Testing

- Include a readme file if there is setup required for your code to run, or if there is something
  you would like to comment on.

You can pass on the finished code either as a zip file or link us to any of hosted VCS like github/bitbucket/gitlab etc,.

## Description and architecture

Glofox-backend is a backend api for studio classes management. It allows a studio owner to create a class for their studio and a studio member to book classes.

Since the use cases were very simple I opted for a plain old monolith. With the current, simplest architecture, all the endpoints are treated in the same controller, which in turn calls only one service that uses only one repository. Although this approach may result simple, should the need arise for a more distributed architecture, an event driven system, or even a plain microservices architecture, could be considered by segregating the domains.

## Considerations

- Only a studio owner can create classes for that studio, so the ownership is checked at a service level. The same goes for member and bookings, the service checks if the member belongs to the studio in which the class is in before allowing them to book a class.
- In order to do the above I assumed that the name for the member of a studio is already present in some manner in the HTTP request to book a class, so they can be identified. To simulate this I’ve decided to include the member name as a path variable. The same applies to the owners.
- All the business logic is kept in the service, therefore all the controller does is to map the dto to a model and call the service. At the same time, the only thing that a repository does is return contents from the list that acts as persistence layer. There’s no business logic whatsoever in the controller or the repository.
- Any time something goes wrong in the API an exception is thrown and captured. Depending on the place and the nature of the exception, an error code and an optional message is sent as a response.
- In order to maintain SOLID principles, an interface has been created for the service and the repository for dependency injection purposes, even if it right now makes little sense given the simplicity of the use cases.
- The owners and members are classes for an easier extension. Both should have id’s because right now the search for students in a studio returns the first match for the name, which is a poor identifier and will ignore duplicates. However, it’s not been implemented because of time constraints.
- Storing the bookings in the studio made more sense since the bookings are a feature of a studio, not of a member, and therefore their place is inside a studio. However, as of now a member does not have a way to see their bookings and it’s something that must be addressed.
- The models that map the dtos have the validation logic inside the constructor, thus in case something goes wrong they return an exception.

## Future

Whereas a monolith is enough for our use cases, the implementation is done in a way that can be expanded. For example, it can be ported into a Microservices architecture by simply considering the domains that are needed. Thus, an API gateway could be set as the receiver of all the requests with several microservices behind it. Said microservices could communicate via direct call or, if decoupling is in order, via some messagingsystem/broker such as Rabbit or Kafka.

## HTTP response codes

Several exceptions have been created to represent the most common errors while creating classes or bookings. Those exceptions are then mapped to specific error codes that are returned via http response. Those are:

- FORBIDDEN: if the user that tries to create a class is not the owner of the studio and if the user trying to book a class is not a member of the studio.
- CONFLICT: whenever a resource (class, booking) is already present. Used to avoid duplications because I didn't have time to implement idempotency.
- BAD REQUEST: any other type of exception. In this case, a message is sent that specifies the problem (usually a dto to model mapping problem).

There are several error cases that are not implemented because of time constraints, specifically when a dto has a bad formatted date.

Whenever an operation succeeds, a CREATED code is returned.

## Testing

There's a StudioControllerTest class that tests some (again, time constraints) of the use cases for the endpoints. It would be necessary to create another class for the service and the repository. To launch the tests, either import the project into an IDE and run them or open a terminal, go to the `/backend` folder and run `./gradlew clean test` on MacOS or `gradle clean test` on Windows.

## Running the application

Gradle and Java are needed to run the application. Make sure to have both installed.

There's a bootstrap CommandLineRunner used to populate the studio list with data for testing purposes. There's also a testing endpoint, `/studios`, that returns all the information of the studios.

In order to run the project, go to the `/backend`folder and type `./gradlew clean build` (or `gradle clean build` on Windows). Then proceed to `/backend/build/libs` folder and run `java -jar backend-0.0.1-SNAPSHOT.jar`

Now you can open a tool like Postman to send requests and check responses. Here are some of the tests requests that you may use:

```
POST http://localhost:8080/andy/classes

{
	"name": "class 2",
	"start": "22-09-2022",
	"end": "23-09-2022",
	"capacity": "123"
}`

```

```
POST http://localhost:8080/jane/bookings

{
    "className":"spinning",
	"date": "26-09-2022"
}

```

And to check the changes in the studios:

```
GET http://localhost:8080/studios
```
