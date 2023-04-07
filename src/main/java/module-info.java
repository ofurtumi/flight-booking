module is.hi.flight_booking {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports is.hi.flight_booking.application;
    exports is.hi.flight_booking.controller;
    exports is.hi.flight_booking.interfaces;
  }