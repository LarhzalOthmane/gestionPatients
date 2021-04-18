package com.patients.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.patients.entities.Patient;
import com.patients.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientsController {
    @Autowired

    private PatientRepository patientRepository;

    @GetMapping("/")
    public String listPatients(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "motCle", defaultValue = "") String motCle) {
        Page<Patient> patients = patientRepository.findByNomContains(motCle, PageRequest.of(page, size));
        model.addAttribute("listPatients", patients);
        model.addAttribute("motCle", motCle);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        return "patientsView";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(Long id) {
        patientRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/addPatient")
    public String addPatient(@RequestParam(name = "idField", defaultValue = "") String idField, String nom, String dateField, String scoreField, String maladeField){
        Long id = idField.isEmpty() ? null : Long.parseLong(idField);
        Date dateNaissance;
        try {
            dateNaissance = new SimpleDateFormat("yyyy-mm-dd").parse(dateField);
        } catch (ParseException e) {
            dateNaissance = new Date();
            e.printStackTrace();
        }
        int score = Integer.parseInt(scoreField);
        boolean malade = Boolean.parseBoolean(maladeField);
        Patient patient = new Patient(id, nom, dateNaissance, score, malade);
        patientRepository.save(patient);
        return "redirect:/";
    }

    // @GetMapping("/editPatient")
    // public String editPatient(String idField, String nom, String dateField, String scoreField, String maladeField){
    //     Long id = Long.parseLong(idField);
    //     Date dateNaissance;
    //     try {
    //         dateNaissance = new SimpleDateFormat("yyyy-mm-dd").parse(dateField);
    //     } catch (ParseException e) {
    //         dateNaissance = new Date();
    //         e.printStackTrace();
    //     }
    //     int score = Integer.parseInt(scoreField);
    //     boolean malade = Boolean.parseBoolean(maladeField);
    //     patientRepository.
    //     return "redirect:/";
    // }
}
