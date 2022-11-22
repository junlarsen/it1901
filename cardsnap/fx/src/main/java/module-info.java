module no.ntnu.cardsnap.fx {
  requires no.ntnu.cardsnap.core;
  requires no.ntnu.cardsnap.types;
  requires no.ntnu.cardsnap.persistence;

  requires javafx.controls;
  requires javafx.fxml;

  opens no.ntnu.cardsnap.fx to javafx.graphics, javafx.fxml;
}
