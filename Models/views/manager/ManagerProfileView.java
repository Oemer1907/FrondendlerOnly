package com.bestfit.demo.views.manager;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.ManagerLayout;

@PageTitle("Profil")
@Route(value = "manager/profile", layout = ManagerLayout.class)
public class ManagerProfileView extends VerticalLayout {

    public ManagerProfileView() {
        setSpacing(true);
        setAlignItems(Alignment.CENTER); // Inhalte zentrieren

        // Überschrift
        H2 header = new H2("Manager-Profilinformationen");

        // Formularfelder
        TextField firstName = new TextField("Vorname");
        firstName.setPlaceholder("Geben Sie Ihren Vornamen ein");

        TextField lastName = new TextField("Nachname");
        lastName.setPlaceholder("Geben Sie Ihren Nachnamen ein");

        EmailField email = new EmailField("E-Mail");
        email.setPlaceholder("Geben Sie Ihre E-Mail ein");

        TextField phone = new TextField("Telefon");
        phone.setPlaceholder("Geben Sie Ihre Telefonnummer ein");

        TextField department = new TextField("Abteilung");
        department.setPlaceholder("Geben Sie Ihre Abteilung ein");

        TextField role = new TextField("Rolle");
        role.setPlaceholder("Geben Sie Ihre Rolle ein");

        TextField address = new TextField("Adresse");
        address.setPlaceholder("Geben Sie Ihre Adresse ein");

        // Passwortfeld
        PasswordField password = new PasswordField("Neues Passwort");
        password.setPlaceholder("Geben Sie ein neues Passwort ein (falls gewünscht)");

        // Aktualisieren-Button
        Button updateButton = new Button("Profil aktualisieren");
        updateButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        updateButton.getStyle().set("color", "white"); // Weißer Text

        // Zeilen mit zwei Spalten erstellen
        HorizontalLayout row1 = new HorizontalLayout(firstName, lastName);
        row1.setWidthFull();
        row1.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN); // Inhalte verteilen

        HorizontalLayout row2 = new HorizontalLayout(email, phone);
        row2.setWidthFull();
        row2.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row3 = new HorizontalLayout(department, role);
        row3.setWidthFull();
        row3.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row4 = new HorizontalLayout(address);
        row4.setWidthFull();
        row4.setJustifyContentMode(FlexLayout.JustifyContentMode.START); // Inhalte linksbündig

        // Formular-Layout
        VerticalLayout formLayout = new VerticalLayout(
            header,
            row1,
            row2,
            row3,
            row4,
            password,
            updateButton
        );

        formLayout.setWidth("50%"); // Breite des Formulars setzen
        formLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER); // Inhalte zentrieren

        add(formLayout);
    }
}
