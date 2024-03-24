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
        axios .post("http://localhost:8080/my-page/reserve",{

            })
            .then((res) => {
                console.log(res.data)
                if(res.data.length == 0) {
                    alert('예약 정보가 없습니다')
                    navigate(-1)
                }

                if(res.data) {
                    dispatch(setGuestReservConfirm({
                        date : res.data[0].reg_date,
                        detail_date : res.data[0].reserve_date, 
                        detail_adults : res.data[0].adults_count ,
                        detail_children : res.data[0].children_count ,
                        detail_baby : res.data[0].infants_count,
                        name : res.data[0].name,
                        phone : res.data[0].phone
                    }))
                    navigate('/my-page/reservation')
                } 
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return {
        confirmHandler
    }
}