package ru.practicum.ewm.events.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.events.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {


    Page<Event> getEventsByInitiatorIdOrderByIdAsc(Long userId, Pageable pageable);



    @Query("select e from Event e " +
            "where (:users is null or e.initiator.id in :users)" +
            "and (:categories is null or e.category.id in :categories)" +
            "and (:states is null or e.state in :states)" +
            "and (:rangeStart is null or e.eventDate > cast(:rangeStart as timestamp))" + //:rangeStart is null or e.eventDate > cast(:rangeStart as timestamp) преобразование типа String(rangeStart) в LocalDate(timestamp)
            "and (:rangeEnd is null or e.eventDate < cast(:rangeEnd as timestamp))")
    Page<Event> searchEvents(@Param("users") List<Long> users,
                             @Param("states") List<State> states,
                             @Param("categories") List<Long> categories,
                             @Param("rangeStart") String rangeStart,
                             @Param("rangeEnd") String rangeEnd,
                             Pageable page);


    @Query("select e from Event e " +
            "where (e.state = 'PUBLISHED') " +
            "and ((:text is null or upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "or (:text is null or upper(e.description) like upper(concat('%', :text, '%')))) " +
            "and (:paid is null or e.paid = :paid) " +
            "and (:rangeStart is null or e.eventDate > cast(:rangeStart as timestamp))" +
            "and (:rangeEnd is null or e.eventDate < cast(:rangeEnd as timestamp))" +
            "and (:categories is null or e.category.id in :categories)")
    Page<Event> getEvents(@Param("text") String text,
                          @Param("categories") List<Long> categories,
                          @Param("paid") Boolean paid,
                          @Param("rangeStart") String rangeStart,
                          @Param("rangeEnd") String rangeEnd,
                          Pageable page);
}


