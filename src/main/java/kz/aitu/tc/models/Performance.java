package kz.aitu.tc.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetTime;

@Data
@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for each performance.
    private String title;
    private LocalDate date; // yyyy-MM-dd
    private OffsetTime time; // HH:mm:ss + HH:mm (time with timezone)
    private int duration; // In minutes.
    private String venue; /*
                            Venue means place of the event.
                            For example, "The State Opera and Ballet Theatre 'Astana Opera'".
                          */
}
