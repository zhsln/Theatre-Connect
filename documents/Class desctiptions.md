![Theatre-Connect](/documents/images/Theatre‒Connect.png "Web service system for theatres.")

# Theatre Connect: Class Descriptions
## Models
### User
Represents a user of the Theatre Connect system. Includes attributes such as user ID, name, and contact information. Users can book performances.

### Booking
Represents a booking made by a user. It contains details about the performance booked, the user who made the booking, and the booking date.

### Performance
Represents a theatrical performance. It includes information such as the performance ID, title, description, and the date and time of the performance.

## Controllers
### UserController
Manages user-related operations. It handles requests to add a new user, retrieve user information, and update user details.

### BookingController
Handles booking operations. This includes creating new bookings, fetching booking details, and updating or cancelling a booking.

### PerformanceController
Responsible for performance-related actions. It allows adding new performances, retrieving details about performances, and updating or deleting performance information.

## Services and Interfaces
### BookingService (Interfaces: BookingServiceInterface and BookingServiceColumnGettersInterface)
Provides business logic for booking operations. It interacts with the BookingRepository to perform CRUD operations on bookings.

### UserService (Interfaces: UserServiceInterface and UserServiceColumnGettersInterface)
Offers functionality related to users. It uses the UserRepository to manage user data, such as creating and updating user information.

### PerformanceService (Interfaces: PerformanceServiceInterface and PerformanceServiceColumnGettersInterface)
Manages services related to performances. It works with the PerformanceRepository to handle performance data.

## Repositories and Interfaces
### UserRepositoryInterface
Defines methods for user-related database operations. This includes adding a new user, fetching user details, and updating user information.

### BookingRepositoryInterface
Specifies the repository methods for handling booking data. This involves creating, updating, and retrieving bookings.

### PerformanceRepositoryInterface
Outlines the methods for managing performance data in the database. It includes adding, updating, and fetching performance details.

## GlobalExceptionHandler and Exceptions
### GlobalExceptionHandler
designed for global exception handling, including method argument validation, missing resources, unique data conflicts, and other exceptions, returning appropriate HTTP responses.

### AlreadyExistsException
Indicates a conflict, typically used when an attempt to create a resource that already exists is made.

### ResourceNotFoundException
Aimed at handling cases where requested resources are not found, signaling a NOT_FOUND error condition.
