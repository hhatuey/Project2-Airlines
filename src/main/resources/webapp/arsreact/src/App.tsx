import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';

import { Book } from './Components/Flight/Book';
import { Footer } from './Components/Landing/Footer';
import './Components/Landing/Main'
import { Main } from './Components/Landing/Main';
import { Login } from './Components/Login/Login';
import { Register } from './Components/Register/Register';
import { SeatDisplay } from './Components/Seat/Seat';
import { TicketDisplay } from './Components/TicketDisplay';
import { ITicket } from './Interfaces/ITicket';

function App() {


  return (
    <div className="grad h-100">
      <Router> 
        <Switch>
          <Route exact path='/' component = {Main}/>
          <Route exact path='/login' component = {Login}/>    
          <Route exact path='/register' component = {Register}/>
          <Route exact path='/book' component = {Book}/>
          <Route exact path='/seat' component = {SeatDisplay}/>
          <Route exact path='/ticket' component = {TicketDisplay}/>
        </Switch>
      </Router>
      
      <Footer />
    </div>
  );
}

export default App;
