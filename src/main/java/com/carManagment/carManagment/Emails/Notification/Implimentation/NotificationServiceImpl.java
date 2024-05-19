package com.carManagment.carManagment.Emails.Notification.Implimentation;

import com.carManagment.carManagment.Dto.Casko.CASKODTO;
import com.carManagment.carManagment.Dto.CivilInsurance.CivilInsuranceDTO;
import com.carManagment.carManagment.Dto.Employee.EmployeeDTO;
import com.carManagment.carManagment.Dto.TechnicalInspectation.TechnicalInspectionDTO;
import com.carManagment.carManagment.Dto.Vignette.VignetteDTO;
import com.carManagment.carManagment.Emails.EmailService;
import com.carManagment.carManagment.Emails.Notification.NotificationService;
import com.carManagment.carManagment.Entitys.CASKO;
import com.carManagment.carManagment.Entitys.CivilInsurance;
import com.carManagment.carManagment.Entitys.TechnicalInspection;
import com.carManagment.carManagment.Entitys.Vignette;
import com.carManagment.carManagment.Mappers.*;
import com.carManagment.carManagment.Service.CASKOService;
import com.carManagment.carManagment.Service.EmployeeService;
import com.carManagment.carManagment.Service.TechnicalInspectionService;
import com.carManagment.carManagment.Service.implimentation.CivilInsuranceServiceImpl;
import com.carManagment.carManagment.Service.implimentation.VignetteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final EmailService emailService;
    private final VignetteServiceImpl vignetteService;
    private final VignetteMapper vignetteMapper;
    private final CivilInsuranceServiceImpl civilInsuranceService;
    private final CivilInsuranceMapper civilInsuranceMapper;
    private final CASKOService caskoService;
    private final CASKOMapper caskoMapper;
    private final EmployeeService employeeService;
    private final TechnicalInspectionService technicalInspectionService;
    private final TechnicalInspectionMapper technicalInspectionMapper;
    private final EmployeeMapper employeeMapper;


    @Autowired
    public NotificationServiceImpl(VignetteServiceImpl vignetteService, CivilInsuranceServiceImpl civilInsuranceService, EmailService emailService, VignetteMapper vignetteMapper, CivilInsuranceMapper civilInsuranceMapper, CASKOService caskoService, CASKOMapper caskoMapper, EmployeeService employeeService, TechnicalInspectionService technicalInspectionService, TechnicalInspectionMapper technicalInspectionMapper, EmployeeMapper employeeMapper) {
        this.vignetteService = vignetteService;
        this.civilInsuranceService = civilInsuranceService;
        this.emailService = emailService;
        this.vignetteMapper = vignetteMapper;
        this.civilInsuranceMapper = civilInsuranceMapper;
        this.caskoService = caskoService;
        this.caskoMapper = caskoMapper;
        this.employeeService = employeeService;
        this.technicalInspectionService = technicalInspectionService;
        this.technicalInspectionMapper = technicalInspectionMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public void notifyAboutExpiringVignette() {
        List<VignetteDTO> allVignettes = vignetteService.getAllVignettes();

        // Перебираем винетки для определения тех, которые истекают через 3 дня
        for (VignetteDTO vignetteDTO : allVignettes) {
            if (vignetteDTO.getExpirationDate().minusDays(3).isEqual(LocalDate.now())) {
                Vignette vignette = vignetteMapper.toEntity(vignetteDTO);
                String recipientEmail = "notification.recipient.email";
                String subject = "Vignette expiration alert";
                String message = buildVignetteExpirationMessage(vignette);
                emailService.sendNotificationEmail(recipientEmail, subject, message);
            }
        }
    }

    private String buildVignetteExpirationMessage(Vignette vignette) {
        return "Dear manager, \n\n For employee " + vignette.getCar().getEmployee().getName() + ", position: " + vignette.getCar().getEmployee().getPosition() +
                ", the vignette for the car with number " + vignette.getCar().getNumber() +
                " will expire in 3 days. Expiration date: " + vignette.getExpirationDate().toString();
    }

    @Override
    public void notifyAboutExpiringCivilInsurance() {
        List<CivilInsuranceDTO> allCivilInsurances = civilInsuranceService.getAllCivilInsurances();

        // Перебираем страховки для определения тех, которые истекают через 3 дня
        for (CivilInsuranceDTO civilInsuranceDTO : allCivilInsurances) {
            if (civilInsuranceDTO.getExpirationDate().minusDays(3).isEqual(LocalDate.now())) {
                CivilInsurance civilInsurance = civilInsuranceMapper.toEntity(civilInsuranceDTO);
                String recipientEmail = "notification.recipient.email";
                String subject = "Civil Insurance expiration alert";
                String message = buildCivilInsuranceExpirationMessage(civilInsurance);
                emailService.sendNotificationEmail(recipientEmail, subject, message);
            }
        }
    }

    private String buildCivilInsuranceExpirationMessage(CivilInsurance civilInsurance) {
        return "Dear manager, \n\n For employee " + civilInsurance.getCar().getEmployee().getName() + ", position " + civilInsurance.getCar().getEmployee().getPosition() +
                ", the civil insurance for the car with number " + civilInsurance.getCar().getNumber() +
                " will expire in 3 days. Expiration date: " + civilInsurance.getExpirationDate().toString();
    }

    @Override
    public void notifyAboutExpiringCASKO() {
        List<CASKODTO> allCASKOs = caskoService.getAllCASKOs();

        // Iterate through CASKOs to determine those expiring in 3 days
        for (CASKODTO caskoDTO : allCASKOs) {
            if (caskoDTO.getExpirationDate().minusDays(3).isEqual(LocalDate.now())) {
                CASKO casko = caskoMapper.toEntity(caskoDTO);
                String recipientEmail = "notification.recipient.email";
                String subject = "CASKO expiration alert";
                String message = buildCASKOExpirationMessage(casko);
                emailService.sendNotificationEmail(recipientEmail, subject, message);
            }
        }
    }

    private String buildCASKOExpirationMessage(CASKO casko) {
        return "Dear manager,\n\n For employee " + casko.getCar().getEmployee().getName() +
                ", position: " + casko.getCar().getEmployee().getPosition() +
                ", the CASKO insurance for the car with number " + casko.getCar().getNumber() +
                " will expire in 3 days. Expiration date: " + casko.getExpirationDate().toString();
    }

    @Override
    public void notifyAboutExpiringLicense() {
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();

        // Перебираем всех сотрудников для определения тех, у которых истекает лицензия через 3 дня
        for (EmployeeDTO employee : allEmployees) {
            if (employee.getLicenseExpirationDate().minusDays(3).isEqual(LocalDate.now())) {
                String recipientEmail = "notification.recipient.email";
                String subject = "License expiration alert";
                String message = buildLicenseExpirationMessage(employee);
                emailService.sendNotificationEmail(recipientEmail, subject, message);
            }
        }
    }

    private String buildLicenseExpirationMessage(EmployeeDTO employee) {
        return "Dear manager,\n\n For employee " + employee.getName() + ", position: " + employee.getPosition() +
                ", the license will expire in 3 days. Expiration date: " + employee.getLicenseExpirationDate().toString();
    }

    @Override
    public void notifyAboutExpiringTechnicalInspection() {
        List<TechnicalInspectionDTO> allTechnicalInspections = technicalInspectionService.getAllTechnicalInspections();

        // Iterate through technical inspections to determine those expiring in 3 days
        for (TechnicalInspectionDTO technicalInspectionDTO : allTechnicalInspections) {
            if (technicalInspectionDTO.getDateNextInspection().minusDays(3).isEqual(LocalDate.now())) {
                String recipientEmail = "notification.recipient.email";
                String subject = "Technical Inspection expiration alert";
                String message = buildTechnicalInspectionExpirationMessage(technicalInspectionMapper.toEntity(technicalInspectionDTO));
                emailService.sendNotificationEmail(recipientEmail, subject, message);
            }
        }
    }

    private String buildTechnicalInspectionExpirationMessage(TechnicalInspection technicalInspection) {
        return "Dear manager,\n\n  For employee " + technicalInspection.getCar().getEmployee().getName() + ", position: " + technicalInspection.getCar().getEmployee().getPosition() +
                ", the technical inspection insurance for the car with number " + technicalInspection.getCar().getNumber() +
                " will expire in 3 days. Next inspection date: " + technicalInspection.getDateNextInspection().toString();
    }

    @Scheduled(cron = "0 0 10 * * *") // каждый день в 10:00 утра
    public void sendDocumentExpirationNotifications() {
        notifyAboutExpiringVignette();
        notifyAboutExpiringCivilInsurance();
        notifyAboutExpiringCASKO();
        notifyAboutExpiringLicense();
        notifyAboutExpiringTechnicalInspection();
    }
    

}