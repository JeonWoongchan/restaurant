import React from 'react';
import axios from 'axios';
import {useEffect, useState} from "react";
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { setSignInEmail, setSignInPw } from '../store/loginStore';

// 로그인 로직
export default function SignInLogic() {
    const navigate = useNavigate()
    const signInEmail = useSelector(state=>state.loginReducer.signInEmail)
    const signInPw = useSelector(state=>state.loginReducer.signInPw)

    const signInHandler = ()=>{
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if(emailPattern.test(signInEmail) && signInEmail != null){
            axios
                .post("http://localhost:8080/login/sign-in", {
                email: signInEmail,
                password: signInPw
            })
                .then((res) => {
                    console.log(res.data)
                    if(res.data == 1 ){ //로그인 성공
                        navigate('/')
                    }else if(res.data == -1){ // 이메일 없음
                        navigate('/login/sign-in')
                        alert('이메일 오류')
                    }else if(res.data == 0){ // 비번 틀림
                        navigate('/login/sign-in') 
                        alert('비밀번호 오류')
                    }
            })
            .catch((error)=>{
                console.log(error)
            })
        }else{
            alert('잘못된 이메일 형식입니다.')
        }
    }


    return {
        signInHandler
    }
}