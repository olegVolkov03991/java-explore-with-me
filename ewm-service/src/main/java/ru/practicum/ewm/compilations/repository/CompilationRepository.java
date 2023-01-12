package ru.practicum.ewm.compilations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.compilations.model.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into events_compilations (event_id, compilation_id) " +
                    "values (:eventId, :compId)")
    int addEventToCompilation(@Param("compId") Long compId,
                              @Param("eventId") Long eventId);

    @Modifying
    @Query(nativeQuery = true,
            value = "delete from events_compilations " +
                    "where event_id = :eventId " +
                    "and compilation_id = :compId")
    int deleteEventToCompilation(@Param("compId") Long compId,
                                 @Param("eventId") Long eventId);


    Page<Compilation> findCompilationByPinnedOrderByIdAsc(Boolean pinned,
                                                          Pageable pageable);


}
