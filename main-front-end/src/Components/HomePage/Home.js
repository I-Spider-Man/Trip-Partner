import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import SearchBar from '../SearchBar/SearchBar'
import './Home.css'
import Events from '../Events/Events'
import Hotspots from './Hotspots'
import axios from 'axios'
function Home() {
  const [events,setEvents]=useState([]);
  const [spots,setSpots]=useState([]);
  useEffect(()=>{
    const fetchEvents=async()=>{
      try{
        const response=await axios.get('http://localhost:8080/PopularEvents');
      setEvents(response.data);
      console.log(response.data);
    }catch(error){
      console.error(error);
    }
  };
  const fetchSpots=async()=>{
    try{
      const response=await axios.get('http://localhost:8080/PopularSpots');
      setSpots(response.data);
    }catch(error){
      console.error();
    }
  };
  fetchSpots();
  fetchEvents();
},[]);
  return (
    <div className='home'>
        <div className='nav-container'><NavBar/></div>
        <div className='search-container'>
          <SearchBar/>
        </div>
        <div>
          <h3>Events</h3>
          <div className='popular-event-container'>
            {events.map(event=>(<><Events key={event.eventId} eventId={event.eventId} eventName={event.eventName} eventDiscription={event.description} /></>))}
          </div>
        </div>
        <div><h3>Popular Spots</h3><div className='popular-hotspot-container'>
          {spots.map(spot=>(<><Hotspots key={spot.spotId} spotId={spot.spotId} spotName={spot.spotName} spotDescription={spot.description} /></>))}
        </div>
    </div>
    </div>
  )
}
export default Home
