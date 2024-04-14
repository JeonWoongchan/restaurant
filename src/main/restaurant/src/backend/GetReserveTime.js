import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';

// 캘린더에서 선택한 날짜에 예약가능한 시간대
export default function GetReserveTime() {
    const navigate = useNavigate()
    const getReservTimeHandler = (date) => {
        axios
            .post("http://localhost:8080/reservation/time", {
                date : date
            })
            .then((res) => {
                console.log(res.data)
                
            })
            .catch((error) => {
                console.log(error)
            })

    }

    return {
        getReservTimeHandler
    }
}