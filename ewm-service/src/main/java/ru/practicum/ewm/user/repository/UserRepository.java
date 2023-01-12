package ru.practicum.ewm.user.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.user.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull Page<User> findAll(@NonNull Pageable page);

    Page<User> getUsersByIdIsIn(List<Long> id, Pageable pageable);

    Boolean existsByName(String name);
}
