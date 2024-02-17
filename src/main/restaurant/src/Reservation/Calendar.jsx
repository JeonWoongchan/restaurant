import React, { useEffect, useState } from 'react';
import './css/Calendar.css'
import { IoIosArrowDown, IoIosArrowUp } from "react-icons/io";
import { IoIosArrowDropleft, IoIosArrowDropright } from "react-icons/io";
import useSelectBanner from '../Function/useSelectBanner'

export default function Calendar() {
    const { selectorOn, setSelectorOn, innerHeight } = useSelectBanner(600)

    // 현재 날짜
    const [currentDate, setCurrentDate] = useState(new Date());
    const [currentYear, setCurrentYear] = useState(currentDate.getFullYear());
    const [currentMonth, setCurrentMonth] = useState(currentDate.getMonth() + 1);
    const [currentDay, setCurrentDay] = useState(currentDate.getDate());
    console.log(currentMonth)

    // 선택된 날짜 정보
    const [selectedDate, setSelectedDate] = useState('2024.02.29(목)'); // ex) 2024.02.29(목) 
    const [selectedYear, setSelectedYear] = useState(''); // ex) 2024
    const [selectedMonth, setSelectedMonth] = useState(''); // ex) 02

    // 캘린더에 표시될 날짜 정보
    const [startDate, setStartDate] = useState([]) // 해당 월 시작 날짜
    const [endDate, setEndDate] = useState([]) // 해당 월 마지막 날짜
    const [calendarDate, setCalendarDate] = useState([]) // 해당 월 전체 날짜 배열

    // 캘린더 요일
    const weekList = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']

    useEffect(()=>{
        setSelectedYear(Number(currentYear))
        setSelectedMonth(Number(currentMonth.toString().padStart(2, '0')))
    },[currentMonth])

    // 해당 월 시작 날짜, 마지막 날짜 설정
    useEffect(() => {
        const options = { weekday: 'short', month: 'short', day: '2-digit' };
        const newStartDate = new Date(selectedYear, selectedMonth - 1, 1);
        const newEndDate = new Date(selectedYear, selectedMonth, 0);

        const formattedStartDate = newStartDate.toLocaleDateString('en-US', options).replace(',', '').split(' ');
        const formattedEndDate = newEndDate.toLocaleDateString('en-US', options).replace(',', '').split(' ');

        setStartDate(formattedStartDate)
        setEndDate(formattedEndDate)

        // 해당 월의 전체 날짜 배열 생성
        const newCalendarDate = [];
        for (let i = 1; i <= newEndDate.getDate(); i++) {
            const currentDate = new Date(currentYear, currentMonth - 1, i);
            if (currentDate.getMonth() === currentMonth - 1) {
                const formattedDate = currentDate.getDate(); // 해당 월의 날짜만 가져옵니다.
                newCalendarDate.push(formattedDate);
            }
        }
        setCalendarDate(newCalendarDate);

    }, [selectedMonth])

    useEffect(() => {
        if (startDate.length == 0) {
            return
        }

        if(calendarDate[0] !== null){
            const index = weekList.indexOf(startDate[0])
            const arr = new Array(index).fill(null)
            setCalendarDate(prev => [...arr, ...prev])
        }

    }, [startDate])

    useEffect(() => {
        console.log(calendarDate)
    }, [calendarDate])

    useEffect(() => {
        console.log(startDate, endDate)
    }, [startDate, endDate])

    return (
        <div id='calendar'>
            <div className='calendar selector' onClick={() => { setSelectorOn(!selectorOn) }}>
                <p>예약 일시</p>
                <div className="right">
                    <span style={{ display: selectedDate ? 'block' : 'none' }}>{selectedDate}</span>
                    {selectorOn ? <IoIosArrowUp className='arrow' /> : <IoIosArrowDown className='arrow' />}
                </div>
            </div>
            <div className="calendar-inner" style={{ height: innerHeight }}>
                <div className="calendar-month">
                    <IoIosArrowDropleft className='icon' onClick={()=>{setSelectedMonth(prev=>prev-1)}}/>
                    <span className='text'>{selectedYear}.{selectedMonth}</span>
                    <IoIosArrowDropright className='icon' onClick={()=>{setSelectedMonth(prev=>prev+1)}}/>
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
                        const dayStyle = ()=>{
                            return{
                                opacity : currentMonth == selectedMonth && a < currentDay ? 0.5 : 1
                            }
                        }

                        return (
                            <li className='day' style={dayStyle()} key={i}>{a}</li>
                        )
                    })}
                </ul>
            </div>
        </div>
    );
}

