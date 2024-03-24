import React, { useEffect, useState } from 'react';
import './css/Reservation.css'
import Personnel from './Personnel';
import Calendar from './Calendar';
import CalendarForm from './CalendarForm';
import ReservModal from './ReservModal';
import { MdErrorOutline } from "react-icons/md";
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import IsLoginCheck from '../backend/IsLoginCheck';
import PaymentUserData from '../backend/PaymentUserData';

export default function Reservation() {
    const navigate = useNavigate()

    const { getUserData } = PaymentUserData()
    const isLogin = useSelector(state=>state.loginReducer.isLogin)

    // 예약 페이지 접속 시 로컬스토리지 초기화
    useEffect(()=>{
        if(isLogin){
            getUserData() // 유저 데이터 요청
        }

        localStorage.removeItem('personnel')
        localStorage.removeItem('calendar')
    },[])


    const moveToPayment = ()=>{
        const personnelData =  JSON.parse(localStorage.getItem('personnel'))
        const calendarData =  JSON.parse(localStorage.getItem('calendar'))

        if(personnelData != null && calendarData != null){
            navigate('/payment')
        }else if(personnelData == null){
            alert('예약 인원을 선택하세요')
        }else if(calendarData == null){
            alert('예약 날짜을 선택하세요')
        } 
    }

    return (
        <div id='reservation'>
            <div className="reserv-container">
                <div className="reserv-image">
                    <img src="/image/login1.png" alt="" />
                    <div className="image-text">Reservation</div>
                </div>
                <div className="reserv-inner">
                    <div className="reserv-intro">
                        <h1 className="reserv-title">레스토랑 예약</h1>
                        <div className="reserv-detail">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem cumque voluptates doloribus temporibus, minus repellat animi dolor, quos debitis molestiae explicabo, nemo incidunt laborum quas? Facilis doloribus expedita tenetur. Molestias!</div>
                    </div>
                    <div className="reserv-main">
                        <Personnel/>
                        <Calendar/>
                        <CalendarForm/>
                        <div className="caution-box">
                            <div className="top">
                                <MdErrorOutline className='text-red-500'/>
                                <h5 className='text-red-500'>유의사항</h5>
                            </div>
                            <div className="inner">
                                <p>테이블은 홀 좌석부터 순차적으로 배정되며, 지정 좌석 예약은 불가하오니 양해 부탁드립니다.</p>
                                <p>홈페이지 예약은 최대 6인 이하 테이블에 한하며 7인 이상 단체는 유선으로 문의 부탁드립니다.</p>
                                <p>레스토랑 예약 상황에 따라 일정이 조정되거나 예약이 진행되지 않을 수 있습니다.</p>
                                <p>예약 변경을 원하실 경우 최소 이틀 전까지 온라인으로 예약 취소 후 다시 이용해 주시기 바랍니다.</p>
                                <p>야외 가든 좌석은 채광이 있을 수 있는 점 이용에 참고 부탁드립니다.</p>
                                <p>매주 월요일은 휴무입니다.</p>
                                <p>해당 페이지는 포트폴리오 용도로 작성된 페이지 입니다.</p>
                                <h3>[환불 정책]</h3>
                                <p>이용일로부터 1일 전 23:59분까지 취소 시: 100% 환불</p>
                                <p>당일 취소 및 변경, No Show 시: 환불 불가</p>
                            </div>
                        </div>
                        <button className="reserv-submit" onClick={()=>{moveToPayment()}}>다음단계</button>
                    </div>
                </div>
                <ReservModal/>
            </div>
        </div>
    );
}

