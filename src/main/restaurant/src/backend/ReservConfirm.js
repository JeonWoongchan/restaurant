import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setIsLogin, setSignInEmail, setSignInPw, setUserData } from '../store/loginStore';
import { setGuestReservConfirm, setReservUserData } from '../store/reservStore';

// 예약 확인 로직
export default function ReservConfrim() {
  const navigate = useNavigate()
  const dispatch = useDispatch()

  const confirmHandler = () => {
    axios.post("http://localhost:8080/my-page/reserve")
      .then((response) => {
        if (response.data.status === 1) {
          console.log(response.data);
          const reserveData = response.data;
          // 예약 정보 설정
          dispatch(setGuestReservConfirm({
            reserve_id: reserveData.reserve_id,
            date: reserveData.reg_date,
            detail_date: reserveData.reserve_date,
            detail_adults: reserveData.adults_count,
            detail_children: reserveData.children_count,
            detail_baby: reserveData.infants_count,
            name: reserveData.name,
            phone: reserveData.phone,
          }));
          // 예약 페이지로 이동
          navigate('/my-page/reservation');
        } else {
          console.log('예약 정보 불러오기 실패:', response.data.message);
        }
      })
      .catch((error) => {
        console.error('예약 정보 요청 실패:', error);
      });
  }

  return {
    confirmHandler
  }
}
