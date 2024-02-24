package kz.aitu.tc.repositories;

import kz.aitu.tc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Integer> {
    // Find users by their surname. Returns a list of users with the given surname.
    List<User> findBySurname(String surname);

    // Find users by their name. Returns a list of users with the given name.
    List<User> findByName(String name);

    // Find users who are editors. Returns a list of users marked as editors.
    List<User> findByEditorTrue();

    // Find users who are managers. Returns a list of users marked as managers.
    List<User> findByManagerTrue();
}
