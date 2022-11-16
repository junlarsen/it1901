# User stories Cardsnap

This is a collection of the user stories for the project. They will help us to develop a product that solves the real requirements and problems an end-user of the system wil have.
We have formated our user stories on the format:

> "As [a user persona], I want [to perform this action] so that [I can accomplish this goal]"

## Launch app (us-1)

> As a user I want to be able to start the application because I want to use it

The user has to be able to launch an app-view to interact with the app.

**Comment** We have realized that this user story is quiet simple, but we planned on using a
lot of time on setting up the framework of the app, and therefore waited with specific functionality

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

**Comment** We didn't got this far in release 2 but have chosen to implement this feature in the next iteration

### Edit deck name (user story 4)

> As a user I want to be able to change the name of a card deck because
> I could have made a mistake when creating it

It should be possible to change the name of a deck, possible reasons:

-   The user could have made a typo when creating the deck
-   The user could want to change to a more appropriate name

### Edit card (user story 5)

> As a user I want to be able to edit a card because
> I may have made a mistake when I created the card

There are many reasons to implement editing of cards. The user
should babe able to edit cards because...

-   ...the card could have typos
-   ...the answer could be wrong or uncomplete
-   ...the card could no longer be relevant
-   ...user may have learned more since the card was
    created and could want to add content

### Play decks (user story 6)

> As a user I want to practice by using the decks I have
> created because I want to improve my knowledge

When user opens the list of available decks it should be possible to
select a card deck, and then play with the cards in that deck. The
play-view should contain a card with the question in focus. It should
also be buttons to toggle between cards and a button to show the answer.

### Create deck (user story 7)

> As a user I want to be able to create a new deck in the web
> app, because I want to categorize my cards

If user wants to create a new deck it should be easy to create a new
deck in the app. User should select a name for the deck. We want the user
to be able to select the name becuse this wil makes it easier for user to
understand which decks that contains different kind of cards.

### Add card (user story 8)

> As a user I want to add cards to a deck in web app, because I
> want to improve a certain skill.

User should be able to add a new card to a given card deck
in the web app. The new card should contain a question and an answer.

### Delete card (user story 9)

> As a user I want to be avle to delete a card from a deck
> because I no longer want the card to be a part of the deck.

User should be able to delete an existing card from a given card deck.

-   User may have learned the content on the card well enough
-   The card could contain wrong content
-   User may want to reduce the amount of cards in that deck
