import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Components/HomePage/Home';
import OrganizerHome from './Components/HomePage/OrganizerHome';
import EventsHome from './Components/EventsHome/EventsHome';
import HotSpotHome from './Components/HomePage/HotSpotHome';

function App() {
  return (
    <Router>
    <div className='page'>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/OrganizerHome" element={<OrganizerHome />} />
        <Route path="/EventsHome" element={<EventsHome/>}/>
        <Route path="/HotSpotsHome" element={<HotSpotHome/>}/>
      </Routes>
    </div>
  </Router>
  );
}
export default App;