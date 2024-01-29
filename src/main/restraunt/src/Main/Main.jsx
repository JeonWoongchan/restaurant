import React from 'react';
import MainSlide from './MainSlide';
import MainIntro from './mainIntro';
import MainInfo from './mainInfo';

export default function Main() {
    return (
        <div>
            <MainSlide></MainSlide>
            <MainIntro></MainIntro>
            <MainInfo></MainInfo>
        </div>
    );
}

