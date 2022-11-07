import { FC } from 'react';
import { Subtitle } from '../components/subtitle';
import { CreateDeckForm } from '../views/createDeckPage/createDeckForm';

export const CreateDeckPage: FC = () => (
  <>
    <Subtitle title="Create deck" />
    <CreateDeckForm />
  </>
);
