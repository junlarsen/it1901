import React, { FC } from 'react';

interface SubtitleProps {
  title: string;
}

export const Subtitle: FC<SubtitleProps> = ({ title }) => {
  return <h2 className="text-2xl mb-2 font-medium">{title}</h2>;
};
