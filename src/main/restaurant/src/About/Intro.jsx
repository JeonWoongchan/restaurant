import React, { useEffect, useState } from 'react';
import './css/Intro.css'
import useScroll from '../Function/useScroll'

export default function Intro() {
    const imageList = ['main1', 'main2', 'intro1', 'intro2', 'intro3']
    const [nowImage, setNowImage] = useState('main1') 
    const {scrollPosition} = useScroll()

    useEffect(()=>{
        if(scrollPosition <= 400){
            setNowImage(imageList[0])
        }else if(scrollPosition >= 400 && scrollPosition <= 1000){
            setNowImage(imageList[1])
        }else if(scrollPosition >= 1000 && scrollPosition <= 1500){
            setNowImage(imageList[2])
        }else if(scrollPosition >= 1500){
            setNowImage(imageList[3])
        }
        
    },[scrollPosition])

    return (
        <div id='sub-intro'>
            <div className="sub-intro-container">
                <div className="sub-intro-title">
                    <h1>Restaurant WooDy를 소개합니다.</h1>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Non, voluptates sit ratione ex consectetur in delectus fugit, dolor impedit nulla quas dolorem ut laborum officiis ea labore reprehenderit placeat assumenda.</p>
                </div>
                <div className="sub-intro-main">
                    <div className="sub-intro-image">
                        <div className='image-content' style={{background:`url('/image/${nowImage}.png') no-repeat center/cover`}}></div>
                    </div>
                    <div className="sub-intro-text">
                        <div className="text-content">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ut maiores omnis aut, asperiores nesciunt nostrum corrupti voluptatum quisquam, eum inventore saepe incidunt explicabo aliquid error, nulla dolorum. Amet, dolorum porro!</div>
                        <div className="text-content">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ut maiores omnis aut, asperiores nesciunt nostrum corrupti voluptatum quisquam, eum inventore saepe incidunt explicabo aliquid error, nulla dolorum. Amet, dolorum porro!</div>
                        <div className="text-content">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ut maiores omnis aut, asperiores nesciunt nostrum corrupti voluptatum quisquam, eum inventore saepe incidunt explicabo aliquid error, nulla dolorum. Amet, dolorum porro!</div>
                        <div className="text-content">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ut maiores omnis aut, asperiores nesciunt nostrum corrupti voluptatum quisquam, eum inventore saepe incidunt explicabo aliquid error, nulla dolorum. Amet, dolorum porro!</div>
                    </div>
                </div>
            </div>
        </div>
    );
}

