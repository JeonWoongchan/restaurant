import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import ReservConfirmGuest from '../backend/ReservConfirmGuest';
import { useParams } from 'react-router-dom';
import { setGuestPhone } from '../store/reservStore';

export default function MyReservGuest() {
    const dispatch = useDispatch()
    const { confirmGuestHandler } = ReservConfirmGuest()

    // const [guestName, setGuestName] = useState('')

    const guestPhone = useSelector(state=>state.reservReducer.guestPhone)

    const [guestPhone1, setGuestPhone1] = useState('010')
    const [guestPhone2, setGuestPhone2] = useState('')
    const [guestPhone3, setGuestPhone3] = useState('')

    useEffect(() => {
        dispatch(setGuestPhone(guestPhone1 + guestPhone2 + guestPhone3))
    }, [guestPhone1, guestPhone2, guestPhone3])


    return (
        <div id='my-reserv'>
            <div className="tab-page-image">
                <img src="/image/login1.png" alt="" />
                <div className="image-text">Guest Reservation</div>
            </div>
            <div className="reserv-inner">
                <div className="reserv-intro">
                    <h1 className="reserv-title">예약 확인</h1>
                    <div className="reserv-detail">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem cumque voluptates doloribus temporibus, minus repellat animi dolor, quos debitis molestiae explicabo, nemo incidunt laborum quas? Facilis doloribus expedita tenetur. Molestias!</div>
                </div>
                <div className="payment-main">
                    <div className="payment-product">
                        <div className="detail">
                            <h5 className='sub-title'>예약자 정보 입력</h5>
                            <ul>
                                {/* <li className='text'>
                                    <p>이름</p>
                                    <input type="text" onChange={(e) => { setGuestName(e.target.value) }} />
                                </li> */}
                                <li className='phone guest-input'>
                                    <p>휴대폰 번호</p>
                                    <div className="input-list" >
                                        <select name="" id="" onChange={(e) => { setGuestPhone1(e.target.value) }}>
                                            <option value="010">010</option>
                                            <option value="011">011</option>
                                            <option value="016">016</option>
                                            <option value="017">017</option>
                                            <option value="018">018</option>
                                            <option value="019">019</option>
                                        </select>
                                        <div className="num">
                                            <input type="text" onChange={(e) => { setGuestPhone2(e.target.value) }} />
                                        </div>
                                        <div className="num">
                                            <input type="text" onChange={(e) => { setGuestPhone3(e.target.value) }} />
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <button className='payment-submit' onClick={() => { confirmGuestHandler() }}>예약 조회</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

