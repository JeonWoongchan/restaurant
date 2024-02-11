import React from 'react';
import TabPage from '../TabPage/TabPage';
import Notice from './Notice';
import Reservation from '../Reservation/Reservation';
import Map from './Map';

import { useParams } from 'react-router-dom';

export default function Contact() {
    const { subMenu } = useParams()

    return (
        <div id='contact'>
            <TabPage tabMenu={['공지사항', '오시는 길']} subMenu={subMenu === 'notice' ? <Notice/> : subMenu === 'reservation' ? <Reservation/> : subMenu === 'map' ? <Map/> : null}/>
        </div>
    );
}

