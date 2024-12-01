package com.bestfit.demo.views.admin;

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
import com.bestfit.demo.layouts.AdminLayout;

@PageTitle("Admin Profile")
@Route(value = "admin/profile", layout = AdminLayout.class)
public class AdminProfileView extends VerticalLayout {

    public AdminProfileView() {
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        // Başlık
        H2 header = new H2("Admin Profile");

        // Form Alanları
        TextField firstName = new TextField("First Name");
        firstName.setPlaceholder("Enter your first name");

        TextField lastName = new TextField("Last Name");
        lastName.setPlaceholder("Enter your last name");

        EmailField email = new EmailField("Email");
        email.setPlaceholder("Enter your email");

        TextField phone = new TextField("Phone");
        phone.setPlaceholder("Enter your phone number");

        TextField role = new TextField("Role");
        role.setValue("Admin");
        role.setReadOnly(true);

        TextField address = new TextField("Address");
        address.setPlaceholder("Enter your address");

        // Parola Alanı
        PasswordField password = new PasswordField("New Password");
        password.setPlaceholder("Enter a new password (if you want to change)");

        // Güncelle Butonu
        Button updateButton = new Button("Update Profile");
        updateButton.getStyle().set("background-color", "#FF5733");
        updateButton.getStyle().set("color", "white");

        // İki sütunlu satır düzeni
        HorizontalLayout row1 = new HorizontalLayout(firstName, lastName);
        row1.setWidthFull();
        row1.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row2 = new HorizontalLayout(email, phone);
        row2.setWidthFull();
        row2.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        HorizontalLayout row3 = new HorizontalLayout(role, address);
        row3.setWidthFull();
        row3.setJustifyContentMode(FlexLayout.JustifyContentMode.BETWEEN);

        // Form düzeni
        VerticalLayout formLayout = new VerticalLayout(
            header,
            row1,
            row2,
            row3,
            password,
            updateButton
        );

        formLayout.setWidth("50%");
        formLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(formLayout);
    }
}
