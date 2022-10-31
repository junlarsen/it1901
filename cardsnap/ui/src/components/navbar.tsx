import { FC } from 'react';
import { NavLink } from './navLink';
import { NavLinkType } from '../helpers/navbar';

interface NavbarProps {
  navLinks: NavLinkType[];
}

export const Navbar: FC<NavbarProps> = ({ navLinks }) => (
  <nav className="bg-gray-200 fixed top-0 left-0 h-screen p-8">
    <h1 className="text-2xl">CardSnap</h1>
    <ul>
      {navLinks.map((navLink) => (
        <NavLink key={navLink.url} navLink={navLink} />
      ))}
    </ul>
  </nav>
);
