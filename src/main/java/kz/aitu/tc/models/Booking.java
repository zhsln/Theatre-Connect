package kz.aitu.tc.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @Column(name = "seat_number")
    private String seat_number; /*
                                Seat number is String,
                                because in theatre your seat can be, for example, in the balcony,
                                and it should be noted by words in your ticket. And why it is called a number?
                                Because seat can have information about rows and your seat number in a row.
                                Example: "Parterre: Row 13, Seat 10" .
                                */
}
