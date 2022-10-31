import { FC } from 'react';
import { Subtitle } from '../components/subtitle';
import { CreateDeckForm } from '../components/createDeck'

export const CreateDeckView: FC = () => (
  <>
    <Subtitle title="Create deck" />
    <CreateDeckForm/>
  </>
);
