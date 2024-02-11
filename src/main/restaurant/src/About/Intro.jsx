import React, { useState } from 'react';
import './css/Intro.css'

export default function Intro() {
    const imageList = ['main1', 'main2', 'intro1', 'intro2', 'intro3']
    const [nowImage, setNowImage] = useState('main1') 

    return (
        <div id='sub-intro'>
            <div className="sub-intro-container">
                <div className="sub-intro-image">
                    <div className='image-content'>
                        <img src={`/image/${nowImage}.png`} alt="" />
                    </div>
                </div>
                <div className="sub-title-text"></div>
            </div>
        </div>
    );
}

