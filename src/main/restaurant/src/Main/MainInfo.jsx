import React, { useEffect, useState } from 'react';
import { IoIosArrowForward } from "react-icons/io";
import './css/MainInfo.css'
import useScroll from '../Function/useScroll'
import ScrollPosition from '../Function/useScroll';
import { useNavigate } from 'react-router-dom';

export default function MainInfo() {
    const navigate = useNavigate()
    const {scrollPosition} = useScroll()
    const [imgAni, setImgAni] = useState('img-base')

    useEffect(()=>{
        if(scrollPosition > 2250){
            setImgAni('img-transform')
        }
    },[scrollPosition])

    return (
        <div id='main-info' className='section-container'>
            <div className="main-info-container">
                <div className="main-info-img">
                    <img src="/image/building.png" alt="" className={`${imgAni}`}/>
                </div>
                <div className="main-info-text">
                    <div className="main-info-text-title">
                        <h1>Restaurant WooDy</h1>
                        <div className="navigate-btn" onClick={()=>{navigate('/reservation')}}>
                            <div className="text">Reservation</div> 
                            <IoIosArrowForward className='icon'/>
                        </div>
                    </div>
                    <div className="main-info-text-detail">
                        <div className="inner">
                            <h5>레스토랑 위치</h5>
                            <p>서울특별시 OO시 OO구 OOO동 0-00(XXX로 xx)</p>
                            <p>xx XXX-ro, OO-gu, Seoul, Korea</p>
                        </div>
                        <div className="inner">
                            <h5>운영시간</h5>
                            <div className="box">
                                <p>Open ~ Close</p>
                                <p>AM11:30 ~ PM9:30</p>
                            </div>
                            <div className="box">
                                <p>Break Time</p>
                                <p>PM3:30 ~ PM5:30</p>
                            </div>
                            <p>매주 월요일 휴무</p>
                        </div>
                        <div className="inner">
                            <h5>대표전화</h5>
                            <p>02-0000-0000</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

