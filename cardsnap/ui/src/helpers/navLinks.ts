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
    label: 'Create deck',
    url: '/create-deck',
  },
  {
    label: 'Import',
    url: '/import',
  },
  {
    label: 'About',
    url: '/about',
  },
];
