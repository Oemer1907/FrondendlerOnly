package com.bestfit.demo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.MainLayout;

@Route(value = "contact", layout = MainLayout.class) // Einzigartige Route: "contact"
public class ContactView extends Div {
    public ContactView() {
        addClassName("contact-view");

        // Titel
        H2 title = new H2("Kontaktieren Sie uns");
        title.addClassName("contact-title");

        // Kontaktdaten
        Paragraph phone = new Paragraph("Telefon: +1 (555) 123-4567");
        phone.addClassName("contact-info");

        Paragraph email = new Paragraph("E-Mail: kontakt@bestfit.com");
        email.addClassName("contact-info");

        Paragraph address = new Paragraph("Adresse: Hauptstraße 123, Fitnessstadt, Fitland 98765");
        address.addClassName("contact-info");

        VerticalLayout layout = new VerticalLayout(
            title,
            phone,
            email,
            address
        );
        layout.setSpacing(true);
        layout.setPadding(true);
        layout.setAlignItems(Alignment.BASELINE); // Inhalte ausrichten

        add(layout);
    }
}
