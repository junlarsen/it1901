import { createRoutesFromElements, createBrowserRouter, Route } from 'react-router-dom';
import { PageLayout } from './layouts/page';
import { AboutView } from './pages/about-view';
import { CreateDeckView } from './pages/create-deck-view';
import { DeckView } from './pages/deck-view';
import { EditDeckView } from './pages/edit-deck-view';
import { HomeView } from './pages/home';
import { ImportView } from './pages/import-view';

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<PageLayout />}>
      <Route path="/" element={<HomeView />} />
      <Route path="/create-deck" element={<CreateDeckView />} />
      <Route path="/import" element={<ImportView />} />
      <Route path="/about" element={<AboutView />} />
      <Route path="/play/:id" element={<DeckView />} />
      <Route path="/edit/:id" element={<EditDeckView />} />
    </Route>,
  ),
);
