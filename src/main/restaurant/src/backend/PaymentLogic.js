import React from 'react';
import axios from 'axios';
import {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';

// 예약 완료 로직
export default function PaymentLogic() {
    const navigate = useNavigate()
    const dispatch = useDispatch()
    
    const reservUserData = useSelector(state=>state.reservReducer.reservUserData)
    const reservData = useSelector(state=>state.reservReducer.reservData)

    const PaymentHandler = ()=>{
        
    }

    return {
        PaymentHandler
    }
}