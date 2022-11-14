module no.ntnu.cardsnap.rest {
  requires transitive no.ntnu.cardsnap.core;
  requires transitive no.ntnu.cardsnap.persistence;

  requires spring.core;
  requires spring.web;
  requires spring.beans;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;

  opens no.ntnu.cardsnap.rest to spring.beans, spring.core, spring.context, spring.web, com.google.gson;
  opens no.ntnu.cardsnap.rest.services to spring.beans, spring.core, spring.context, spring.web, com.google.gson;
  opens no.ntnu.cardsnap.rest.controllers to spring.beans, spring.core, spring.context, spring.web, com.google.gson;
  opens no.ntnu.cardsnap.rest.repositories to spring.beans, spring.core, spring.context, spring.web, com.google.gson;
}
