module no.ntnu.cardsnap.persistence {
    requires transitive no.ntnu.cardsnap.core;
    requires transitive com.google.gson;

    exports no.ntnu.cardsnap.persistence;

    opens no.ntnu.cardsnap.persistence to com.google.gson;
}
