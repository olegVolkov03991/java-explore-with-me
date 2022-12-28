package ru.practicum.ewm.events.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.events.CustomDateDeserializer;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /*
     заголовок
     * */
    private String title; // заголовок

    /*
     краткое описание
     * */
    private String annotation; // краткое описание

    /*
     полное описание
     * */
    private String description; // описание

    /*
     Ограничение на количество участников. Значение 0 - означает отсутствие ограничения
     * */
    @Column(name = "participant_limit")
    private Long participantLimit;

    /*
     Нужно ли оплачивать участие
     * */
    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    /*
     @JsonSerialize применяется для указания класса,
     с помощью которого будет производиться десериализация сущности.
     */
    @Column(name = "event_date", columnDefinition = "TIMESTAMP")
    @JsonDeserialize(using = CustomDateDeserializer.class)

    /*
    валидация, при которой значение переменной должно быть в только в будущем времени
    Дата и время на которые намечено событие
    */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Future
    private LocalDateTime eventDate;

    /*
    Дата и время создания события
    */
    @Column(name = "created_on")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    /*
    Дата и время публикации события
    */
    @Column(name = "published_on")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

    @ManyToOne
    @JoinColumn(name = "initiator_id", referencedColumnName = "id")
    private User initiator;


    /**
     * Нужна ли пре-модерация заявок на участие
     */
    @Column(name = "request_moderation")
    @Builder.Default
    private Boolean requestModeration = true;

    /*
     * Список состояний жизненного цикла события
     * */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.PENDING;


    /*
     * локация события
     * */
    @ManyToOne()
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

}
