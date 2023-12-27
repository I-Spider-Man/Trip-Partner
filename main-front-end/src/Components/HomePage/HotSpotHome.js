import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import Hotspots from './Hotspots'
import axios from 'axios';
import './HotSpotsHome.css';
function HotSpotHome() {
  const [spots,setSpots] =useState([]);
  useEffect(()=>{
    const fetchSpot=async()=>{
    try{await axios.get('http://localhost:8080/Spots')
    .then(response=>{
      setSpots(response.data);
    })}catch(error){
       console.error(error);
    }
  };
  fetchSpot();

  },[])
  return (
    <div>
      <div className='nav'>
        <NavBar/>
      </div>
      <div className='hotspot-container'>
        {spots.map(spot=>(<>
        <Hotspots key={spot.spotId} spotId={spot.spotId} spotName={spot.spotName} spotDescription={spot.description}/></>))

        }
      </div>
    </div>
  )
}

export default HotSpotHome
