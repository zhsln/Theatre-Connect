package kz.aitu.tc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId; // Unique identifier for each booking.

    // Define a many-to-one relationship between bookings and users.
    // CascadeType.REMOVE means if a User is deleted, all their bookings are also deleted.
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    @NotBlank(message = "Please, enter user's ID.")
    private User user; // The user who made the booking.

    // Define a many-to-one relationship between bookings and performances.
    // If a Performance is deleted, all related bookings are also deleted.
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "performance_id", nullable = false)
    @NotBlank(message = "Please, enter ID of the performance.")
    private Performance performance; // The performance that was booked.

    @Column(name = "seat_number")
    @NotBlank(message = "Seat number is mandatory.")
    private String seat_number; // Example: "Parterre: Row 13, Seat 10".
    /*
    Seat number is String, because seat information can be complex.
    And why it is called a number?
    Because seat can have information about rows and your seat number in a row.
    Example: "Parterre: Row 13, Seat 10".
    */
}
