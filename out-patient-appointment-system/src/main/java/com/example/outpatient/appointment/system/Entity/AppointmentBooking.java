package com.example.outpatient.appointment.system.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "booking")
public class AppointmentBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Long doctorId;

    private Long patientId;

    private LocalDateTime appointmentDateTime;

}
