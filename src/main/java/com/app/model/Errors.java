package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Errors is a class which entity for Errors table in database.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Errors extends RuntimeException {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String message;

    @ElementCollection
    @CollectionTable(name = "emessage", joinColumns = @JoinColumn(name = "messageId"))
    @Column(name = "emessage")
    @Enumerated(EnumType.STRING)
    private Set<EMessage> emeggages = new HashSet<>();

    public Errors(String message, LocalDate date) {
        this.date = date;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Errors errors = (Errors) o;
        return Objects.equals(id, errors.id) &&
                Objects.equals(date, errors.date) &&
                Objects.equals(message, errors.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, message);
    }

    @Override
    public String toString() {
        return "Errors{" +
                "id=" + id +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
