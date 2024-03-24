import React from 'react';
import TabPage from '../TabPage/TabPage';
import Dinner from './Dinner'
import Lunch from './Lunch'
import Additional from './Additional'

import { useParams } from 'react-router-dom';

export default function Menu() {
    const { subMenu } = useParams()

    return (
        <div id='menu'>
            <TabPage tabMenu={['Lunch', 'Dinner', '추가 메뉴']} menu={'menu'} tabLink={['lunch','dinner', 'additional']} 
                    subMenu={subMenu === 'lunch' ? <Lunch/> : subMenu === 'dinner' ? <Dinner/> : subMenu === 'additional' ? <Additional/> : null}/>
        </div>
    );
}

