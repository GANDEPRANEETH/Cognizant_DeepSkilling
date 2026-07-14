import React from 'react';
// Import 'players' along with your components
import { ListofPlayers, Scorebelow70, OddPlayers, EvenPlayers, IndianPlayers, players } from './Cricket';

function App() {
    const flag = true;

    return (
        <div>
            {flag ? (
                <div>
                    <h1>List of Players</h1>
                    <ListofPlayers players={players} />
                    <h1>List of Players having Scores Less than 70</h1>
                    <Scorebelow70 players={players} />
                </div>
            ) : (
                <div>
                    <h1>Odd Players</h1>
                    <OddPlayers players={IndianPlayers} />
                    <h1>Even Players</h1>
                    <EvenPlayers players={IndianPlayers} />
                </div>
            )}
        </div>
    );
}
export default App;