package kz.aitu.tc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Title of the performance is mandatory.")
    private String title;

    @NotBlank(message = "Date of the performance is mandatory.")
    private LocalDate date; // yyyy-MM-dd

    @NotBlank(message = "Time of the performance is mandatory.")
    private OffsetTime time; // HH:mm:ss + HH:mm (time with timezone)

    @NotBlank(message = "Duration of the performance is mandatory.")
    private int duration; // In minutes.

    @NotBlank(message = "Venue of the performance is mandatory.")
    private String venue; /*
                            Venue means place of the event.
                            For example, "The State Opera and Ballet Theatre 'Astana Opera'".
                          */
}
