module no.ntnu.cardsnap {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.cardsnap to javafx.graphics, javafx.fxml;
}
