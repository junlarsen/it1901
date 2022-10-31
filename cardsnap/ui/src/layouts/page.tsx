import { FC } from 'react';
import { Outlet } from 'react-router-dom';
import { Navbar } from '../components/navbar';
import { navLinks } from '../helpers/navbar';

export const PageLayout: FC = () => (
  <main>
    <Navbar navLinks={navLinks} />
    <section className="ml-48 mt-8">
      <Outlet />
    </section>
  </main>
);
