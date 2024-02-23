package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    User create(User user);
    List<User> getBySurname(String surname);
    List<User> getByName(String name);
    List<User> getEditors();
    List<User> getManagers();
    boolean deleteById(int id);
    User update(int id, User user);
}