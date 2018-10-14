package ru.dmitartur.dao.abstraction;

import ru.dmitartur.model.Role;

public interface RoleDAO {
    Role get(Long id);
    void add(Role t);
}
