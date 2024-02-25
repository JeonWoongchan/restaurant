import React, { useEffect, useState } from 'react';
import './css/Payment.css'
import { useNavigate } from 'react-router-dom';

export default function Payment() {
    const [reservData, setReservData] = useState('')
    const navigate = useNavigate()

    useEffect(()=>{
        const personnelData =  JSON.parse(localStorage.getItem('personnel'))
        const calendarData =  JSON.parse(localStorage.getItem('calendar'))

        if(personnelData != null && calendarData != null){
            const newReservData = `${calendarData.date} ${calendarData.time} / 
                                    ${personnelData.adult != 0 ? `성인(${personnelData.adult})` : ''}
                                    ${personnelData.child != 0 ? `어린이(${personnelData.child})` : ''}
                                    ${personnelData.baby != 0 ? `유아(${personnelData.baby})` : ''}`

            setReservData(newReservData)
        }else{ // 예약 인원, 날짜 없이 결제페이지 진입 막음
            navigate('/reservation')
        }
        
    },[])

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
                                        <input type="text" />
                                    </li>
                                    <li className='phone'>
                                        <p>휴대폰 번호</p>
                                        <div className="input-list">
                                            <select name="" id="">
                                                <option value="">010</option>
                                                <option value="">011</option>
                                                <option value="">016</option>
                                                <option value="">017</option>
                                                <option value="">018</option>
                                                <option value="">019</option>
                                            </select>
                                            <div className="num">
                                                <input type="text" />
                                            </div>
                                            <div className="num">
                                                <input type="text" />
                                            </div>
                                        </div>
                                    </li>
                                    <li className='text'>
                                        <p>이메일</p>
                                        <input type="text" placeholder='WooDy@restaurant.com'/>
                                    </li>
                                    <li className='check'>
                                        <p>포인트 사용여부(잔여 포인트: 100점)</p>
                                        <div className="input-list">
                                            <input className='point' type="text" />
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <button className='payment-submit'>예약하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

