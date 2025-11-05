package medical.api.appointment.controller;

import medical.api.appointment.common.auth.UserAuthenticated;
import medical.api.appointment.dto.doctor.DoctorFilterDTO;
import medical.api.appointment.dto.doctor.DoctorRequestDTO;
import medical.api.appointment.dto.doctor.DoctorResponseDTO;
import medical.api.appointment.service.DoctorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorResponseDTO> findDoctors(
            @ModelAttribute DoctorFilterDTO filter,
            @AuthenticationPrincipal UserAuthenticated user
    ) {
        Long schedulerId = user.getId();
        return doctorService.findDoctors(filter, schedulerId);
    }

    @GetMapping("/{id}")
    public DoctorResponseDTO findById(
            @PathVariable Long id
    ) {
        return doctorService.findById(id);
    }

    @PostMapping
    public DoctorResponseDTO create(
            @RequestBody DoctorRequestDTO dto,
            @AuthenticationPrincipal UserAuthenticated user
    ) {
        return doctorService.create(dto, user.getId());
    }

    @PutMapping("/{id}")
    public DoctorResponseDTO update(
            @PathVariable Long id,
            @RequestBody DoctorRequestDTO dto
    ) {
        return doctorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}
