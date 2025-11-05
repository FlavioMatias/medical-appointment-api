package medical.api.appointment.controller;

import medical.api.appointment.common.auth.UserAuthenticated;
import medical.api.appointment.dto.appointment.AppointmentFilterDTO;
import medical.api.appointment.dto.appointment.AppointmentRequestDTO;
import medical.api.appointment.dto.appointment.AppointmentResponseDTO;
import medical.api.appointment.service.AppointmentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<AppointmentResponseDTO> findAppointments(
            @ModelAttribute AppointmentFilterDTO filter,
            @AuthenticationPrincipal UserAuthenticated user
    ) {
        Long schedulerId = user.getId();
        return appointmentService.findAppointments(filter, schedulerId);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO findById(
            @PathVariable Long id
    ) {
        return appointmentService.findById(id);
    }

    @PostMapping
    public AppointmentResponseDTO create(
            @RequestBody AppointmentRequestDTO dto,
            @AuthenticationPrincipal UserAuthenticated user
    ) {
        return appointmentService.create(dto, user.getId());
    }

    @PutMapping("/{id}")
    public AppointmentResponseDTO update(
            @PathVariable Long id,
            @RequestBody AppointmentRequestDTO dto
    ) {
        return appointmentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}
