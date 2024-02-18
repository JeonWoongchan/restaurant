import React, { useEffect, useState } from 'react';
import './css/Calendar.css'
import { IoIosArrowDown, IoIosArrowUp } from "react-icons/io";
import { IoIosArrowDropleft, IoIosArrowDropright } from "react-icons/io";
import useSelectBanner from '../Function/useSelectBanner'
import useCalendar from '../Function/useCalendar';
import CalendarTime from './CalendarTime';

export default function Calendar() {
    const { selectedDate, selectedYear, selectedMonth, calendarDate, nowClickDate, changeMonth, dayStyle, clickDate} = useCalendar() 
    const [calendarHeight, setCalendarHeight] = useState(500)
    const { selectorOn, setSelectorOn, innerHeight } = useSelectBanner(calendarHeight)
    const [selectedTime, setSelectedTime] = useState(null) // 선택된 예약 시간

    useEffect(()=>{
        if(nowClickDate !== null){
            setCalendarHeight(820)
        }else if(nowClickDate === null){
            setCalendarHeight(500)
        }
    },[nowClickDate])

    return (
        <div id='calendar'>
            <div className='calendar selector' onClick={() => { setSelectorOn(!selectorOn) }}>
                <p>예약 일시</p>
                <div className="right">
                    <span style={{ display: selectedDate ? 'block' : 'none' }}>{selectedDate} {selectedTime}</span>
                    {selectorOn ? <IoIosArrowUp className='arrow' /> : <IoIosArrowDown className='arrow' />}
                </div>
            </div>
            <div className="calendar-inner" style={{ height: innerHeight }}>
                <div className="calendar-month">
                    <IoIosArrowDropleft className='icon' onClick={() => { changeMonth(-1) }} />
                    <span className='text'>
                        {selectedYear}.{selectedMonth.toString().length === 1 ? selectedMonth.toString().padStart(2, '0') : selectedMonth}
                    </span>
                    <IoIosArrowDropright className='icon' onClick={() => { changeMonth(1) }} />
                </div>
                <ul className="calendar-week">
                    <li className='text-red-500'>Sun</li>
                    <li>Mon</li>
                    <li>Tue</li>
                    <li>Wed</li>
                    <li>Thu</li>
                    <li>Fri</li>
                    <li className='text-blue-700'>Sat</li>
                </ul>
                <ul className="calendar-day">
                    {calendarDate.map((a, i) => {
                        // calendarDate[i] === a의 형식 [날짜, 요일] -> 숫자로 표시됨 0 ~ 6 === 일 ~ 토
                        return (
                            <li className={`day ${nowClickDate == a && a[0] != null ? 'select' : ''}`} style={dayStyle(a)} key={i} onClick={()=>{clickDate(a)}}>
                                {a[0]}
                            </li>
                        )
                    })}
                </ul>
                <CalendarTime nowClickDate={nowClickDate} selectedTime={selectedTime} setSelectedTime={setSelectedTime}/>
            </div>
        </div>
    );
}

