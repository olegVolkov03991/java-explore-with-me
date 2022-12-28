package ru.practicum.ewm.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface StatRepository extends JpaRepository<EndpointHit, Long> {
}
