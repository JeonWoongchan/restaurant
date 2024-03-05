import React from 'react';
import './css/Map.css'
import { IoIosPin } from "react-icons/io";

export default function Map() {
    const mapImage = 'map1'

    return (
        <div id='map'>
            <div className="map-container">
                <div className="map-main">
                    <div className="map-image" style={{ background: `url(/image/${mapImage}.png) no-repeat center/cover` }}>
                        <div className="text">
                            <h1>Restaurant WooDy</h1>
                            <IoIosPin className='icon'/>
                        </div>
                    </div>
                    <div className="map-text">
                        <h1>Restaurant WooDy</h1>
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

