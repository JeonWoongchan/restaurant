import React, { useEffect, useState } from 'react';
import './css/Menu.css'
import AutoSlide from '../Function/AutoSlide';

export default function Diiner() {
    const slideList = ['dish4', 'dish5', 'dish6']
    const menuListKR = ['스프', '아뮤즈', '메인디쉬', '아이스크림', '차']
    const menuListENG = ['Soup', 'Amuse', 'Main Dish', 'Dessert', 'Tea']

    return (
        <div id='dinner'>
            <div className="menu-container">
                <div className="menu-title">
                    <h1>디너 메뉴</h1>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Labore ab earum hic culpa sapiente corrupti dolorum totam nobis similique id a laboriosam distinctio laborum iste accusantium, sed sint ipsa dolore?</p>
                </div>
                <ul className="menu-set-list" >
                    <li className="set">
                        <AutoSlide width={300} height={300} slideList={slideList}/>
                        <div className="set-title">WooDy Dinner Course</div>
                        <div className="set-inner">
                            <p>155,000 KRW</p>
                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eligendi, itaque fuga! A sit id ipsum? Rem quod, natus est eligendi qui eaque, voluptatibus laboriosam velit similique molestiae doloremque distinctio eveniet.    </p>
                        </div>
                    </li>
                    <li className="set-detail">
                        <div className="detail-title"> 
                            <h3>Restaurant WooDy</h3>
                            <h1>WooDy Dinner Course</h1>
                        </div>
                        <ul className="menu-list">
                            {menuListKR.map((a,i)=>{
                                return(
                                    <li>{a}<br />{menuListENG[i]}</li>
                                )
                            })}
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    );
}

