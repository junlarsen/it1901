package no.ntnu.cardsnap;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private Label output;

    private Counter counter;
    private DiskCounterStorage dsc;

    @FXML
    private void initialize() {
        dsc = new DiskCounterStorage("src/main/resources/storage");
        try {
            counter = dsc.load();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        updateOutput();
    }

    @FXML
    private void count() {
        counter.increment();

        try {
            dsc.store(counter);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        updateOutput();
    }

    private void updateOutput() {
        this.output.setText(String.valueOf(counter.getValue()));
    }

}
