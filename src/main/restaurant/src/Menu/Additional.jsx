import React, { useEffect, useState } from 'react';
import './css/Menu.css'
import AutoSlide from '../Function/AutoSlide';

export default function Additional() {
    const slideList = ['dish1', 'dish2', 'dish3', 'dish4', 'dish5', 'dish6']
    const menuListKR = ['안심 스테이크', '토마호크 스테이크', '크림새우 리조또', '유기농 디저트', '홍차']
    const menuListENG = ['Tenderloin Steak', 'Tomahawk Steak', 'Cream shrimp risotto', 'Organic Dessert', 'Tea']

    return (
        <div id='dinner'>
            <div className="menu-container">
                <div className="menu-title">
                    <h1>단품 메뉴</h1>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Labore ab earum hic culpa sapiente corrupti dolorum totam nobis similique id a laboriosam distinctio laborum iste accusantium, sed sint ipsa dolore?</p>
                </div>
                <ul className="menu-set-list" >
                    <li className="set">
                        <AutoSlide width={300} height={300} slideList={slideList}/>
                        <div className="set-title">WooDy Additional Menu</div>
                        <div className="set-inner">
                            <p>별도 가격</p>
                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eligendi, itaque fuga! A sit id ipsum? Rem quod, natus est eligendi qui eaque, voluptatibus laboriosam velit similique molestiae doloremque distinctio eveniet.    </p>
                        </div>
                    </li>
                    <li className="set-detail">
                        <div className="detail-title"> 
                            <h3>Restaurant WooDy</h3>
                            <h1>WooDy Additional Menu</h1>
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

