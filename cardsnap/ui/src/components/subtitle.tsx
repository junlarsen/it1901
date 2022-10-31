import React, { FC } from 'react';

interface SubtitleProps {
  title: string;
}

export const Subtitle: FC<SubtitleProps> = ({ title }) => {
  return <h2 className="text-2xl font-medium mb-8">{title}</h2>;
};
