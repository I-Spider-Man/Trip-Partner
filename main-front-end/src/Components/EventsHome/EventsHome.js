import React, { useEffect, useState } from 'react'
import NavBar from '../NavBar/NavBar'
import Events from '../Events/Events'
import axios from 'axios';

function EventsHome() {
  const [events,setEvents]=useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/activeEvents');
        setEvents(response.data);
        console.log(response.data); // Log the data, not the state variable
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);
  return (
    <div>
      <div className='nav'><NavBar/></div>
      <div className='event'>
        {events.map(event=>(<>
        <Events key={event.eventId} eventName={event.eventName}/>
        </>
        ))}
      </div>
    </div>
  )
}

export default EventsHome