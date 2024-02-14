import React, { useEffect, useState } from 'react';
import './css/Header.css'
import HeaderLeft from './HeaderLeft';
import { IoMenu } from "react-icons/io5";
import { useNavigate } from 'react-router-dom';

export default function Header() {
    const HeaderMenuList = ['About', 'Menu', 'Contact', 'Social']
    const [headerLeftOn, setHeaderLeftOn] = useState(false)
    const [headerMouseOn, setHeaderMouseOn] = useState(false) // 마우스가 헤더메뉴에 있음
    const navigate = useNavigate()

    return (
        <div id='header'>
            <div className="header-logo" onClick={()=>{navigate('/')}}>
                <a href="" className='header-logo-text'>WooDy</a>
            </div>
            <div className="header-menu">
                <ul className='header-menu-list' onMouseEnter={()=>{setHeaderMouseOn(true)}} onMouseLeave={()=>{setHeaderMouseOn(false)}}>
                    {
                        HeaderMenuList.map((a,i)=>{
                            return(
                                <HeaderMenu key={i} menu={a} headerMouseOn={headerMouseOn} setHeaderMouseOn={setHeaderMouseOn}/>
                            )
                        })
                    }
                </ul>
            </div>
            <div className="header-btn">
                <IoMenu className='all-menu-btn' onClick={()=>{setHeaderLeftOn(!headerLeftOn)}}/>
            </div>
            <div className="header-menu-more-container" onMouseEnter={()=>{setHeaderMouseOn(true)}} style={{display: headerMouseOn ? 'block' : 'none'}}>
                <div className='header-menu-more'>
                    <div className="more-menu-area" onMouseLeave={()=>{setHeaderMouseOn(false)}}></div>
                </div>
            </div>
            {
                headerLeftOn ? <HeaderLeft headerLeftOn={headerLeftOn} setHeaderLeftOn={setHeaderLeftOn}/> : null
            }
        </div>
    );
}

function HeaderMenu(props) {
    const MoreMenuList = {'About':['레스토랑 소개', '쉐프 소개'], 'Menu':['Lunch', 'Dinner', '단품 메뉴'], 'Contact':['오시는 길', '예약'], 'Social':['인스타그램', '유튜브']}
    const MoreMenuLink = {'About':['/about/intro', '/about/chef'], 'Menu':['/menu/lunch', '/menu/dinner', '/menu/aditional'], 'Contact':['/contact/map', '/reservation'], 'Social':['/', '/']}
    const navigate = useNavigate()

    return(
        <li>
            <a href="" className='menu'>{props.menu}</a>
            <div className='more-menu-list' style={{display: props.headerMouseOn ? '' : 'none'}} >
                {
                    MoreMenuList[props.menu].map((a,i)=>{
                        return(
                            <a href="" className='more-menu' key={i} onClick={()=>{navigate(MoreMenuLink[props.menu][i]); props.setHeaderMouseOn(false)}}>{a}</a>
                        )
                    })
                }
            </div>
        </li>
    )
}

