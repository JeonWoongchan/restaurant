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
        // console.log(calendarData.date.split(' ')[0].replaceAll('.', '-'))
        // console.log(calendarData.time)
        // console.log(personnelData.adult + personnelData.child + personnelData.baby)
        // console.log(calendarData.date.split(' ')[0].replaceAll('.', '-') + ' ' + calendarData.time)
        axios 
            .post("http://localhost:8080/reservation/payment", {
                reserve_date: calendarData.date.split(' ')[0].replaceAll('.', '-') + ' ' + calendarData.time,
                adults_count : personnelData.adult,
                children_count: personnelData.child,
                infants_count: personnelData.baby
            })
            .then((res) => {
                console.log(res.data)
                if (res.data === '회원 등록 완료') {
                    navigate('/my-page/reservation')
                } else if (res.data === '비회원 등록 완료') {
                    navigate('/')
                }else {
                    alert('예약 오류')
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