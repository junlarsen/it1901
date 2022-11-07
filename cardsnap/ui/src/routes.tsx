import { createRoutesFromElements, createBrowserRouter, Route } from 'react-router-dom';
import { PageLayout } from './layouts/page';
import { AboutPage } from './pages/aboutPage';
import { CreateDeckPage } from './pages/createDeckPage';
import { PlayPage } from './pages/playPage';
import { EditDeckPage } from './pages/editDeckPage';
import { HomePage } from './pages/homePage';
import { ImportPage } from './pages/importPage';

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<PageLayout />}>
      <Route path="/" element={<HomePage />} />
      <Route path="/create-deck" element={<CreateDeckPage />} />
      <Route path="/import" element={<ImportPage />} />
      <Route path="/about" element={<AboutPage />} />
      <Route path="/play/:id" element={<PlayPage />} />
      <Route path="/edit/:id" element={<EditDeckPage />} />
    </Route>,
  ),
);
