import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { setReservTimeList } from '../store/reservStore';
import { useDispatch } from 'react-redux';

// 캘린더에서 선택한 날짜에 예약가능한 시간대
export default function GetReserveTime() {
    const navigate = useNavigate()
    const dispatch = useDispatch()
    
    const getReservTimeHandler = (date, personnel) => {
        axios
            .post("http://localhost:8080/reservation/time", {

                    total_count: personnel.adult + personnel.child + personnel.baby,
                    reserve_date : date

            })
            .then((res) => {
                console.log(res.data)
                dispatch(setReservTimeList(Object.keys(res.data.possible)))
            })
            .catch((error) => {
                console.log(error)
            })

    }

    return {
        getReservTimeHandler
    }
}