export interface NavLinkType {
  label: string;
  url: string;
}

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
