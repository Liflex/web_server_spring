package ru.dmitartur.dao.abstraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitartur.model.Role;
import ru.dmitartur.model.User;

import java.util.List;

public interface RoleDAO {
    Role get(Long id);
    void add(Role t);
}
