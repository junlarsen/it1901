import { url } from 'inspector';
import { FC } from 'react';

interface ImageProps {
  source: string;
}

export const Image: FC<ImageProps> = ({ source }) => (
  <>
    <img
      src={source}
      className="ml-auto mr-auto w-8/12 lg:w-full transition-shadow ease-in-out duration-300 hover:shadow-xl"
    />
  </>
);
