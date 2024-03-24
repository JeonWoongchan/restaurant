import React from 'react';
import './MyPage.css'
import TabPage from '../TabPage/TabPage';
import MyInfo from './MyInfo'
import MyReserv from './MyReserv'
import { useParams } from 'react-router-dom';
import MyReservGuest from './MyReservGuest';

export default function MyPage() {
    const { subMenu } = useParams()

    if(subMenu === 'reservGuest'){
        return(
            <MyReservGuest/>
        )
    }else{
        return (
            <div id='my-page'>
                <TabPage tabMenu={['예약정보']} menu={'my-page'} tabLink={['reservation']} 
                        subMenu={subMenu === 'reservation' ? <MyReserv/> : null}/>
            </div>
        );
    }

    
}

