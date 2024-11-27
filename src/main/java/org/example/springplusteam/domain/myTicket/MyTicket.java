package org.example.springplusteam.domain.myTicket;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springplusteam.common.BaseEntity;
import org.example.springplusteam.domain.ticket.Ticket;
import org.example.springplusteam.domain.user.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_tickets")
public class MyTicket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    public MyTicket(User user, Ticket ticket) {
        this.user = user;
        this.ticket = ticket;
    }
}
