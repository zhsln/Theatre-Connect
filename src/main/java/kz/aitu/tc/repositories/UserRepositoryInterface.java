package kz.aitu.tc.repositories;

import kz.aitu.tc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Integer> {
    List<User> findBySurname(String surname);
    List<User> findByName(String name);
    List<User> findByEditorTrue();
    List<User> findByManagerTrue();
}
