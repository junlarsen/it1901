module no.ntnu.cardsnap.persistence {
  requires no.ntnu.cardsnap.types;
  requires com.google.gson;

  exports no.ntnu.cardsnap.persistence;

  opens no.ntnu.cardsnap.persistence to com.google.gson;
}
