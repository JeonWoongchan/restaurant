import React from 'react';
import TabPage from '../TabPage/TabPage';
import Dinner from './Dinner'
import Lunch from './Lunch'

import { useParams } from 'react-router-dom';

export default function Menu() {
    const { subMenu } = useParams()

    return (
        <div id='menu'>
            <TabPage tabMenu={['Lunch', 'Dinner']} subMenu={subMenu === 'lunch' ? <Lunch/> : subMenu === 'dinner' ? <Dinner/> : null}/>
        </div>
    );
}

