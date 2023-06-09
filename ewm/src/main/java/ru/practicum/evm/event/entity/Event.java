package ru.practicum.evm.event.entity;

import ru.practicum.evm.category.entity.Category;
import ru.practicum.evm.event.enums.EventState;
import ru.practicum.evm.user.entity.User;
import org.hibernate.Hibernate;
import lombok.*;

import java.time.LocalDateTime;
import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;
import static ru.practicum.evm.event.enums.EventState.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;
import static java.util.Objects.*;


@Entity
@Getter
@Setter
@Table(name = "events", schema = "public")
@NoArgsConstructor(access = PROTECTED)
public class Event {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "annotation", length = 2000)
    private String annotation;

    @Column(name = "confirmed_requests")
    private int confirmedRequests;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "description", length = 7000)
    private String description;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @OneToOne
    @JoinColumn(name = "initiator_id", referencedColumnName = "id")
    private User initiator;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "participant_limit")
    private int participantLimit;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @Enumerated(STRING)
    @Column(name = "state")
    private EventState state;

    @Column(name = "title")
    private String title;

    @Column(name = "views")
    private Long views;

    public Event(Long id,
                 String annotation,
                 Category category,
                 int confirmedRequests,
                 LocalDateTime createdOn,
                 String description,
                 LocalDateTime eventDate,
                 User initiator,
                 Location location,
                 Boolean paid,
                 int participantLimit,
                 LocalDateTime publishedOn,
                 Boolean requestModeration,
                 EventState eventState,
                 String title,
                 Long views) {
        this.requestModeration = requireNonNullElse(requestModeration, true);
        this.createdOn = requireNonNullElseGet(createdOn, LocalDateTime::now);
        this.state = requireNonNullElse(eventState, PENDING);
        this.confirmedRequests = confirmedRequests;
        this.participantLimit = participantLimit;
        this.description = description;
        this.annotation = annotation;
        this.publishedOn = publishedOn;
        this.eventDate = eventDate;
        this.initiator = initiator;
        this.category = category;
        this.location = location;
        this.title = title;
        this.views = views;
        this.paid = paid;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var event = (Event) o;
        return getId() != null && Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}