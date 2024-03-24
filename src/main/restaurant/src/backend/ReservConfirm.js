import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';
import { setReservUserData } from '../store/reservStore';

// 예약 확인 로직
export default function ReservConfrim() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const confrimHandler = () => {
        // console.log(calendarData.date.split(' ')[0].replaceAll('.', '-'))
        // console.log(calendarData.time)
        // console.log(personnelData.adult + personnelData.child + personnelData.baby)
        // console.log(calendarData.date.split(' ')[0].replaceAll('.', '-') + ' ' + calendarData.time)
        axios 
            .get("http://localhost:8080/my-page/reservation")
            .then((res) => {
                console.log(res.data)
                
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return {
        confrimHandler
    }
}