import React from 'react';

// Data for ListofPlayers
export const players = [
    { name: 'Jack', score: 50 }, { name: 'Michael', score: 70 },
    { name: 'John', score: 40 }, { name: 'Ann', score: 61 },
    { name: 'Elisabeth', score: 61 }, { name: 'Sachin', score: 95 },
    { name: 'Dhoni', score: 100 }, { name: 'Virat', score: 84 },
    { name: 'Jadeja', score: 64 }, { name: 'Raina', score: 75 },
    { name: 'Rohit', score: 80 }
];

// Component: ListofPlayers
export const ListofPlayers = ({ players }) => (
    <ul>
        {players.map((item, index) => (
            <li key={index}>Mr. {item.name} {item.score}</li>
        ))}
    </ul>
);

// Component: Scorebelow70
export const Scorebelow70 = ({ players }) => (
    <ul>
        {players.filter(item => item.score <= 70).map((item, index) => (
            <li key={index}>Mr. {item.name} {item.score}</li>
        ))}
    </ul>
);

// Data for IndianPlayers
const T20Players = ['Sachin1', 'Dhoni2', 'Virat3', 'Rohit4', 'Yuvraj5', 'Raina6'];
const RanjiTrophyPlayers = ['Player7', 'Player8', 'Player9', 'Player10', 'Player11'];
export const IndianPlayers = [...T20Players, ...RanjiTrophyPlayers];

// Component: OddPlayers using Destructuring
export const OddPlayers = ({ players }) => {
    const [first, , third, , fifth] = players;
    return (
        <div>
            <li>First : {first}</li>
            <li>Third : {third}</li>
            <li>Fifth : {fifth}</li>
        </div>
    );
};

// Component: EvenPlayers using Destructuring
export const EvenPlayers = ({ players }) => {
    const [, second, , fourth, , sixth] = players;
    return (
        <div>
            <li>Second : {second}</li>
            <li>Fourth : {fourth}</li>
            <li>Sixth : {sixth}</li>
        </div>
    );
};