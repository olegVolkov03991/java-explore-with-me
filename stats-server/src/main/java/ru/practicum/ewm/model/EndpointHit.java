package ru.practicum.ewm.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statistics")
public class EndpointHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "uri", nullable = false)
    @Size(min = 3, max = 255)
    private String uri;

    @Type(type = "jsonb")
    @Column(name = "attributes", nullable = false)
    private Attributes attributes;

}
