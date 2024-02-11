import React, { useEffect, useState } from 'react';
import './css/Login.css'
import LoginForm from './LoginForm';
import { useParams } from 'react-router-dom';

export default function Login() {
    const [nowForm, setNowForm] = useState(true); // true일때 로그인 false일때 회원가입
    const { value } = useParams();

    useEffect(()=>{
        if(value === 'sign-up'){
            setNowForm(false)
        }else if(value === 'sign-in'){
            setNowForm(true)
        }
    },[value])

    const chaneForm = (i) => {
        if (i === 0) {
            return {
                left: nowForm ? 0 : '30%'
            }
        } else if (i === 1) {
            return {
                right: nowForm ? 0 : '70%',
                backgroundPosition: nowForm ? 'right' : 'left'
            }
        }
    }

    return (
        <div id='login'>
            <div className="login-container">
                <div className="login-image">
                    <img src="/image/login1.png" alt="" />
                </div>
                <div className="login-container-main">
                    <h1 className="login-title">로그인 / 회원가입</h1>
                </div>
                <div className="login-form">
                    <LoginForm chaneForm={chaneForm} nowForm={nowForm}/>
                    <div className="side-container" style={chaneForm(1)}>
                        <div className="inner-text" style={{ opacity: nowForm ? 1 : 0 }}>
                            <h5>새로 오셨나요?</h5>
                            <p>가입하고 특별한 서비스를 경험하세요!</p>
                        </div> 
                        <div className="inner-text" style={{ opacity: nowForm ? 0 : 1 }}>
                            <h5>이미 회원이신가요?</h5>
                            <p>로그인하고 특별한 경험을 이어가세요!</p>
                        </div>
                        <button className='change-form-btn' onClick={() => { setNowForm(!nowForm) }}>SIGN UP</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

