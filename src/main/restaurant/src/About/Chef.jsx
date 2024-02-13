import React from 'react';
import './css/Chef.css'

export default function Chef() {
    const chefImage = 'chef2'

    return (
        <div id='sub-chef'>
            <div className="sub-chef-container">
                <div className="sub-chef-title">
                    <h1>레스토랑 WooDy 오너 셰프</h1>
                    <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Labore ab earum hic culpa sapiente corrupti dolorum totam nobis similique id a laboriosam distinctio laborum iste accusantium, sed sint ipsa dolore?</p>
                </div>
                <div className="sub-chef-main">
                    <div className="sub-chef-image" style={{ background: `url(/image/${chefImage}.png) no-repeat center/cover` }}></div>
                    <div className="sub-chef-text">
                        <div className="chef-name">
                            <h3>Owner Chef</h3>
                            <h1>Bryan Fury <span>셰프</span></h1>
                        </div>
                        <ul className="chef-detail">
                            <li className='detail'>前 두바이 OOOO호텔 헤드 셰프</li>
                            <li className='detail'>前 뉴욕 OOOO 헤드 셰프</li>
                            <li className='detail'>레스토랑 WooDy 오너 셰프</li>
                        </ul>
                    </div>
                </div>
                {/* <div className="sub-chef-career"></div> */}
            </div>
        </div>
    );
}

