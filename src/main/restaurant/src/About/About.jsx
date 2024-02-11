import React, { useState } from 'react';
import TabPage from '../TabPage/TabPage';
import Intro from './Intro'
import Chef from './Chef'
import { useParams } from 'react-router-dom';

export default function About() {
    const { subMenu } = useParams()

    return (
        <div id='about'>
            <TabPage tabMenu={['레스토랑 소개', '쉐프 소개']} tabLink={['intro', 'chef']} subMenu={subMenu === 'intro' ? <Intro/> : subMenu === 'chef' ? <Chef/> : null}/>
        </div>
    );
}

