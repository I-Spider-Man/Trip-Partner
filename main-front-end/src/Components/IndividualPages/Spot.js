import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import { useParams } from 'react-router-dom'
import axios from 'axios';
import Loading from '../Loading';

function Spot() {
  const {spotId} = useParams();
  const [spot,setSpot]=useState();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/Spots/${spotId}`);
        setSpot(response.data);
        console.log(response.data); // Log the data, not the state variable
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, [spotId]);
  return (
    <div className='home'>
    <div className='nav'><NavBar/></div>
    {spot ? (
        <div>
          <label>spot Name: {spot.spotName}</label>
          <label>spot location: {spot.location}</label>
          <label>spot description: {spot.description}</label>
          <label>Population level: {(spot.peopleCount>50)?"high":"low"}</label>
        </div>
      ) : (
        <Loading/>
      )}
    </div>
  )
}

export default Spot
