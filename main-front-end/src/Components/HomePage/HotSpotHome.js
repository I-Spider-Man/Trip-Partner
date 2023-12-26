import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import Hotspots from './Hotspots'
import axios from 'axios';

function HotSpotHome() {
  const [spots,setSpots] =useState([]);
  useEffect(()=>{
    const fetchSpot=async()=>{
<<<<<<< HEAD
    try{await axios.get('http://localhost:8080/Spots')
=======
    try{await axios.get('http://localhost:8080/spots')
>>>>>>> 98a1db2bea441de136e4cb7c6d43454aa9761ffa
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
