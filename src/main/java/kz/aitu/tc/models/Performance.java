package kz.aitu.tc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Column(name = "title")
    @NotBlank(message = "Title of the performance is mandatory.")
    private String title;

    @Column(name = "date")
    @NotNull(message = "Date of the performance is mandatory.")
    private LocalDate date; // yyyy-MM-dd

    @Column(name = "time")
    @NotNull(message = "Time of the performance is mandatory.")
    private OffsetTime time; // HH:mm:ss + HH:mm (time with timezone)

    @Column(name = "duration")
    @Min(value = 1, message = "Duration of the performance must be greater than 0.")
    private int duration; // In minutes.

    @Column(name = "venue")
    @NotBlank(message = "Venue of the performance is mandatory.")
    private String venue; /*
                            Venue means place of the event.
                            For example, "The State Opera and Ballet Theatre 'Astana Opera'".
                          */
}