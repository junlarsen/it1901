import { FC } from 'react';
import { NavLink as Link } from 'react-router-dom';
import { NavLinkType } from '../../helpers/navLinks';

interface NavLinkProps {
  navLink: NavLinkType;
}

export const NavLink: FC<NavLinkProps> = ({ navLink }: NavLinkProps) => (
  <li>
    <Link
      className={({ isActive }) =>
        isActive
          ? 'font-medium transition-all border-blue-600 border-b-4'
          : 'font-medium transition-all border-blue-600 hover:border-b-4'
      }
      to={navLink.url}
      end
    >
      {navLink.label}
    </Link>
  </li>
);
