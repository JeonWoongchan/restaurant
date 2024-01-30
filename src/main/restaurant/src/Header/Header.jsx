import React, { useState } from 'react';
import './Header.css'
import { IoMenu } from "react-icons/io5";

export default function Header() {
    const HeaderMenuList = ['About', 'Menu', 'Social', 'Contact']
    const [headerMouseOn, setHeaderMouseOn] = useState(false)

    const headerStyle = ()=>{
        return{
            height : headerMouseOn ? '500px' : ''
        }
    }

    return (
        <div id='header' >
            <div className="header-logo">
                <a href="" className='header-logo-text'>WooDy</a>
            </div>
            <div className="header-menu">
                <ul className='header-menu-list' onMouseLeave={()=>{setHeaderMouseOn(false)}}>
                    {
                        HeaderMenuList.map((a,i)=>{
                            return(
                                <HeaderMenu key={i} menu={a} setHeaderMouseOn={setHeaderMouseOn}/>
                            )
                        })
                    }
                </ul>
            </div>
            <div className="header-btn">
                <IoMenu className='all-menu-btn'/>
            </div>
            <div className="header-menu-more" style={{display: headerMouseOn ? 'block' : 'none'}}>
            </div>
        </div>
    );
}

function HeaderMenu(props) {
    const mouseEnterHandler = ()=>{
        if(!props.HeaderMouseOn){
            props.setHeaderMouseOn(true)
        }
    }

    return(
        <li onMouseEnter={()=>{mouseEnterHandler()}}>
            <a href="" className='menu'>{props.menu}</a>
            <ul className='more-menu-list'>
                <a href="" className='more-menu'>{props.menu}</a>
                <a href="" className='more-menu'>{props.menu}</a>
                <a href="" className='more-menu'>{props.menu}</a>
            </ul>
        </li>
    )
}

