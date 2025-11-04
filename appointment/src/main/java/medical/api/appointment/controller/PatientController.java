package medical.api.appointment.controller;

import medical.api.appointment.common.auth.UserAuthenticated;
import medical.api.appointment.dto.PatientFilterDTO;
import medical.api.appointment.dto.PatientRequestDTO;
import medical.api.appointment.dto.PatientResponseDTO;
import medical.api.appointment.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientResponseDTO> findPatients(
            @ModelAttribute PatientFilterDTO filter,
            @AuthenticationPrincipal UserAuthenticated user
    ) {
        Long schedulerId = user.getId();
        return patientService.findPatients(filter, schedulerId);
    }

    @GetMapping("/{id}")
    public PatientResponseDTO findById(
            @PathVariable Long id
    ) {
        return patientService.findById(id);
    }

    @PostMapping
    public PatientResponseDTO create(
            @RequestBody PatientRequestDTO dto,
            @AuthenticationPrincipal UserAuthenticated user
    ) {
        return patientService.create(dto, user.getId());
    }

    @PutMapping("/{id}")
    public PatientResponseDTO update(
            @PathVariable Long id,
            @RequestBody PatientRequestDTO dto
    ) {
        return patientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.deleteById(id);
    }
}
