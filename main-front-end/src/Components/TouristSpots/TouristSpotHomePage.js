import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar';
import axios from 'axios';
import './TouristSpotsHomePage.css';
import TouristSpotComponent from './TouristSpotComponent';
function TouristSpotHomePage() {
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
        <TouristSpotComponent key={spot.spotId} spotId={spot.spotId} spotName={spot.spotName} spotDescription={spot.description}/></>))

        }
      </div>
    </div>
  )
}

export default TouristSpotHomePage;