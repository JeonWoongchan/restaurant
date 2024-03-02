import React from 'react';
import axios from 'axios';
import {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';

// 로그인 판별 로직
export default function IsLoginCheck() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const loginCheckHandler = ()=>{
        
    }

    return {
        loginCheckHandler
    }
}