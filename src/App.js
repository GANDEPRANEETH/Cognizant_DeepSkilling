import React from 'react';
import './App.css';

// 1. Data Object List
const offices = [
  { Name: 'DBS', Rent: 50000, Address: 'Chennai' },
  { Name: 'Regus', Rent: 75000, Address: 'Bangalore' }
];

function App() {
  // Image source (replace with a valid URL)
  const officeImg = "https://via.placeholder.com/150";

  return (
      <div className="App">
        {/* 2. Heading element */}
        <h1>Office Space, at Affordable Range</h1>

        {/* 3. Image attribute */}
        <img src={officeImg} width="25%" height="25%" alt="Office Space" />

        {/* 4. Loop through office space items */}
        {offices.map((item, index) => {
          // 5. Conditional styling logic
          const rentStyle = { color: item.Rent <= 60000 ? 'red' : 'green' };

          return (
              <div key={index}>
                <h1>Name: {item.Name}</h1>
                <h3 style={rentStyle}>Rent: Rs. {item.Rent}</h3>
                <h3>Address: {item.Address}</h3>
              </div>
          );
        })}
      </div>
  );
}

export default App;