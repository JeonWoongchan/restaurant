import React from 'react';
import TabPage from '../TabPage/TabPage';
import Reservation from '../Reservation/Reservation';
import Map from './Map';

import { useParams } from 'react-router-dom';

export default function Contact() {
    const { subMenu } = useParams()

    return (
        <div id='contact'>
            <TabPage tabMenu={['오시는 길']} tabLink={['map']} subMenu={<Map/>}/>
        </div>
    );
}

