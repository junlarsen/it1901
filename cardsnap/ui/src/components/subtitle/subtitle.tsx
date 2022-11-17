import { FC } from 'react';

interface SubtitleProps {
  title: string;
}

/**
 * Renders a h2-element to be title on each page.
 * @param title string to be displayed
 */
export const Subtitle: FC<SubtitleProps> = ({ title }) => {
  return <h2 className="text-2xl font-medium mb-8">{title}</h2>;
};
