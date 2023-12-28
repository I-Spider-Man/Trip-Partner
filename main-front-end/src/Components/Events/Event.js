import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import { useParams } from 'react-router-dom'
import axios from 'axios';
import ContentLoading from '../LoadingComponents/ContentLoading';
import './Event.css'
function Event() {
  const {eventId} = useParams();
  const [event,setEvent]=useState();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/activeEvents/${eventId}`);
        setEvent(response.data);
        console.log(response.data); // Log the data, not the state variable
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, [eventId]);

  const onOrganize=()=>{
    
  }

  return (
    <div className='home'>
    <div className='nav'><NavBar/></div>
    <div className='event-content'>
      {event ? (
        <><label>Event Name: {event.eventName}</label>
          <label>Event location: {event.location}</label>
          <label>Event description: {event.description}</label>
          <label>Event will be happening date: {event.startDate} - {event.endDate}</label>
          <label>Population level: {(event.peopleCount>50)?"high":"low"}</label></>
      ) : (
        <div className='loading-container'><ContentLoading/></div>
        
      )}
    </div>
    <div className='join-organize-button'>
      <button >Join</button>
      <button onClick={onOrganize}>Organize</button>
    </div>
    
    </div>
  )
}

export default Event
