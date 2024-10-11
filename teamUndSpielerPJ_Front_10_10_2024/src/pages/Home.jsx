import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div className="p-4">
            <h1 className="text-2xl font-bold mb-4">Willkommen bei TeamUndSpielerPJ</h1>
            <nav className="space-x-4">
                <Link to="/signup" className="text-blue-500">Registrieren</Link>
                <Link to="/initializeTeam" className="text-blue-500">Team einrichten</Link>
                <Link to="/createMatch" className="text-blue-500">Spiel erstellen</Link>
            </nav>
        </div>
    );
};

export default Home;
