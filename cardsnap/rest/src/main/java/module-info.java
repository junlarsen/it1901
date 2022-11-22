module no.ntnu.cardsnap.rest {
  requires no.ntnu.cardsnap.core;
  requires no.ntnu.cardsnap.types;
  requires no.ntnu.cardsnap.persistence;

  requires com.google.gson;
  requires spring.core;
  requires spring.web;
  requires spring.beans;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;
  requires io.swagger.v3.oas.annotations;

  opens no.ntnu.cardsnap.rest to spring.beans, spring.core, spring.context, spring.web, com.google.gson;
}
