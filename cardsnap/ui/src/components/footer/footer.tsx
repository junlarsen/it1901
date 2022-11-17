import { FC } from 'react';
import { Link } from 'react-router-dom';
import { navLinks } from '../../helpers/navLinks';

/**
 * Renders a footer with navlinks and content.
 */
export const Footer: FC = () => (
  <footer className="bg-white w-full p-8">
    <div className="max-w-5xl m-auto px-4 xl:px-0">
      <h2 className="font-medium text-xl">Links</h2>
      <ul className="flex gap-4 mt-2">
        {navLinks.map((navLink) => (
          <li key={navLink.label}>
            <Link to={navLink.url} className="text-gray-700 hover:text-blue-600">
              {navLink.label}
            </Link>
          </li>
        ))}
        <li>
          <a
            href="https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2217/gr2217"
            className="text-gray-70 hover:text-blue-600"
          >
            GitLab
          </a>
        </li>
      </ul>
      <p className="mt-8">2022 CardSnap</p>
      <p className="mt-2">Made with ❤️ by GR2217</p>
    </div>
  </footer>
);
