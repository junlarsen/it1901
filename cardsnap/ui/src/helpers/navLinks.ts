/**
 * Defines the type of NavLinkType.
 */
export interface NavLinkType {
  label: string;
  url: string;
}

/**
 * The links to be displayed in the navbars.
 */
export const navLinks: NavLinkType[] = [
  {
    label: 'Home',
    url: '/',
  },
  {
    label: 'Create',
    url: '/create',
  },
  {
    label: 'About',
    url: '/about',
  },
];
