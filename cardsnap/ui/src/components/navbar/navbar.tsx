import { FC } from 'react';
import { Link } from 'react-router-dom';
import { NavLink } from './navLink';
import { NavLinkType } from '../../helpers/navLinks';

interface NavbarProps {
  navLinks: NavLinkType[];
}

export const Navbar: FC<NavbarProps> = ({ navLinks }) => (
  <nav className="bg-white p-4 shadow-sm">
    <div className="max-w-5xl m-auto flex flex-col sm:flex-row">
      <Link to="/">
        <h1 className="text-2xl text-blue-600 font-medium">CardSnap💙</h1>
      </Link>
      <ul className="flex gap-4 mt-1 sm:ml-8">
        {navLinks.map((navLink) => (
          <NavLink key={navLink.url} navLink={navLink} />
        ))}
      </ul>
    </div>
  </nav>
);
