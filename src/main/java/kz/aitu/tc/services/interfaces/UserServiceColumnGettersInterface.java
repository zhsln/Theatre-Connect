package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.User;

import java.util.List;

// Interface to get information from the columns of the same name.
public interface UserServiceColumnGettersInterface {
    // Get all users
    List<User> getAll();

    // Retrieve a user by their ID
    User getById(int id);

    // Get all users with a specific surname
    List<User> getBySurname(String surname);

    // Get all users with a specific name
    List<User> getByName(String name);

    // Get all users who are editors
    List<User> getEditors();

    // Get all users who are managers
    List<User> getManagers();
}
