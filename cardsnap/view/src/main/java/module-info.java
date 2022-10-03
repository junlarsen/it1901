module no.ntnu.cardsnap.view {
    requires transitive no.ntnu.cardsnap.core;
    requires transitive no.ntnu.cardsnap.domain;

    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.cardsnap.view to javafx.graphics, javafx.fxml;
}
