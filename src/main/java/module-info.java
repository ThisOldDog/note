module pers.dog.note {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires org.apache.commons.lang3;

    opens pers.dog.note to javafx.fxml;
    exports pers.dog.note;
    exports pers.dog.note.controller;
    exports pers.dog.note.component.application.status;
}