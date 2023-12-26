import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import Hotspots from './Hotspots'
import axios from 'axios';

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
      <div className='hotspot'>
        {spots.map(spot=>(<>
        <Hotspots key={spot.spotId} spotName={spot.spotName}/></>))

        }
      </div>
    </div>
  )
}

export default HotSpotHome
