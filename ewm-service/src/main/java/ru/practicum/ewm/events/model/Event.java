package ru.practicum.ewm.events.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.events.CustomDateDeserializer;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
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


    private String title;


    private String annotation;


    private String description;

    @Column(name = "participant_limit")
    private Long participantLimit;


    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;


    @Column(name = "event_date", columnDefinition = "TIMESTAMP")
    @JsonDeserialize(using = CustomDateDeserializer.class)

    private Date eventDate;


    @Column(name = "created_on")
    private Date createdOn;


    @Column(name = "published_on")
    private Date publishedOn;

    @ManyToOne
    @JoinColumn(name = "initiator_id", referencedColumnName = "id")
    private User initiator;


    @Column(name = "request_moderation")
    @Builder.Default
    private Boolean requestModeration = true;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private State state = State.PENDING;


    @ManyToOne()
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;


}
