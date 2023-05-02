package com.jarikkomarik.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Integer customerId;
    @NonNull
    private LocalDateTime sentAt;
    @NonNull
    private String message;
    @NonNull
    private String sender;
    @NonNull
    private String customerEmail;

}
