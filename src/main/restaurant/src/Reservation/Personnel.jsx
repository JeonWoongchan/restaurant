import React, { useEffect, useState } from 'react';
import './css/Personnel.css'
import { IoIosArrowDown, IoIosArrowUp } from "react-icons/io";
import { CiCircleMinus, CiCirclePlus } from "react-icons/ci";

export default function Personnel() {
    const [personnelOn, setPersonnelOn] = useState(true)
    const [innerHeight, setInnerHeight] = useState('auto')
    const [innerDisplay, setInnerDisplay] = useState('')

    const [adultCount, setAdultCount] = useState(0)
    const [childCount, setChildCount] = useState(0)
    const [babyCount, setBabyCount] = useState(0)
    const [totalCount, setTotalCount] = useState(0)

    useEffect(() => {
        setTotalCount(adultCount + childCount + babyCount)
    }, [adultCount, childCount, babyCount])

    useEffect(()=>{
        if(personnelOn){
            setInnerHeight('50px')
        }else{
            setInnerHeight(0)
        }
    },[personnelOn])

    const addCount = (func) => {
        if (totalCount < 5) {
            func(prev => prev + 1)
        } else {
            alert('예약 가능 인원수가 초과되었습니다. \n최대 예약 가능 인원수는 6명입니다.')
        }
    }

    const personnelInnerStyle = ()=>{
        return{
            // display : innerDisplay,
            height : innerHeight
        }
    }

    return (
        <div id="personnel">
            <div className='personnel' onClick={()=>{setPersonnelOn(!personnelOn)}}>
                <p>인원 선택</p>
                <div className="right">
                    <span style={{display : adultCount > 0 ? 'block' : 'none'}}>성인 ({adultCount})</span>
                    <span style={{display : childCount > 0 ? 'block' : 'none'}}>어린이 ({childCount})</span>
                    <span style={{display : babyCount > 0 ? 'block' : 'none'}}>유아 ({babyCount})</span>
                    <IoIosArrowUp className='arrow' />
                </div>
            </div>
            <ul className='personnel-inner' style={personnelInnerStyle()}>
                <li>
                    <div className="text">
                        <p>성인</p>
                        <span>만 13세 이상</span>
                    </div>
                    <div className="count">
                        <CiCircleMinus className='icon' onClick={() => { if (adultCount != 0) setAdultCount(prev => prev - 1) }} />
                        <span>{adultCount}</span>
                        <CiCirclePlus className='icon' onClick={() => {addCount(setAdultCount)}} />
                    </div>
                </li>
                <li>
                    <div className="text">
                        <p>어린이</p>
                        <span>37개월 ~ 만 13세 미만</span>
                    </div>
                    <div className="count">
                        <CiCircleMinus className='icon' onClick={() => { if (childCount != 0) setChildCount(prev => prev - 1) }} />
                        <p>{childCount}</p>
                        <CiCirclePlus className='icon' onClick={() => {addCount(setChildCount)}} />
                    </div>
                </li>
                <li>
                    <div className="text">
                        <p>유아</p>
                        <span>37개월 미만</span>
                    </div>
                    <div className="count">
                        <CiCircleMinus className='icon' onClick={() => { if (babyCount != 0) setBabyCount(prev => prev - 1) }} />
                        <p>{babyCount}</p>
                        <CiCirclePlus className='icon' onClick={() => {addCount(setBabyCount)}} />
                    </div>
                </li>
            </ul>
        </div>
    );
}

