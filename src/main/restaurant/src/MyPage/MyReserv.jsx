import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import ReservConfirm from '../backend/ReservConfirm';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function MyReserv() {
    const isLogin = useSelector(state => state.loginReducer.isLogin);
    const guestReservConfirm = useSelector(state => state.reservReducer.guestReservConfirm);
    const [reservDetail, setReservDetail] = useState('');

    const { currentIndex, totalReservations, handlePrev, handleNext, confirmHandler } = ReservConfirm();
    const navigate = useNavigate();

    useEffect(() => {
        if (!isLogin) {
            navigate('/my-page/reservGuest');
        } else {
            confirmHandler();
        }
    }, []);

    useEffect(() => {
        let detail = '';
        if (guestReservConfirm.detail_adults > 0) {
            detail += `성인(${guestReservConfirm.detail_adults}) `;
        }
        if (guestReservConfirm.detail_children > 0) {
            detail += `어린이(${guestReservConfirm.detail_children}) `;
        }
        if (guestReservConfirm.detail_baby > 0) {
            detail += `유아(${guestReservConfirm.detail_baby})`;
        }
        setReservDetail(detail);
    }, [],[guestReservConfirm]);

    const deleteReserve = async () => {
        const requestData = {
            reserve_id: guestReservConfirm.reserve_id,
        };

        try {
            const response = await axios.post('http://localhost:8080/reservation/delete_reserve', requestData);
            if (response.data.status === 1) {
                console.log('예약이 성공적으로 삭제되었습니다.');
                confirmHandler(currentIndex);
            } else {
                console.error('예약 삭제 오류:', response.data.message);
            }
        } catch (error) {
            console.error('예약 삭제 중 에러:', error);
        }
    };

    return (
      <div id='my-reserv'>
          <div className='reserv-inner'>
              <div className='reserv-intro'>
                  <h1 className='reserv-title'>예약 확인</h1>
                  <div className='reserv-detail'>
                      예약 내용을 확인하세요.
                  </div>
              </div>
              {guestReservConfirm !== '' && (
                <div className='payment-main'>
                    <div className='payment-product'>
                        <div className='detail'>
                            <h5 className='sub-title'>예약 일시</h5>
                            <p>{guestReservConfirm.date}</p>
                        </div>
                        <div className='detail'>
                            <h5 className='sub-title'>예약 내용</h5>
                            <p>{guestReservConfirm.detail_date} {reservDetail}</p>
                        </div>
                        <div className='detail'>
                            <h5 className='sub-title'>예약자 정보</h5>
                            <p>이름: {guestReservConfirm.name}</p>
                            <p>전화번호: {guestReservConfirm.phone ? `${guestReservConfirm.phone.slice(0, 3)}-${guestReservConfirm.phone.slice(3, 7)}-${guestReservConfirm.phone.slice(7)}` : null}</p>
                        </div>
                        <button className='payment-submit' onClick={deleteReserve}>예약 취소</button>
                    </div>
                    {currentIndex > 0 && (
                      <button className='arrow-prev' onClick={handlePrev}>
                          이전
                      </button>
                    )}
                    {currentIndex < totalReservations - 1 && (
                      <button className='arrow-next' onClick={handleNext}>
                          다음
                      </button>
                    )}
                </div>
              )}
          </div>
      </div>
    );
}
