import { createRoutesFromElements, createBrowserRouter, Route } from 'react-router-dom';
import { PageLayout } from './layouts/page';
import { AboutPage } from './pages/aboutPage';
import { CreateDeckPage } from './pages/createDeckPage';
import { EditDeckPage } from './pages/editDeckPage';
import { HomePage } from './pages/homePage';
import { DeckPage } from './pages/deckPage';

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<PageLayout />}>
      <Route path="/" element={<HomePage />} />
      <Route path="/create" element={<CreateDeckPage />} />
      <Route path="/about" element={<AboutPage />} />
      <Route path="/deck/:id" element={<DeckPage />} />
      <Route path="/deck/:id/edit" element={<EditDeckPage />} />
    </Route>,
  ),
);
