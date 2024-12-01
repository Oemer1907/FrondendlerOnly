package com.bestfit.demo.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dienstleistungen")
@Route(value = "services", layout = com.bestfit.demo.layouts.MainLayout.class)
public class ServicesView extends Div {

    private Checkbox basicCheckbox;
    private Checkbox premiumCheckbox;

    public ServicesView() {
        addClassName("services-view");

        H2 mainTitle = new H2("PREISLISTE");
        mainTitle.addClassName("main-title");

        Paragraph subTitle = new Paragraph("MITGLIEDSCHAFTEN");
        subTitle.addClassName("sub-title");

        // Service-Karten
        Div basicCard = createCard("BASIS", "15€/Monat", "Standardausrüstung", "8:00 - 23:00 Uhr");
        Div premiumCard = createCard("PREMIUM", "25€/Monat", "Standardausrüstung", "Premiumausrüstung", "Rund um die Uhr geöffnet");

        // Kartenlayout
        HorizontalLayout cardLayout = new HorizontalLayout(basicCard, premiumCard);
        cardLayout.setSpacing(true);
        cardLayout.setWidthFull();
        cardLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); // Karten zentrieren

        // Mitgliedschafts-Checkboxen
        basicCheckbox = new Checkbox("Basis");
        premiumCheckbox = new Checkbox("Premium");

        HorizontalLayout checkboxLayout = new HorizontalLayout(basicCheckbox, premiumCheckbox);
        checkboxLayout.setSpacing(true);
        checkboxLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        checkboxLayout.addClassName("checkbox-layout");

        // Nur eine Checkbox auswählen lassen
        basicCheckbox.addValueChangeListener(event -> {
            if (basicCheckbox.getValue()) {
                premiumCheckbox.setValue(false);
            }
        });
        premiumCheckbox.addValueChangeListener(event -> {
            if (premiumCheckbox.getValue()) {
                basicCheckbox.setValue(false);
            }
        });

        // Jetzt beitreten-Button
        Button joinButton = new Button("Jetzt beitreten", event -> openJoinDialog());
        joinButton.addClassName("join-button");
        joinButton.getStyle().set("background-color", "#FFA500");
        joinButton.getStyle().set("color", "white");

        // Fußzeile
        HorizontalLayout footerLayout = new HorizontalLayout();
        footerLayout.addClassName("footer-layout");
        footerLayout.add(createFooterItem("123 Irgendwo Straße, Irgendeine Stadt", "location.png"));
        footerLayout.add(createFooterItem("Bestfit.com", "websiteicon.png"));
        footerLayout.add(createFooterItem("bestfit@fitness.com", "mail.png"));

        footerLayout.setWidthFull();
        footerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER); // Fußzeile zentrieren

        add(mainTitle, subTitle, cardLayout, checkboxLayout, joinButton, footerLayout);
    }

    private Div createCard(String title, String price, String... features) {
        Div card = new Div();
        card.addClassName("service-card");
        card.getStyle().set("border", "1px solid #ccc");
        card.getStyle().set("border-radius", "8px");
        card.getStyle().set("padding", "16px");
        card.getStyle().set("text-align", "center");
        card.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.1)");

        H2 cardTitle = new H2(title);
        cardTitle.addClassName("card-title");

        Paragraph cardPrice = new Paragraph(price);
        cardPrice.addClassName("card-price");

        VerticalLayout featureList = new VerticalLayout();
        featureList.addClassName("feature-list");
        featureList.setSpacing(false);
        featureList.setPadding(false);

        for (String feature : features) {
            Paragraph featureText = new Paragraph("✔ " + feature);
            featureText.addClassName("feature-text");
            featureList.add(featureText);
        }

        Image icon = new Image("/images/rocket.png", "Raketen-Symbol");
        icon.addClassName("card-icon");

        card.add(cardTitle, cardPrice, icon, featureList);
        return card;
    }

    private Div createFooterItem(String text, String iconPath) {
        Div footerItem = new Div();
        footerItem.addClassName("footer-item");

        Image icon = new Image("/images/" + iconPath, "Fußzeilen-Symbol");
        icon.addClassName("footer-icon");

        Paragraph footerText = new Paragraph(text);
        footerText.addClassName("footer-text");

        footerItem.add(icon, footerText);
        return footerItem;
    }

    private void openJoinDialog() {
        if (!basicCheckbox.getValue() && !premiumCheckbox.getValue()) {
            Notification.show("Bitte wählen Sie eine Mitgliedschaftsart aus.", 3000, Notification.Position.MIDDLE);
            return;
        }

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitgliedschaftsinformationen");

        TextField nameField = new TextField("Vorname");
        TextField surnameField = new TextField("Nachname");
        TextField phoneField = new TextField("Telefon");
        EmailField emailField = new EmailField("E-Mail");
        TextArea addressField = new TextArea("Adresse");

        Button saveButton = new Button("Speichern", event -> {
            Notification.show("Ihre Informationen wurden gespeichert! (E-Mail-Simulation)", 3000, Notification.Position.MIDDLE);
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(nameField, surnameField, phoneField, emailField, addressField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }
}
