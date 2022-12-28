package ru.practicum.ewm.compilations.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.events.model.Event;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "compilations")
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /*
    заголовок подборки
    */
    @Column(name = "title")
    private String title;


    /*
    закреплена ли подборка на главной странице сайта
    */
    @Column(name = "pinned")
    private Boolean pinned;


    /* fetchtype.lazy - загрузка данными только по запросу
    joinColumns для прямой связи
    inverseJoinColumns для обратной связи
    таблица compilations и таблица events будут связаны через*/
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events_compilations",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;
}
