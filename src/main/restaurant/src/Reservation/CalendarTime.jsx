import React, { useEffect, useState } from 'react';
import useSelectBanner from '../Function/useSelectBanner';

export default function CalendarTime(props) {
    const [timeList, setTimeList] = useState(['11:30', '12:00', '12:30', '13:00', '13:30', '14:00', '14:30', '15:00', '17:30', '18:00', '18:30', '19:00', '19:30', '20:00', '20:30', '21:00'])

    const timeStyle = ()=>{
        return{
            display : props.nowClickDate === null ? 'none' : ''
        }
    }

    const selectTime = (a)=>{
        if(props.selectedTime === a){
            props.setSelectedTime(null)
        }else{
            props.setSelectedTime(a)
        }
    }

    return (
        <div className="calendar-time" style={timeStyle()}>
            <h5>예약시간</h5>
            <ul className="time-list">
                {timeList.map((a, i) => {
                    return (
                        <li className={`time ${props.selectedTime === a ? 'select' : null}`} onClick={()=>{selectTime(a)}} key={i}>{a}</li>
                    )
                })}
            </ul>
        </div>
    );
}

