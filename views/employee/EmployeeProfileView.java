package com.bestfit.demo.views.employee;

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
import com.bestfit.demo.layouts.EmployeeLayout;

@PageTitle("Employee Profile")
@Route(value = "employee/profile", layout = EmployeeLayout.class)
public class EmployeeProfileView extends VerticalLayout {

    public EmployeeProfileView() {
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        // Başlık
        H2 header = new H2("Employee Profile");

        // Form Alanları
        TextField firstName = new TextField("First Name");
        firstName.setPlaceholder("Enter your first name");

        TextField lastName = new TextField("Last Name");
        lastName.setPlaceholder("Enter your last name");

        EmailField email = new EmailField("Email");
        email.setPlaceholder("Enter your email");

        TextField phone = new TextField("Phone");
        phone.setPlaceholder("Enter your phone number");

        TextField age = new TextField("Age");
        age.setPlaceholder("Enter your age");

        TextField height = new TextField("Height (cm)");
        height.setPlaceholder("Enter your height");

        TextField weight = new TextField("Weight (kg)");
        weight.setPlaceholder("Enter your weight");

        TextField address = new TextField("Address");
        address.setPlaceholder("Enter your address");

        // Parola Alanı
        PasswordField password = new PasswordField("New Password");
        password.setPlaceholder("Enter a new password (if you want to change)");

        // Güncelle Butonu
        Button updateButton = new Button("Update Profile");
        updateButton.getStyle().set("background-color", "#007BFF");
        updateButton.getStyle().set("color", "white");

        // İki sütunlu satır düzeni
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

        // Form düzeni
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
