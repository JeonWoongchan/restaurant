import React from 'react';
import MainSlide from './MainSlide.jsx';
import MainIntro from './MainIntro.jsx';
import MainMenu from './MainMenu.jsx';
import MainInfo from './MainInfo.jsx';

export default function Main() {
    return (
        <div>
            <MainSlide></MainSlide>
            <MainIntro></MainIntro>
            <MainMenu></MainMenu>
            <MainInfo></MainInfo>
        </div>
    );
}

