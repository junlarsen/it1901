module no.ntnu.cardsnap.core {
    exports no.ntnu.cardsnap.core;

    requires transitive com.google.gson;

    opens no.ntnu.cardsnap.core to com.google.gson;
}
