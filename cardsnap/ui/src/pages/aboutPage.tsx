import { FC } from 'react';
import { Subtitle } from '../components/subtitle/subtitle';
import { AboutPageContent } from '../views/aboutPage/aboutPageContent';

/**
 * Renders the aboutpage with a subtitle and content.
 */
export const AboutPage: FC = () => (
  <>
    <Subtitle title="About🌸" />
    <AboutPageContent />
  </>
);
