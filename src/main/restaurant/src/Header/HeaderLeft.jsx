import React, { useEffect } from 'react';
import './css/HeaderLeft.css'
import { GrClose } from "react-icons/gr";
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import IsLoginCheck from '../backend/IsLoginCheck';

export default function HeaderLeft(props) {
    const navigate = useNavigate()
    const { loginCheckHandler } = IsLoginCheck()

    const isLogin = useSelector(state=>state.loginReducer.isLogin)
    const userData = useSelector(state=>state.loginReducer.userData)

    useEffect(()=>{
        loginCheckHandler() // 로그인 판별 함수
    },[])
    
    const navigateHandler = (link)=>{
        navigate(link);
        props.setHeaderLeftOn(false)
    }

    

    return (
        <div id='header-left'>
            <div className="header-left-container">
                <div className="header-left-inner">
                    <GrClose className="header-left-close" onClick={()=>{props.setHeaderLeftOn(false)}}/>
                    <div className="inner-top">
                        {/* <div className="profile">
                                <div className="profile-icon"></div>
                                <div className="user-name"></div>
                                <div className="logout"></div>
                            </div> */}
                        {
                            isLogin ? <div className="sign-in">{userData.name}</div>
                            : <>
                                <div className="sign-in" onClick={()=>{navigateHandler('/login/sign-in')}}>로그인</div>
                                <div className="sign-up" onClick={()=>{navigateHandler('/login/sign-up')}}>회원가입</div>
                            </>
                        }
                        
                    </div>
                    <div className="line"></div>
                    <div className="inner-middle">
                        <div className="user-menu">
                            <h5>Member</h5>
                            <p  onClick={()=>{navigateHandler('/reservation')}}>예약</p>
                            <p>예약확인</p>
                            <p>고객문의</p>
                        </div>
                        <div className="company">
                            <h5>Restaurant</h5>
                            <p>회사소개</p>
                            <p>가맹점모집</p>
                            <p>인재채용</p>
                        </div>
                        <div className="information">
                            <h5>운영시간</h5>
                            <div className="box">
                                <h6>Open</h6>
                                <p>AM11:30</p>
                            </div>
                            <div className="box">
                                <h6>Close</h6>
                                <p>PM9:30</p>
                            </div>
                            <div className="box">
                                <h6>Break Time</h6>
                                <p>PM3:30~PM5:30</p>
                            </div>
                            <div className="box">
                                <p>매주 월요일 휴무</p>
                            </div>
                        </div>
                    </div>
                    <div className="inner-bottom">
                        <p>본 사이트는 포트폴리오 용도로 만들어진 사이트입니다.</p>
                    </div>
                </div>
            </div>
        </div>
    );
}

