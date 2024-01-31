import React, { useEffect, useRef, useState } from 'react';
import './css/Header.css'
import HeaderLeft from './HeaderLeft';
import { IoMenu } from "react-icons/io5";

export default function Header() {
    const HeaderMenuList = ['About', 'Menu', 'Contact', 'Social']
    const [headerLeftOn, setHeaderLeftOn] = useState(false)
    const [headerMouseOn, setHeaderMouseOn] = useState(false) // 마우스가 헤더메뉴에 있음
    const [moreMenuOn, setMoreMenuOn] = useState(false) // 마우스가 드롭다운 메뉴에 있음

    const moreMenuRef = useRef(null)

    return (
        <div id='header'>
            <div className="header-logo">
                <a href="" className='header-logo-text'>WooDy</a>
            </div>
            <div className="header-menu">
                <ul className='header-menu-list' onMouseEnter={()=>{setHeaderMouseOn(true)}} onMouseLeave={()=>{setHeaderMouseOn(false)}}>
                    {
                        HeaderMenuList.map((a,i)=>{
                            return(
                                <HeaderMenu key={i} menu={a} headerMouseOn={headerMouseOn}/>
                            )
                        })
                    }
                </ul>
            </div>
            <div className="header-btn">
                <IoMenu className='all-menu-btn' onClick={()=>{setHeaderLeftOn(!headerLeftOn)}}/>
            </div>
            <div className="header-menu-more-container" onMouseEnter={()=>{setHeaderMouseOn(true)}}>
                <div className='header-menu-more' style={{display: headerMouseOn ? 'block' : 'none'}} >
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
    const MoreMenuList = {'About':['레스토랑 소개', '쉐프 소개'], 'Menu':['Lunch', 'Dinner'], 'Contact':['공지사항', '예약', '오시는길'], 'Social':['인스타그램']}

    return(
        <li>
            <a href="" className='menu'>{props.menu}</a>
            <ul className='more-menu-list' style={{display: props.headerMouseOn ? '' : 'none'}} >
                {
                    MoreMenuList[props.menu].map((a,i)=>{
                        return(
                            <a href="" className='more-menu' key={i}>{a}</a>
                        )
                    })
                }
            </ul>
        </li>
    )
}

