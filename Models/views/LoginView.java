package com.bestfit.demo.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Anmelden")
@Route(value = "login", layout = com.bestfit.demo.layouts.MainLayout.class)
public class LoginView extends Div {

    public LoginView() {
        addClassName("login-view");

        // Titel
        H1 title = new H1("Anmelden");
        title.addClassName("login-title");

        // E-Mail-Feld
        EmailField emailField = new EmailField("E-Mail");
        emailField.setPlaceholder("Geben Sie Ihre E-Mail-Adresse ein");
        emailField.addClassName("login-email");

        // Passwortfeld
        PasswordField passwordField = new PasswordField("Passwort");
        passwordField.setPlaceholder("Geben Sie Ihr Passwort ein");
        passwordField.addClassName("login-password");

        // "Angemeldet bleiben" und "Passwort vergessen" Layout
        Checkbox rememberMe = new Checkbox("Angemeldet bleiben");
        rememberMe.addClassName("remember-me");

        Anchor forgotPassword = new Anchor("#", "Passwort vergessen?");
        forgotPassword.addClassName("forgot-password");
        forgotPassword.getElement().addEventListener("click", e -> openForgotPasswordDialog());

        HorizontalLayout rememberAndForgot = new HorizontalLayout(rememberMe, forgotPassword);
        rememberAndForgot.addClassName("remember-forgot-layout");

        // Anmeldebutton
        Button loginButton = new Button("Anmelden");
        loginButton.addClassName("login-button1");
        loginButton.addClickListener(event -> Notification.show("Anmelden geklickt!"));

        // Vertikales Layout
        VerticalLayout layout = new VerticalLayout(title, emailField, passwordField, rememberAndForgot, loginButton);
        layout.setAlignItems(Alignment.CENTER); // Zentrieren
        layout.addClassName("login-layout");

        add(layout);
    }

    private void openForgotPasswordDialog() {
        // Dialog für "Passwort vergessen"
        Dialog dialog = new Dialog();

        H1 dialogTitle = new H1("Passwort zurücksetzen");
        dialogTitle.addClassName("dialog-title");

        EmailField emailField = new EmailField("E-Mail");
        emailField.setPlaceholder("Geben Sie Ihre E-Mail-Adresse ein");
        emailField.setWidthFull();

        Button sendButton = new Button("Senden");
        sendButton.addClickListener(event -> {
            Notification.show("Link zum Zurücksetzen des Passworts wurde gesendet an: " + emailField.getValue());
            dialog.close();
        });
        sendButton.addClassName("dialog-send-button");

        dialog.add(dialogTitle, emailField, sendButton);
        dialog.open();
    }
}
