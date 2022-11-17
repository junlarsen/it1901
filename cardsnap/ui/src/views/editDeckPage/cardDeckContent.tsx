import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CreateCardContainer } from './createCardContainer';
import { CardContainer } from './cardView/cardContainer';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card, CardDeck } from '../../helpers/mockData';
import { CardsNotFound } from '../../components/cardsNotFound/cardsNotFound';

interface CardDeckContentProps {
  deck: CardDeck;
}

/**
 * Displays the cards in a deck and inputs for creating a new card.
 * @param deck CardDeck to be displayed
 * @returns
 */
export const CardDeckContent: FC<CardDeckContentProps> = ({ deck }) => {
  /**
   * Gets the cards belonging to a given deck in a list with a call to the REST API
   * GET(Localhost:xxxx/{deckid}/cards/)
   */
  const { isLoading, data, isSuccess, isError, refetch } = useQuery(['cards', deck.id], () =>
    axios.get(`${DECKS_ENDPOINTS}${deck.id}/cards/`).then((res: AxiosResponse<Card[]>) => res.data),
  );

  /**
   * Renders content based on the success of the api call.
   * Displays a view for creating new cards.
   * If sucess, displays all cards.
   * If error, errormessage is shown.
   */
  return (
    <>
      <CreateCardContainer deck={deck} refetch={refetch} />
      {isLoading && <p>Loading...</p>}
      {isSuccess && data.map((card) => <CardContainer key={card.id} card={card} refetch={refetch} />)}
      {isSuccess && data.length === 0 && <p className="mt-8">This deck is empty, use the inputs above to add cards</p>}
      {isError && <CardsNotFound id={deck.id} />}
    </>
  );
};
