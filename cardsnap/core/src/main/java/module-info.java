module no.ntnu.cardsnap.core {
  exports no.ntnu.cardsnap.core;

  requires com.google.gson;
  requires no.ntnu.cardsnap.types;
  requires no.ntnu.cardsnap.persistence;

  opens no.ntnu.cardsnap.core to spring.beans, spring.core, spring.context, spring.web, com.google.gson;
}
