import React from 'react';
import './Header.css'
import { IoMenu } from "react-icons/io5";

export default function Header() {
    return (
        <div id='header'>
            <div className="header-logo">
                <a href="" className='header-logo-text'>
                    {/* <img className='header-logo-img' src="" alt="" /> */}
                    WooDy
                </a>
            </div>
            <div className="header-menu">
                <ul className='header-menu-list'>
                    <li><a href="" className='menu'>About</a></li>
                    <li><a href="" className='menu'>Menu</a></li>
                    <li><a href="" className='menu'>Social</a></li>
                    <li><a href="" className='menu'>Contact</a></li>
                </ul>
            </div>
            <div className="header-btn">
                <a href="">
                    <IoMenu className='all-menu-btn'/>
                </a>
            </div>
        </div>
    );
}

