package ru.practicum.ewmservice.user.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewmservice.user.model.dto.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull Page<User> findAll(@NonNull Pageable page);

    Page<User> getUsersByIdIsInOrderByIdAsc(List<Long> id, Pageable pageable);

    User findByName(String name);
}
