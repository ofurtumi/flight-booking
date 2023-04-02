module is.hi.flight_booking {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;

    exports is.hi.flight_booking.application;
    exports is.hi.flight_booking.controller;
    exports is.hi.flight_booking.interfaces;
    exports is.hi.flight_booking.ui;
  }