import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Components/HomePage/Home';
import OrganizerHome from './Components/Organizer/OrganizerHome';
import EventsHome from './Components/Events/EventsHome';
import HotSpotHome from './Components/TouristSpots/TouristSpotHomePage';
import Event from './Components/Events/Event';
import TouristSpot from './Components/TouristSpots/TouristSpot';

function App() {
  return (
    <Router>
    <div className='page'>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/OrganizerHome" element={<OrganizerHome />} />
        <Route path="/EventsHome" element={<EventsHome/>}/>
        <Route path="/HotSpotsHome" element={<HotSpotHome/>}/>
        <Route path='/Events/:eventId' element={<Event/>}/>
        <Route path='/Spot/:spotId' element={<TouristSpot/>}/>
      </Routes>
    </div>
  </Router>
  );
}
export default App;