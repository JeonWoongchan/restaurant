import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import ReservConfrim from '../backend/ReservConfirm';

export default function MyReserv() {
    const isLogin = useSelector(state => state.loginReducer.isLogin)
    const guestReservConfirm = useSelector(state => state.reservReducer.guestReservConfirm)
    const [reservDetail, setReservDetail] = useState("")

    const { confrimHandler } = ReservConfrim()

    useEffect(() => {
        if (!isLogin) {
            confrimHandler()
        }
    }, [])

    useEffect(() => {
        if (guestReservConfirm.detail_adults > 0) {
            setReservDetail(prev => prev + `성인(${guestReservConfirm.detail_adults})`)
        }

        if (guestReservConfirm.detail_children > 0) {
            setReservDetail(prev => prev + `어린이(${guestReservConfirm.detail_children})`)
        }

        if (guestReservConfirm.detail_baby > 0) {
            setReservDetail(prev => prev + `유아(${guestReservConfirm.detail_baby})`)
        }
    }, [guestReservConfirm])

    if (guestReservConfirm === '') {
        return (
            <div className="">로딩중</div>
        )
    } else {
        return (
            <div id='my-reserv'>
                <div className="reserv-inner">
                    <div className="reserv-intro">
                        <h1 className="reserv-title">예약 확인</h1>
                        <div className="reserv-detail">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem cumque voluptates doloribus temporibus, minus repellat animi dolor, quos debitis molestiae explicabo, nemo incidunt laborum quas? Facilis doloribus expedita tenetur. Molestias!</div>
                    </div>
                    <div className="payment-main">
                        <div className="payment-product">
                            <div className="detail">
                                <h5 className='sub-title'>예약 일시</h5>
                                <p>{guestReservConfirm.date}</p>
                            </div>
                            <div className="detail">
                                <h5 className='sub-title'>예약 내용</h5>
                                <p>
                                    {guestReservConfirm.detail_date + ' ' + reservDetail}
                                </p>
                            </div>
                            <div className="detail">
                                <h5 className='sub-title'>예약자 정보</h5>
                                <p>이름 : {guestReservConfirm.name}</p>
                                <p>전화번호 : {guestReservConfirm.phone.substring(0, 3) + '-' + guestReservConfirm.phone.substring(3, 7) + '-' + guestReservConfirm.phone.substring(7)}</p>
                            </div>
                            <button className='payment-submit'>예약 취소</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

