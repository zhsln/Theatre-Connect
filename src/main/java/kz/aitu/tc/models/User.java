package kz.aitu.tc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for each user.

    @NotBlank(message = "Login is mandatory.") // for validation.
    @Size(min = 5, message = "Login must be at least 5 characters long.") // size matters!
    @Column(name = "login", unique = true)
    private String login;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Name is mandatory.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname is mandatory.")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "Please specify whether the user is an editor.")
    @Column(name = "editor")
    private Boolean editor; // Editor is administrator that can upload and change performances in Database and Website.

    @NotNull(message = "Please specify whether the user is a manager.")
    @Column(name = "manager")
    private Boolean manager; // Manager is administrator that can upload and change everything.
}