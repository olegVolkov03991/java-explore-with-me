drop table if exists users cascade;
drop table if exists categories cascade;
drop table if exists requests cascade;
drop table if exists events cascade;
drop table if exists compilations cascade;
drop table if exists events_compilations cascade;
drop table if exists locations;
drop table if exists comment;


CREATE TABLE IF NOT EXISTS compilations
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pinned BOOLEAN,
    title  VARCHAR(255),
    CONSTRAINT pk_compilations PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS locations
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    lat         FLOAT                                   NOT NULL,
    lon         FLOAT                                   NOT NULL,
    name        VARCHAR(255),
    description TEXT,
    radius      FLOAT,
    CONSTRAINT pk_locations PRIMARY KEY (id)
);

create table if not exists users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255)                            NOT NULL,
    email    VARCHAR(512)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email),
    ban_user BOOLEAN
);

CREATE TABLE IF NOT EXISTS categories
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id),
    CONSTRAINT uq_category_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS events
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title              VARCHAR(120)                            NOT NULL,
    description        TEXT                                    NOT NULL,
    annotation         VARCHAR(2000),
    category_id        BIGINT                                  NOT NULL,
    event_date         TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    created_on         TIMESTAMP WITHOUT TIME ZONE,
    published_on       TIMESTAMP WITHOUT TIME ZONE,
    location_id        BIGINT                                  NOT NULL,
    paid               BOOLEAN,
    participant_limit  BIGINT,
    initiator_id       BIGINT,
    state              VARCHAR(50),
    request_moderation BOOLEAN,
    CONSTRAINT pk_events PRIMARY KEY (id),
    CONSTRAINT fk_events_users
        FOREIGN KEY (initiator_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_events_categories
        FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
    CONSTRAINT fk_events_locations
        FOREIGN KEY (location_id) REFERENCES locations (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comment
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text         VARCHAR(500)                            NOT NULL,
    created_on   TIMESTAMP WITHOUT TIME ZONE,
    initiator_id BIGINT,
    event_id     BIGINT,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS requests
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    requester_id BIGINT,
    event_id     BIGINT,
    created      TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(50),
    CONSTRAINT pk_requests PRIMARY KEY (id),
    CONSTRAINT fk_request_users
        FOREIGN KEY (requester_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_request_events
        FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS events_compilations
(
    event_id       BIGINT,
    compilation_id BIGINT,
    CONSTRAINT pk_events_compilations
        PRIMARY KEY (event_id, compilation_id),
    CONSTRAINT fk_events_compilations_event
        FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    CONSTRAINT fk_events_compilations_compilation
        FOREIGN KEY (compilation_id) REFERENCES compilations (id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION distance(lat1 float, lon1 float, lat2 float, lon2 float)
    RETURNS float
AS
'
    declare
        dist      float = 0;
        rad_lat1  float;
        rad_lat2  float;
        theta     float;
        rad_theta float;
    BEGIN
        IF lat1 = lat2 AND lon1 = lon2
        THEN
            RETURN dist;
        ELSE
            -- переводим градусы широты в радианы
            rad_lat1 = pi() * lat1 / 180;
            -- переводим градусы долготы в радианы
            rad_lat2 = pi() * lat2 / 180;
            -- находим разность долгот
            theta = lon1 - lon2;
            -- переводим градусы в радианы
            rad_theta = pi() * theta / 180;
            -- находим длину ортодромии
            dist = sin(rad_lat1) * sin(rad_lat2) + cos(rad_lat1) * cos(rad_lat2) * cos(rad_theta);
            IF dist > 1
            THEN
                dist = 1;
            END IF;
            dist = acos(dist);
            -- переводим радианы в градусы
            dist = dist * 180 / pi();
            -- переводим градусы в километры
            dist = dist * 60 * 1.8524;
            RETURN dist;
        END IF;
    END;
'
    LANGUAGE PLPGSQL;



