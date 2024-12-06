package com.bestfit.demo.views.manager;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.ManagerLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Mitgliederliste")
@Route(value = "manager/members", layout = ManagerLayout.class)
public class ManagerMembersView extends VerticalLayout {

    private List<Member> members = new ArrayList<>(); // Liste der Mitglieder
    private Grid<Member> memberGrid; // Tabelle zur Anzeige der Mitglieder

    public ManagerMembersView() {
        setSpacing(true);
        setPadding(true);

        // Titel und "Mitglied hinzufügen"-Button
        Button addMemberButton = new Button("Mitglied hinzufügen", event -> openAddMemberDialog());
        addMemberButton.getStyle().set("background-color", "#28a745"); // Grüner Hintergrund
        addMemberButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout headerLayout = new HorizontalLayout(addMemberButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END); // Button rechts ausrichten

        // Tabelle erstellen
        memberGrid = new Grid<>(Member.class, false);
        memberGrid.addColumn(Member::getFirstName).setHeader("Vorname");
        memberGrid.addColumn(Member::getLastName).setHeader("Nachname");
        memberGrid.addColumn(Member::getEmail).setHeader("E-Mail");
        memberGrid.addColumn(Member::getPhone).setHeader("Telefon");
        memberGrid.addColumn(Member::getMembershipType).setHeader("Mitgliedschaftstyp");
        memberGrid.addComponentColumn(member -> createActions(member)).setHeader("Aktionen");

        // Beispiel-Mitglieder hinzufügen
        members.add(new Member("John", "Doe", "john.doe@example.com", "123456789", "Premium"));
        members.add(new Member("Jane", "Smith", "jane.smith@example.com", "987654321", "Standard"));
        memberGrid.setItems(members);

        add(headerLayout, memberGrid);
    }

    // Dialog zum Hinzufügen eines neuen Mitglieds öffnen
    private void openAddMemberDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Neues Mitglied hinzufügen");

        TextField firstNameField = new TextField("Vorname");
        firstNameField.setPlaceholder("Vorname eingeben");

        TextField lastNameField = new TextField("Nachname");
        lastNameField.setPlaceholder("Nachname eingeben");

        EmailField emailField = new EmailField("E-Mail");
        emailField.setPlaceholder("E-Mail eingeben");

        TextField phoneField = new TextField("Telefon");
        phoneField.setPlaceholder("Telefonnummer eingeben");

        ComboBox<String> membershipTypeField = new ComboBox<>("Mitgliedschaftstyp");
        membershipTypeField.setItems("Premium", "Standard");
        membershipTypeField.setPlaceholder("Mitgliedschaftstyp auswählen");

        Button saveButton = new Button("Mitglied speichern", event -> {
            if (firstNameField.isEmpty() || lastNameField.isEmpty() || emailField.isEmpty() || phoneField.isEmpty() || membershipTypeField.isEmpty()) {
                Notification.show("Alle Felder sind erforderlich!", 3000, Notification.Position.MIDDLE);
            } else {
                String firstName = firstNameField.getValue();
                String lastName = lastNameField.getValue();
                String email = emailField.getValue();
                String phone = phoneField.getValue();
                String membershipType = membershipTypeField.getValue();

                members.add(new Member(firstName, lastName, email, phone, membershipType));
                memberGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField, lastNameField, emailField, phoneField, membershipTypeField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Aktionen (Bearbeiten, Löschen) für jedes Mitglied erstellen
    private VerticalLayout createActions(Member member) {
        Button editButton = new Button("Bearbeiten", event -> openEditDialog(member));
        editButton.getStyle().set("background-color", "#007BFF"); // Blauer Hintergrund
        editButton.getStyle().set("color", "white"); // Weißer Text

        Button deleteButton = new Button("Löschen", event -> {
            members.remove(member); // Mitglied aus der Liste entfernen
            memberGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
        });
        deleteButton.getStyle().set("background-color", "#DC3545"); // Roter Hintergrund
        deleteButton.getStyle().set("color", "white"); // Weißer Text

        return new VerticalLayout(editButton, deleteButton);
    }

    // Dialog zum Bearbeiten eines Mitglieds öffnen
    private void openEditDialog(Member member) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitglied bearbeiten");

        TextField firstNameField = new TextField("Vorname");
        firstNameField.setValue(member.getFirstName());

        TextField lastNameField = new TextField("Nachname");
        lastNameField.setValue(member.getLastName());

        EmailField emailField = new EmailField("E-Mail");
        emailField.setValue(member.getEmail());

        TextField phoneField = new TextField("Telefon");
        phoneField.setValue(member.getPhone());

        ComboBox<String> membershipTypeField = new ComboBox<>("Mitgliedschaftstyp");
        membershipTypeField.setItems("Premium", "Standard");
        membershipTypeField.setValue(member.getMembershipType());

        Button saveButton = new Button("Änderungen speichern", event -> {
            member.setFirstName(firstNameField.getValue());
            member.setLastName(lastNameField.getValue());
            member.setEmail(emailField.getValue());
            member.setPhone(phoneField.getValue());
            member.setMembershipType(membershipTypeField.getValue());
            memberGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField, lastNameField, emailField, phoneField, membershipTypeField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Mitgliederklasse
    public static class Member {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String membershipType;

        public Member(String firstName, String lastName, String email, String phone, String membershipType) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.membershipType = membershipType;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMembershipType() {
            return membershipType;
        }

        public void setMembershipType(String membershipType) {
            this.membershipType = membershipType;
        }
    }
}
