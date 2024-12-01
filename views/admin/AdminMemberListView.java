package com.bestfit.demo.views.admin;

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
import com.bestfit.demo.layouts.AdminLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Member List")
@Route(value = "admin/members", layout = AdminLayout.class)
public class AdminMemberListView extends VerticalLayout {

    private List<Member> members = new ArrayList<>();
    private Grid<Member> memberGrid;

    public AdminMemberListView() {
        setSpacing(true);
        setPadding(true);

        // Başlık ve "Add Member" Butonu
        Button addMemberButton = new Button("Add Member", event -> openAddMemberDialog());
        addMemberButton.getStyle().set("background-color", "#28a745");
        addMemberButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addMemberButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        memberGrid = new Grid<>(Member.class, false);
        memberGrid.addColumn(Member::getFirstName).setHeader("First Name");
        memberGrid.addColumn(Member::getLastName).setHeader("Last Name");
        memberGrid.addColumn(Member::getEmail).setHeader("Email");
        memberGrid.addColumn(Member::getPhone).setHeader("Phone");
        memberGrid.addColumn(Member::getMembershipType).setHeader("Membership Type"); // Üyelik Tipi
        memberGrid.addComponentColumn(member -> createActions(member)).setHeader("Actions");

        // Örnek Üyeler
        members.add(new Member("John", "Doe", "john.doe@example.com", "123456789", "Premium"));
        members.add(new Member("Jane", "Smith", "jane.smith@example.com", "987654321", "Standard"));
        memberGrid.setItems(members);

        add(headerLayout, memberGrid);
    }

    private void openAddMemberDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Member");

        TextField firstNameField = new TextField("First Name");
        firstNameField.setPlaceholder("Enter first name");

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setPlaceholder("Enter last name");

        EmailField emailField = new EmailField("Email");
        emailField.setPlaceholder("Enter email");

        TextField phoneField = new TextField("Phone");
        phoneField.setPlaceholder("Enter phone number");

        ComboBox<String> membershipTypeField = new ComboBox<>("Membership Type");
        membershipTypeField.setItems("Premium", "Standard");
        membershipTypeField.setPlaceholder("Select membership type");

        Button saveButton = new Button("Save Member", event -> {
            if (firstNameField.isEmpty() || lastNameField.isEmpty() || emailField.isEmpty() || phoneField.isEmpty() || membershipTypeField.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                String firstName = firstNameField.getValue();
                String lastName = lastNameField.getValue();
                String email = emailField.getValue();
                String phone = phoneField.getValue();
                String membershipType = membershipTypeField.getValue();

                members.add(new Member(firstName, lastName, email, phone, membershipType));
                memberGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField, lastNameField, emailField, phoneField, membershipTypeField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private VerticalLayout createActions(Member member) {
        Button editButton = new Button("Edit", event -> openEditDialog(member));
        editButton.getStyle().set("background-color", "#007BFF");
        editButton.getStyle().set("color", "white");

        Button deleteButton = new Button("Delete", event -> {
            members.remove(member);
            memberGrid.getDataProvider().refreshAll();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");

        return new VerticalLayout(editButton, deleteButton);
    }

    private void openEditDialog(Member member) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit Member");

        TextField firstNameField = new TextField("First Name");
        firstNameField.setValue(member.getFirstName());

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setValue(member.getLastName());

        EmailField emailField = new EmailField("Email");
        emailField.setValue(member.getEmail());

        TextField phoneField = new TextField("Phone");
        phoneField.setValue(member.getPhone());

        ComboBox<String> membershipTypeField = new ComboBox<>("Membership Type");
        membershipTypeField.setItems("Premium", "Standard");
        membershipTypeField.setValue(member.getMembershipType());

        Button saveButton = new Button("Save Changes", event -> {
            member.setFirstName(firstNameField.getValue());
            member.setLastName(lastNameField.getValue());
            member.setEmail(emailField.getValue());
            member.setPhone(phoneField.getValue());
            member.setMembershipType(membershipTypeField.getValue());
            memberGrid.getDataProvider().refreshAll();
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField, lastNameField, emailField, phoneField, membershipTypeField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Üye Modeli
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
