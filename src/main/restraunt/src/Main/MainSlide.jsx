import React, { useEffect, useState } from 'react';
import './css/MainSlide.css'
import { SlArrowLeft, SlArrowRight } from "react-icons/sl";
import { IoPlay, IoPause  } from "react-icons/io5";
import useImageSlide from '../Function/useImageSlide';

export default function MainSlide() {
    const ImageList = ['main1', 'main2', 'main3'] // 슬라이드에 들어갈 이미지 배열
    const {nowImage, imageOpacity, animationOn, autoSlide, setAutoSlide, prevImage, nextImage} = useImageSlide(ImageList)

    return (
        <div id='main-slide'>
            <div className="main-slide-container">
                <div className="main-slide-content" style={{ backgroundImage: `url(image/${nowImage}.png)`, opacity:imageOpacity }}>
                    <div className={`content-inner ${animationOn ? 'fadeDown' : ''}`}>
                        <div className="content-title">
                            <div className="title">Restaurant WooDy</div>
                        </div>
                        <div className="content-detail">
                            <p className='kor'>가장 아늑하고 편안한 경험의 가치</p>
                            <p className='eng'>The value of the cosiest and most comfortable experience</p>
                        </div>
                    </div>
                    <div className="move-btn">
                        <SlArrowLeft className='btn' onClick={()=>{prevImage()}}/>
                        <SlArrowRight className='btn' onClick={()=>{nextImage()}}/>
                    </div>
                    <div className="slide-button">
                        <button className='dot'></button>
                        <button className='dot'></button>
                        <button className='dot'></button>
                        <button className='dot'></button>
                        {
                            autoSlide ? <IoPause className="auto-btn" onClick={()=>{setAutoSlide(!autoSlide)}}/>
                            : <IoPlay className="auto-btn" onClick={()=>{setAutoSlide(!autoSlide)}}/>
                        }
                    </div>
                </div>
            </div>
        </div>
    );
}

