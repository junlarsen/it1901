import { FC } from 'react';
import { Link } from 'react-router-dom';
import { NavLinkType } from '../helpers/navbar';

interface NavLinkProps {
  navLink: NavLinkType;
}

export const NavLink: FC<NavLinkProps> = ({ navLink }: NavLinkProps) => (
  <li className="mt-4">
    <Link to={navLink.url} className="hover:font-medium">
      {navLink.label}
    </Link>
  </li>
);
