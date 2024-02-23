import React, { useEffect, useState } from 'react';
import './css/Calendar.css'
import { IoIosArrowDown, IoIosArrowUp } from "react-icons/io";
import { IoIosArrowDropleft, IoIosArrowDropright } from "react-icons/io";
import { IoMdClose } from "react-icons/io";
import useSelectBanner from '../Function/useSelectBanner'
import useCalendar from '../Function/useCalendar';
import CalendarTime from './CalendarTime';

export default function Calendar() {
    const { selectedDate, selectedYear, selectedMonth, calendarDate, nowClickDate, changeMonth, dayStyle, clickDate, 
            dropDownOn, setDropDownOn, dropDownYear, dropDownMonth, setDropDownYear, setDropDownMonth, chaneSelectMonth } = useCalendar()
            
    const [calendarHeight, setCalendarHeight] = useState(500)
    const { selectorOn, setSelectorOn, innerHeight } = useSelectBanner(calendarHeight)
    const [selectedTime, setSelectedTime] = useState(null) // 선택된 예약 시간

    const YEAR_LIST = [2023, 2024, 2025, 2026, 2027]
    const MONTH_LIST = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

    useEffect(() => {
        if (nowClickDate.length > 0) {
            setCalendarHeight(820)
        } else if (nowClickDate.length === 0) {
            setCalendarHeight(500)
        }
    }, [nowClickDate])

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
                {/*캘린더 월 선택 부분*/}
                <div className="calendar-month">
                    <IoIosArrowDropleft className='icon' onClick={() => { changeMonth(-1) }} />
                    <span className='text' onClick={()=>{setDropDownOn(!dropDownOn)}}>
                        {selectedYear}.{selectedMonth.toString().length === 1 ? selectedMonth.toString().padStart(2, '0') : selectedMonth}
                    </span>
                    <IoIosArrowDropright className='icon' onClick={() => { changeMonth(1) }} />
                </div>
                <div className="month-selector" style={{display : dropDownOn ? '' : 'none' }}>
                    <div className="top">
                        <h5>직접 입력</h5>
                        <IoMdClose className='icon' onClick={()=>{setDropDownOn(false)}}/>
                    </div>
                    <div className="select-list">
                        <div className="select"> {/*연도 select*/}
                            {
                                YEAR_LIST.map((a, i) => {
                                    return (
                                        <option key={i} 
                                        onClick={()=>{setDropDownYear(a)}} 
                                        style={{background : a === dropDownYear ? 'rgb(194,122,52)' : ''}}>{a}년</option>
                                    )
                                })
                            }
                        </div>
                        <div className="select"> {/*월 select*/}
                            {
                                MONTH_LIST.map((a, i) => {
                                    return (
                                        <option key={i} 
                                        onClick={()=>{setDropDownMonth(a); chaneSelectMonth()}}
                                        style={{background : a === dropDownMonth ? 'rgb(194,122,52)' : ''}}>{a}월</option>
                                    )
                                })
                            }
                        </div>
                    </div>
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
                            <li className={`day ${nowClickDate == a && a[0] != null ? 'select' : ''}`} style={dayStyle(a)} key={i} onClick={() => { clickDate(a) }}>
                                {a[0]}
                            </li>
                        )
                    })}
                </ul>
                <CalendarTime nowClickDate={nowClickDate} selectedTime={selectedTime} setSelectedTime={setSelectedTime} />
            </div>
        </div>
    );
}

