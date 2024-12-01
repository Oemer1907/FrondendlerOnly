package com.bestfit.demo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Startseite")
@Route(value = "", layout = com.bestfit.demo.layouts.MainLayout.class) 
public class MainView extends Div {

    public MainView() {
        Section sliderSection = new Section();
        sliderSection.addClassName("slider-section");

        Image sliderImage = new Image("/images/o-p-o-3.jpg", "Slider-Bild");
        sliderImage.addClassName("slider-image");

        Div textOverlay = new Div();
        textOverlay.addClassName("slider-text-overlay");

        H1 mainText = new H1("Formen Sie Ihren perfekten Körper");
        mainText.addClassName("slider-main-text");

        Div subText = new Div();
        subText.setText("Werden Sie Mitglied und beginnen Sie Ihre Reise!");
        subText.addClassName("slider-sub-text");

        Div button = new Div();
        button.setText("Jetzt Mitglied werden");
        button.addClassName("slider-button");

        textOverlay.add(mainText, subText, button);

        sliderSection.add(sliderImage, textOverlay);
        add(sliderSection);
    }
}
