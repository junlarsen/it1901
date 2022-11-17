import { FC } from 'react';

interface ImageProps {
  alt: string;
  source: string;
}

/**
 * Image view for displaying images.
 * @param alt string alt-text for the image
 * @param source string source for getting the image
 */
export const Image: FC<ImageProps> = ({ alt, source }) => (
  <img src={source} alt={alt} className="w-8/12 lg:w-full max-w-xs" />
);
