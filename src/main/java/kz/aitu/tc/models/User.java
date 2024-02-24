package kz.aitu.tc.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for each user.
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean editor; // Editor is administrator that can upload and change performances in Database and Website.
    private boolean manager; // Manager is administrator that can upload and change everything.
}