import { FC } from 'react';

interface IndexIndicatorProps {
  currentCount: number;
  totalCount: number;
}

export const IndexIndicator: FC<IndexIndicatorProps> = ({ currentCount, totalCount }) => (
  <figure>
    <p className="font-thin">
      {currentCount}/{totalCount}
    </p>
    <div className="bg-sky-500 w-full h-2 rounded mb-4">
      <div
        className="bg-sky-900 h-2 rounded transition-all duration-300"
        style={{ width: `${(currentCount / totalCount) * 100}%` }}
      />
    </div>
  </figure>
);
