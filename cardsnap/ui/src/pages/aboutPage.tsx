import { FC } from 'react';
import { Subtitle } from '../components/subtitle';
import { Image } from '../views/aboutPage/image';

export const AboutPage: FC = () => (
  <>
    <Subtitle title="About🌸" />
    <div className="flex flex-col lg:flex-row md:ml-0">
      <div className="mr-4 flex flex-col justify-center">
        <Image source="https://media.istockphoto.com/photos/happy-family-mother-father-children-son-and-daughter-on-sunset-picture-id1159094800?b=1&k=20&m=1159094800&s=612x612&w=0&h=unZPRxqj1jYd5aHZls9uo7Wu69pdY999q27SQa2fkUs=" />
        <div className="mt-8">
          <Image source="https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg" />
        </div>
      </div>
      <p className="ml-auto mr-auto mt-8 max-w-xl lg:mt-0 lg:ml-8">
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Voluptate omnis nihil sed ad, consequuntur magnam
        maxime impedit eius esse incidunt nesciunt aut eos unde distinctio, commodi ipsa tempore nam? Velit! Lorem ipsum
        dolor sit amet consectetur adipisicing elit. Dolorum voluptates molestiae, suscipit porro id neque culpa
        doloremque nisi rerum ipsum beatae, assumenda alias consectetur fugit fugiat odio quis obcaecati sequi. Lorem
        ipsum dolor, sit amet consectetur adipisicing elit. Aliquid atque facere vitae provident veritatis vero saepe
        excepturi enim dicta a voluptatum, corrupti delectus veniam, labore laboriosam reiciendis magni doloribus
        reprehenderit? Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet, enim! Ullam, ex error. Veniam
        cum quibusdam voluptatem repellendus unde porro quos sed eaque natus tempora, laborum consectetur. Sunt,
        aspernatur nobis! Lorem ipsum, dolor sit amet consectetur adipisicing elit. Laudantium ducimus illo quam,
        assumenda repellat aut impedit aliquid delectus similique quisquam quos numquam quidem dolorum modi at
        temporibus aperiam soluta aspernatur.
      </p>
    </div>
  </>
);
