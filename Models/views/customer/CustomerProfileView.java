package com.bestfit.demo.views.customer;

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
import com.bestfit.demo.layouts.CustomerLayout;

@PageTitle("Profil")
@Route(value = "customer/profile", layout = CustomerLayout.class)
public class CustomerProfileView extends VerticalLayout {

    public CustomerProfileView() {
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        // Überschrift
        H2 header = new H2("Profilinformationen");

        // Formularfelder
        TextField firstName = new TextField("Vorname");
        firstName.setPlaceholder("Geben Sie Ihren Vornamen ein");

        TextField lastName = new TextField("Nachname");
        lastName.setPlaceholder("Geben Sie Ihren Nachnamen ein");

        EmailField email = new EmailField("E-Mail");
        email.setPlaceholder("Geben Sie Ihre E-Mail-Adresse ein");

        TextField phone = new TextField("Telefon");
        phone.setPlaceholder("Geben Sie Ihre Telefonnummer ein");

        TextField age = new TextField("Alter");
        age.setPlaceholder("Geben Sie Ihr Alter ein");

        TextField height = new TextField("Größe (cm)");
        height.setPlaceholder("Geben Sie Ihre Größe ein");

        TextField weight = new TextField("Gewicht (kg)");
        weight.setPlaceholder("Geben Sie Ihr Gewicht ein");

        TextField address = new TextField("Adresse");
        address.setPlaceholder("Geben Sie Ihre Adresse ein");

        // Passwortfeld
        PasswordField password = new PasswordField("Neues Passwort");
        password.setPlaceholder("Geben Sie ein neues Passwort ein (falls Sie es ändern möchten)");

        // Aktualisieren-Button
        Button updateButton = new Button("Profil aktualisieren");
        updateButton.getStyle().set("background-color", "#FFA500");
        updateButton.getStyle().set("color", "white");

        // Zwei-spaltige Zeilenanordnung
        HorizontalLayout row1 = new HorizontalLayout(firstName, lastName);
        row1.setWidthFull();
        row1.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row2 = new HorizontalLayout(email, phone);
        row2.setWidthFull();
        row2.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row3 = new HorizontalLayout(age, height);
        row3.setWidthFull();
        row3.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row4 = new HorizontalLayout(weight, address);
        row4.setWidthFull();
        row4.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        // Formularlayout
        VerticalLayout formLayout = new VerticalLayout(
            header,
            row1,
            row2,
            row3,
            row4,
            password,
            updateButton
        );

        formLayout.setWidth("50%");
        formLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(formLayout);
    }
}
