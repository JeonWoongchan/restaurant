import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';

// 로그인 로직
export default function SignInLogic() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const signInEmail = useSelector(state => state.loginReducer.signInEmail)
    const signInPw = useSelector(state => state.loginReducer.signInPw)
    const userData = useSelector(state => state.loginReducer.userData)
    const isLogin = useSelector(state => state.loginReducer.isLogin)

    console.log(userData, isLogin)
    const signInHandler = () => {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (emailPattern.test(signInEmail) && signInEmail != null) {
            axios
                .post("http://localhost:8080/login/sign-in", {
                    email: signInEmail,
                    password: signInPw
                })
                .then((res) => {
                    console.log(res.data)
                    if (res.data.status == 1) { // 로그인 성공
                        // document.cookie = `accessToken=${res.data.accessToken}; Secure; HttpOnly; SameSite=Strict`; // https only 쿠키에 저장
                        document.cookie = `refreshToken=${res.data.refreshToken}; Secure; HttpOnly; SameSite=Strict`; // https only 쿠키에 저장

                        // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
                        axios.defaults.headers.common['Authorization'] = `Bearer ${res.data.accessToken}`;

                        navigate('/')
                    } else if (res.data.status == -1) { // 이메일 없음
                        navigate('/login/sign-in')
                        alert('이메일 오류')
                    } else if (res.data.status == 0) { // 비번 틀림
                        navigate('/login/sign-in')
                        alert('비밀번호 오류')
                    }
                })
                .catch((error) => {
                    console.log(error)
                })
        } else {
            alert('잘못된 이메일 형식입니다.')
        }
    }

    return {
        signInHandler
    }
}