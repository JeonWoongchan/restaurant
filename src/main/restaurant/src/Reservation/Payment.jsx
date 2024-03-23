import React, { useEffect, useReducer, useState } from 'react';
import './css/Payment.css'
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import PaymentLogic from '../backend/PaymentLogic';
import { setReservData, setReservUserData } from '../store/reservStore';
import IsLoginCheck from '../backend/IsLoginCheck';
import PaymentUserData from '../backend/PaymentUserData';

export default function Payment() {
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const [reservUser, setReservUser] = useState('')
    const [reservPhoneFirst, setReservPhoneFirst] = useState('010') // 입력받은 폰 앞자리
    const [reservPhoneMiddle, setReservPhoneMiddle] = useState('') // 폰 중간
    const [reservPhoneLast, setReservPhoneLast] = useState('') // 폰 마지막
    const [reservPhone, setReservPhone] = useState('')
    const [reservEmail, setReservEmail] = useState('')

    const isLogin = useSelector(state=>state.loginReducer.isLogin)
    const reservUserData = useSelector(state=>state.reservReducer.reservUserData)
    const reservData = useSelector(state=>state.reservReducer.reservData)

    const { paymentHandler } = PaymentLogic()

    useEffect(() => {
        // 페이지 로드 후 스크롤을 맨 위로 이동
        window.scrollTo(0, 0);
    }, []);

    
    useEffect(()=>{
        if(reservData != localStorage.getItem('reservData')){
            dispatch(setReservData(localStorage.getItem('reservData'))) // 예약정보 최신화
        }
        // if(reservUserData != localStorage.getItem('reservUserData')){
        //     dispatch(setReservUserData(localStorage.getItem('reservUserData'))) // 예약정보 최신화
        // }
    })

    useEffect(()=>{
        const personnelData =  JSON.parse(localStorage.getItem('personnel'))
        const calendarData =  JSON.parse(localStorage.getItem('calendar'))

        if(personnelData != null && calendarData != null){
            const newReservData = `${calendarData.date} ${calendarData.time} / 
                                    ${personnelData.adult != 0 ? `성인(${personnelData.adult})` : ''}
                                    ${personnelData.child != 0 ? `어린이(${personnelData.child})` : ''}
                                    ${personnelData.baby != 0 ? `유아(${personnelData.baby})` : ''}`

            localStorage.setItem('reservData',newReservData)
        }else{ // 예약 인원, 날짜 없이 결제페이지 진입 막음
            navigate('/reservation')
        }
    },[])

    useEffect(()=>{
        dispatch(setReservUserData({'name': reservUser, 'email': reservEmail, 'phone' : reservPhoneFirst + reservPhoneMiddle + reservPhoneLast, 'point': ''}))
    },[reservUser, reservPhoneFirst, reservPhoneMiddle, reservPhoneLast, reservEmail])

    useEffect(()=>{
        setReservPhone(reservPhoneFirst + reservPhoneMiddle + reservPhoneLast)
    },[reservPhoneFirst, reservPhoneMiddle, reservPhoneLast])

    return (
        <div id='payment'>
            <div className="payment-container">
                <div className="reserv-image">
                    <img src="/image/login1.png" alt="" />
                    <div className="image-text">Reservation</div>
                </div>
                <div className="reserv-inner">
                    <div className="payment-title">
                        <h1>예약 정보 입력</h1>
                    </div>
                    <div className="payment-main">
                        <div className="payment-product">
                            <div className="detail">
                                <h5 className='sub-title'>예약 내용</h5>
                                <p>{reservData}</p>
                            </div>
                            <div className="detail">
                                <h5 className='sub-title'>예약자 정보</h5>
                                <ul>
                                    <li className='text'>
                                        <p>이름</p>
                                        <input type="text" defaultValue={isLogin && reservUserData.name ? reservUserData.name : null} onChange={(e)=>{setReservUser(e.target.value)}}/>
                                    </li>
                                    <li className='phone'>
                                        <p>휴대폰 번호</p>
                                        <div className="input-list">
                                            <select name="" id="" defaultValue={isLogin && reservUserData.phone ? reservUserData.phone.substring(0, 3): null} onChange={(e)=>{setReservPhoneFirst(e.target.value)}}>
                                                <option value="010">010</option>
                                                <option value="011">011</option>
                                                <option value="016">016</option>
                                                <option value="017">017</option>
                                                <option value="018">018</option>
                                                <option value="019">019</option>
                                            </select>
                                            <div className="num">
                                                <input type="text" defaultValue={isLogin && reservUserData.phone ? reservUserData.phone.substring(3, 7): null} onChange={(e)=>{setReservPhoneMiddle(e.target.value)}}/>
                                            </div>
                                            <div className="num">
                                                <input type="text" defaultValue={isLogin && reservUserData.phone ? reservUserData.phone.substring(7): null} onChange={(e)=>{setReservPhoneLast(e.target.value)}}/>
                                            </div>
                                        </div>
                                    </li>
                                    <li className='text'>
                                        <p>이메일</p>
                                        <input type="text" placeholder='WooDy@restaurant.com' defaultValue={isLogin && reservUserData ? reservUserData.email: null} onChange={(e)=>{setReservEmail(e.target.value)}}/>
                                    </li>
                                    {
                                        isLogin ?
                                        <li className='check'>
                                            <p>포인트 사용(잔여 포인트: {isLogin && reservUserData.point ? reservUserData.point: null}점)</p>
                                            <div className="input-list">
                                                <input className='point' type="text"/>
                                            </div>
                                        </li> : null
                                    }
                                </ul>
                            </div>
                            <button className='payment-submit' onClick={()=>{ localStorage.setItem('reservUserData', JSON.stringify(reservUserData)); paymentHandler();}}>예약하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

