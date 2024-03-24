import React from 'react';
import axios from 'axios';

// 예약 확인 로직
export default function ReservConfirmGuest(name, phone) {

    const confirmGuestHandler = () => {
        axios 
            .post("http://localhost:8080/my-page/reservation", {
                name: name,
                phone: phone
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