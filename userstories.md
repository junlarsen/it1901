# User stories Cardsnap

This is a collection of the user stories for the project. They will help us to develop a product that solves the real requirements and problems an end-user of the system wil have.
We have formated our user stories on the format:

> "As [a user persona], I want [to perform this action] so that [I can accomplish this goal]"

## Launch app (us-1)

> As a user I want to be able to start the application because I want to use it

The user has to be able to launch an app-view to interact with the app.

### Comment

We have realized that this user story is quiet simple, but we planned on using a lot of time on setting up the framework of the app.

## Create content in app (us-2)

> As a user I want to create Card Decks in the application because I want to create cards in them

The user needs to be able to create an empty deck so it later can contain cards. As we see it the deck only needs a name. We discussed the need of a description, but we have chosen to not implement this, because we think that the name will be descriptive enough.

After the user has created card decks it would be nice to have a list of available decks. This list should contain some data about the deck, here we think the number of cards in the deck fits well. The user should also find buttons to edit/add cards or edit the deck here.

After the user has created a deck, the user should be able to add new cards to this deck. A card should contain a question and an answer. The user should be able to add as many questions as it wants.

## Edit Card decks (us-3)

> As a user I want to be able to remove card decks because I no longer wish to study them

It should be possible to delete an existing card deck when the user doesn't want to keep it anymore. This can be for a number of reasons:

-   They no longer wish to study the cards in the deck, maybe the passed the course and no longer need the cards?
-   They created a deck by accident and want to remove it
-   They are finished with a course at school and no longer want that deck to be a part of the app

**Comment** We didn't got this far but have chosen to implement this feature in the next sprint
