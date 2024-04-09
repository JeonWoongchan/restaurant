import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';
import { setReservUserData } from '../store/reservStore';

// 예약 완료 로직
export default function PaymentLogic() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const personnelData = JSON.parse(localStorage.getItem('personnel'))
    const calendarData = JSON.parse(localStorage.getItem('calendar'))

    const paymentHandler = () => {

        axios
            .post("http://localhost:8080/reservation/payment", {
                reserve_date: calendarData.date.split(' ')[0].replaceAll('.', '-') + ' ' + calendarData.time + ', ' + personnelData.adult + personnelData.child + personnelData.baby
            })
            .then((res) => {
                console.log(res.data)
                if (res.data) {

                } else if (!res.data) {

                }
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return {
        paymentHandler
    }
}