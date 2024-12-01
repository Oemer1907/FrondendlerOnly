package com.bestfit.demo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.MainLayout;

@Route(value = "impressum", layout = MainLayout.class) // Einzigartige Route: "impressum"
public class ImpressumView extends Div {
    public ImpressumView() {
        addClassName("impressum-view");

        // Titel
        H2 title = new H2("Impressum");
        title.addClassName("impressum-title");

        // Unternehmensinformationen
        Paragraph companyName = new Paragraph("Firmenname: BestFit Fitness Center");
        companyName.addClassName("company-info");

        Paragraph email = new Paragraph("E-Mail: kontakt@bestfit.com");
        email.addClassName("company-info");

        Paragraph phone = new Paragraph("Telefon: +1 (555) 123-4567");
        phone.addClassName("company-info");

        Paragraph address = new Paragraph("Adresse: Hauptstraße 123, Fitnessstadt, Fitland 98765");
        address.addClassName("company-info");

        Paragraph description = new Paragraph(
            "Willkommen im BestFit Fitness Center! Wir sind engagiert, Ihnen mit erstklassiger Ausstattung und einer unterstützenden Gemeinschaft zu helfen, Ihre Fitnessziele zu erreichen."
        );
        description.addClassName("company-description");

        // Layout
        VerticalLayout layout = new VerticalLayout(title, companyName, email, phone, address, description);
        layout.setSpacing(true);
        layout.setPadding(true);
        layout.setAlignItems(Alignment.BASELINE); // Inhalte ausrichten

        add(layout);
    }
}
