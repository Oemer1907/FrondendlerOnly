package com.bestfit.demo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Pläne")
@Route(value = "plans", layout = com.bestfit.demo.layouts.MainLayout.class)
public class PlansView extends Div {

    public PlansView() {
        addClassName("plans-view");

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.addClassName("main-layout");

        VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.addClassName("left-layout");

        H2 title = new H2("TRAININGSPLAN");
        title.addClassName("title");
        leftLayout.add(title);

        leftLayout.add(createPlanItem("DIENSTAG", "18:00 - 20:00 Uhr", "Liegestütze, Klimmzüge, Cardio, Sit-ups"));
        leftLayout.add(createPlanItem("FREITAG", "18:00 - 20:00 Uhr", "Liegestütze, Klimmzüge, Cardio, Sit-ups"));
        leftLayout.add(createPlanItem("SONNTAG", "18:00 - 20:00 Uhr", "Liegestütze, Klimmzüge, Cardio, Sit-ups"));

        Image image = new Image("/images/service-4.jpg", "Bild des Trainingsplans");
        image.addClassName("right-image");

        mainLayout.add(leftLayout, image);
        add(mainLayout);
    }

    private Div createPlanItem(String day, String time, String activities) {
        Div planItem = new Div();
        planItem.addClassName("plan-item");

        H2 dayText = new H2(day);
        dayText.addClassName("plan-day");

        Div timeText = new Div();
        timeText.setText(time);
        timeText.addClassName("plan-time");

        Div activitiesText = new Div();
        activitiesText.setText(activities);
        activitiesText.addClassName("plan-activities");

        VerticalLayout leftPart = new VerticalLayout(dayText, timeText);
        leftPart.addClassName("plan-left");

        VerticalLayout rightPart = new VerticalLayout(activitiesText);
        rightPart.addClassName("plan-right");

        HorizontalLayout planLayout = new HorizontalLayout(leftPart, rightPart);
        planLayout.addClassName("plan-layout");
        planItem.add(planLayout);

        return planItem;
    }
}
