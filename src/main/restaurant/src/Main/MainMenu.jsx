import React, { useEffect, useState } from 'react';
import { IoAdd } from "react-icons/io5";
import './css/MainMenu.css'
import useScroll from '../Function/useScroll'
import menuList from '../Json/menuList.json'
import { useNavigate } from 'react-router-dom';

export default function MainMenu() {
    const {scrollPosition} = useScroll();
    const [boxAnimation, setBoxAnimation] = useState(['base-position', 'base-position', 'base-position'])
    
    useEffect(()=>{
        if(scrollPosition > 1150){
            setBoxAnimation(['first-slide-up ', 'second-slide-up', 'third-slide-up'])
        }
    },[scrollPosition])

    return (
        <div id='main-menu' className='section-container'>
            <div className="main-menu-container">
                <div className="main-menu-title">
                    <p className='sub'>Restaurant WooDy</p>
                    <h2 className='main'>WooDy Menu</h2>
                </div>
                <div className="main-menu-list">
                    {
                        menuList.map((a,i)=>{
                            return(
                                <MenuItem key={i} title={a.title} image={a.image} detail={a.detail} price={a.price} style={boxAnimation[i]}/>
                            )
                        })
                    }
                </div>
            </div>
        </div>
    );
}

function MenuItem(props){
    const navigate = useNavigate()

    return(
        <div className={`menu-item ${props.style}`} style={{backgroundImage : `url(/image/${props.image}.png)`}} onClick={()=>{navigate(`menu/${props.title}`)}}>
            <div className="item-title">{props.title}</div>
            <IoAdd className='more-btn'/>
            <div className="item-detail">
                {props.detail}
                <div className="line"></div>
                {props.price}
            </div>
        </div>
    )
}

