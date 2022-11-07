module no.ntnu.cardsnap.domain {
    exports no.ntnu.cardsnap.domain;

    requires transitive com.google.gson;

    opens no.ntnu.cardsnap.domain to com.google.gson;
}
