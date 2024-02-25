import React, { useEffect, useState } from 'react';
import './css/MainIntro.css'
import { IoIosArrowForward } from "react-icons/io";
import useScroll from '../Function/useScroll'

export default function MainIntro() {
    const {scrollPosition} = useScroll();
    const [textAni, setTextAni] = useState('base-position')
    const [imgAni, setImgAni] = useState('base-position')

    useEffect(()=>{
        if(scrollPosition > 750){
            setTextAni('text-slide-up') 
            setImgAni('img-slide-up')
        }
    },[scrollPosition]) 

    return (
        <div id='main-intro' className='section-container'>
            <div className='intro-container'>
                <div className={`intro-text ${textAni}`}>
                    <div className="intro-text-title">
                        <p className='sub'>Restaurant WooDy</p>
                        <h2 className='main'>WooDy Style</h2>
                    </div>
                    <div className="intro-text-detail">
                        <div className="text-detail-inner">
                            레스토랑 WooDy에서 선보이는 요리들은 <br/>
                            기본을 바탕으로 현대적인 요리 기법과 창의성을 결합하여 준비되며, <br/>
                            고객들에게 더 나은 순간, 더 나은 기억을 만들기 위해 최선을 다하고 있습니다. <br/>
                        </div>
                        <div className="text-detail-inner">
                            The dishes that the restaurant WooDy presents <br/>
                            Based on the tradition of fine dining, <br/>
                            it is prepared by combining modern cooking techniques and creativity, <br/>
                            We are doing our best to make better moments and memories for our customers. <br/>
                        </div>
                    </div>
                    <div className="navigate-btn">
                        <div className="text">ABOUT</div> 
                        <IoIosArrowForward className='icon'/>
                    </div>
                </div>
                <div className={`intro-image ${imgAni}`}>
                    <img src="/image/dish1.png"/>
                </div>
            </div>
        </div>
    );
}

