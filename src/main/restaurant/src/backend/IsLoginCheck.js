import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';

// 로그인 판별 로직
export default function IsLoginCheck() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    // 로그인 판별 함수
    const loginCheckHandler = () => {
        axios
            .post("http://localhost:8080/login/login-check", {
                // 헤더에 담겨있는 엑세스토큰을 전송
            })
            .then((res) => {
                console.log(res.data)
                if (res.data.status == 1) { // 로그인 중임
                    console.log('로그인 중임')
                    dispatch(setIsLogin(true))
                } else if (!res.data) { // 로그인 중 아님
                    console.log('로그인 중 아님')
                    dispatch(setIsLogin(false))
                }
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return {
        loginCheckHandler
    }
}