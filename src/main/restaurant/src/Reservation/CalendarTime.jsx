import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

export default function CalendarTime(props) {
    const reservTimeList = useSelector(state => state.reservReducer.reservTimeList)

    const timeStyle = () => {
        return {
            display: props.nowClickDate === null ? 'none' : ''
        }
    }

    const selectTime = (a) => {
        if (props.selectedTime === a) {
            props.setSelectedTime(null)
        } else {
            props.setSelectedTime(a)
        }
    }

    return (
        <div className="calendar-time" style={timeStyle()}>
            <h5>{reservTimeList.length === 0 ? "예약 가능한 시간이 없습니다" : "예약시간"}</h5>
            <ul className="time-list">
                {
                    reservTimeList.map((a, i) => {
                        return (
                            <li className={`time ${props.selectedTime === a ? 'select' : null}`} onClick={() => { selectTime(a) }} key={i}>{a}</li>
                        )
                    })}
            </ul>
        </div>
    );
}

