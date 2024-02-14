import React from 'react';
import './css/Map.css'

export default function Map() {
    const mapImage = 'map1'

    return (
        <div id='map'>
            <div className="map-container">
                <div className="map-title">
                    <h1>Restaurant WooDy 오시는 길</h1>
                </div>
                <div className="map-main">
                    <div className="map-image" style={{background : `url(/image/${mapImage}.png) no-repeat center/cover`}}></div>
                    <div className="map-text">
                        
                    </div>
                </div>
            </div>
        </div>
    );
}

