import { FC } from 'react';

interface ImageProps {
  alt: string;
  source: string;
}

export const Image: FC<ImageProps> = ({ alt, source }) => (
  <img src={source} alt={alt} className="w-8/12 lg:w-full max-w-xs" />
);
