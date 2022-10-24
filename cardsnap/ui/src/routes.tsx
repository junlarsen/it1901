import { createRoutesFromElements, createBrowserRouter, Route } from 'react-router-dom';
import { PageLayout } from './layouts/page';
import { DeckViewPage } from './pages/deck-view';
import { HomeView } from './pages/home';

export const router = createBrowserRouter(
  createRoutesFromElements(
    <>
      <Route path="/" element={<PageLayout />}>
        <Route path="home" element={<HomeView />} />
        <Route path="decks/:id" element={<DeckViewPage />} />
      </Route>
    </>,
  ),
);
