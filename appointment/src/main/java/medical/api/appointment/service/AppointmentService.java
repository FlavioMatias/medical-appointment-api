package medical.api.appointment.service;

import medical.api.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepo;

    public AppointmentService(AppointmentRepository appointmentRepo){
        this.appointmentRepo = appointmentRepo;
    }

}
