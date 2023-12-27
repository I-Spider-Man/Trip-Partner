import React from 'react'
import Logo from 'C://Users//alfredeins_gabriel//Desktop//React//Trip-Partner-front-end//main-front-end//src//Assests//Modules.png'
import './Hotspots.css'
import '../styleguide.css'
function Hotspots({spotName}) {
  return (
    <div>
      <div className="spot-card">
  <img src={Logo}/>
  <div className="spot-card__content">
    <p className="spot-card__title">Project Name</p>
    <p className="spot-card__description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    <button className="spot-card__button"><span className='view-more'>View More!</span></button>
  </div>
</div>

    </div>
  )
}

export default Hotspots
