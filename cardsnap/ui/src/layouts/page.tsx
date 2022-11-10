import { FC } from 'react';
import { Outlet } from 'react-router-dom';
import { Footer } from '../components/footer';
import { Navbar } from '../components/navbar';
import { navLinks } from '../helpers/navLinks';

export const PageLayout: FC = () => (
  <main className="flex flex-col h-screen justify-between">
    <Navbar navLinks={navLinks} />
    <section className="w-full max-w-5xl m-auto mt-8 mb-auto">
      <div className="mx-4 mb-8 xl:mx-0">
        <Outlet />
      </div>
    </section>
    <Footer />
  </main>
);
