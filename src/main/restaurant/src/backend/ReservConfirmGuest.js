import React from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';

// 예약 확인 로직
export default function ReservConfirmGuest() {
    const guestPhone = useSelector(state=>state.reservReducer.guestPhone)

    const confirmGuestHandler = () => {
        axios 
            .post("http://localhost:8080/my-page/reservGuest", {
                phone: guestPhone
            })
            .then((res) => {
                console.log(res.data)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return {
        confirmGuestHandler
    }
}