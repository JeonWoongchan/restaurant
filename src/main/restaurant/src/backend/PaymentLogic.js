import React from 'react';
import axios from 'axios';
import {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';
import { setReservUserData } from '../store/reservStore';

// 예약 완료 로직
export default function PaymentLogic() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const reservUserData = useSelector(state=>state.reservReducer.reservUserData)
    const reservData = useSelector(state=>state.reservReducer.reservData)

    useEffect(()=>{
        if(reservUserData.name){
            axios
                .post("http://localhost:8080/reservation/payment", {
                    reservUserData: reservUserData,
                    reservData: reservData
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
    },[reservUserData])

    return {
        
    }
}