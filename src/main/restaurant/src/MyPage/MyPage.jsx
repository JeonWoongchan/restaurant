import React from 'react';
import './MyPage.css'
import TabPage from '../TabPage/TabPage';
import MyInfo from './MyInfo'
import MyReserv from './MyReserv'
import { useParams } from 'react-router-dom';

export default function MyPage() {
    const { subMenu } = useParams()

    return (
        <div id='my-page'>
            <TabPage tabMenu={['회원정보', '예약정보']} menu={'my-page'} tabLink={['profile','reservation']} 
                    subMenu={subMenu === 'profile' ? <MyInfo/> : subMenu === 'reservation' ? <MyReserv/> : null}/>
        </div>
    );
}

