import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { setSignInEmail, setSignInPw } from '../store/loginStore';
import SignInLogic from '../backend/SignInLogic';

export default function SignIn() {
    const dispatch = useDispatch()
    const {signInHandler} = SignInLogic()    

    return (
        <>
            <h1 className="form-title">로그인</h1>
            <label>
                <span>Email</span>
                <input type="text" onChange={(e)=>{dispatch(setSignInEmail(e.target.value))}}/>
            </label>
            <label>
                <span>Password</span>
                <input type="password" onChange={(e)=>{dispatch(setSignInPw(e.target.value))}}/>
            </label>
            <label>
                <p>비밀번호 찾기</p>
            </label>
            <button className='form-btn' type="button" onClick={()=>{signInHandler()}}>SIGN IN</button>
        </>
    );
}

