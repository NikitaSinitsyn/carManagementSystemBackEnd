package com.carManagment.carManagment.Emails.Notification;

public interface NotificationService {
    void notifyAboutExpiringVignette();
    void notifyAboutExpiringCivilInsurance();
    void notifyAboutExpiringCASKO();
    void notifyAboutExpiringLicense();
    void notifyAboutExpiringTechnicalInspection();
}
