import { FC } from 'react';
import { Image } from './image';
import tdog from '../../assets/tdog.png';
import magg from '../../assets/magg.png';

/**
 * Renders content for the about page.
 */
export const AboutPageContent: FC = () => (
  <div className="flex flex-col lg:flex-row">
    <div className="flex flex-col justify-center gap-4">
      <Image source={tdog} alt="Happy engineer looking at computer" />
      <Image source={magg} alt="Tired engineer looking at computer" />
    </div>
    <div className="max-w-xl mt-2 lg:-mt-2 lg:ml-4">
      <p>
        Welcome to 🦄<b>CardSnap</b>🦄 - a digital flashcard application. You can use CardSnap to practice terms.
      </p>
      <p className="mt-4">
        CardSnap has been developed by a group of students from NTNU throughout the course IT1901. The group consists of
        Isak, Magnus, Mats, and Tale.
      </p>
      <p className="mt-4">We hope that you enjoy this product as much as we do!</p>
    </div>
  </div>
);
