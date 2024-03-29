package ru.practicum.ewm.requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm.requests.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("select count(r) from Request r " +
            "where r.event.id = ?1  and  r.status = 'CONFIRMED' ")
    Long getCountConfirmedRequestByEventId(Long eventId);

    @Query(" select r from Request r " +
            "where r.event.id = ?1 ")
    List<Request> findRequestByEventId(Long eventId);

    @Query("select r from Request  r " +
            "where r.requester.id = ?1 ")
    List<Request> getRequestByRequester(Long userId);

}
