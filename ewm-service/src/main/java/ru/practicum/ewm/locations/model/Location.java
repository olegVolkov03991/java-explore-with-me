package ru.practicum.ewm.locations.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lat", nullable = false)

    private Double lat;

    @Column(name = "lon", nullable = false)
    private Double lon;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "radius")
    @Builder.Default
    private Double radius = 0.1;
}
