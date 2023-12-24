import React from 'react'
import './Destination.css'
import Container from '../Container/Container.js';
import Events from '../Events/Events.js';
import Organizer from '../Organizer/Organizer.js';
function Destination() {

  return (
        <div className="frame">
        <Container/>
        <Events/>
        <Organizer/>
        </div>
    );
};


export default Destination
