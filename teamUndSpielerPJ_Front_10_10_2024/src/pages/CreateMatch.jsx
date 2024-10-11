// src/pages/CreateMatch.jsx
import React, { useState } from 'react';

const CreateMatch = () => {
    const [userId, setUserId] = useState(null); // 当前用户的团队 ID
    const [opponentTeamId, setOpponentTeamId] = useState(null); // 对手团队的 ID
    const [matchData, setMatchData] = useState(null); // 存储比赛数据
    const [message, setMessage] = useState(''); // 显示反馈消息
    const [teamAPlayers, setTeamAPlayers] = useState([]); // 存储队伍A的球员信息
    const [teamBPlayers, setTeamBPlayers] = useState([]); // 存储队伍B的球员信息

    const handleCreateMatch = async (e) => {
        e.preventDefault();
        setMessage(''); // 清除之前的消息

        const response = await fetch('http://localhost:8080/api/v1/users/creatematch', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId,              // 当前用户的团队 ID
                opponentTeamId,      // 对手团队的 ID
            }),
        });

        if (response.ok) {
            const data = await response.json(); // 获取响应数据
            setMatchData(data); // 存储比赛数据以供显示
            setMessage('Match erfolgreich erstellt!'); // 成功反馈

            // 获取团队 A 的球员信息
            const teamAResponse = await fetch(`http://localhost:8080/api/v1/users/teams/${userId}`);
            if (teamAResponse.ok) {
                const teamAData = await teamAResponse.json();
                setTeamAPlayers(teamAData.spielers); // 存储队伍A的球员信息
            }

            // 获取团队 B 的球员信息
            const teamBResponse = await fetch(`http://localhost:8080/api/v1/users/teams/${opponentTeamId}`);
            if (teamBResponse.ok) {
                const teamBData = await teamBResponse.json();
                setTeamBPlayers(teamBData.spielers); // 存储队伍B的球员信息
            }
        } else {
            const errorData = await response.json();
            setMessage(`Fehler beim Erstellen des Spiels: ${errorData.message || 'Bitte versuchen Sie es erneut.'}`); // 失败反馈
        }
    };

    return (
        <div className="max-w-md mx-auto p-4">
            <form onSubmit={handleCreateMatch} className="bg-white shadow-md rounded p-6">
                <div className="mb-4">
                    <label className="block text-gray-700">Benutzer-ID</label>
                    <input
                        type="number"
                        placeholder="Benutzer-ID eingeben"
                        value={userId || ''}
                        onChange={(e) => setUserId(Number(e.target.value))}
                        className="mt-1 p-2 w-full border rounded"
                        required
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Gegner-Team-ID</label>
                    <input
                        type="number"
                        placeholder="Gegner-Team-ID eingeben"
                        value={opponentTeamId || ''}
                        onChange={(e) => setOpponentTeamId(Number(e.target.value))}
                        className="mt-1 p-2 w-full border rounded"
                        required
                    />
                </div>
                <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
                    Spiel erstellen
                </button>
            </form>

            {/* 显示反馈信息 */}
            {message && (
                <div className="mt-4 p-2 text-center text-red-500">
                    {message}
                </div>
            )}

            {/* 显示比赛数据 */}
            {matchData && (
                <div className="mt-4 p-2 bg-gray-100 rounded">
                    <h2 className="text-lg font-bold">Spiel Details:</h2>
                    <ul className="list-disc list-inside">
                        <li><strong>ID:</strong> {matchData.id}</li>
                        <li><strong>Team A ID:</strong> {matchData.teamAId}</li>
                        <li><strong>Team B ID:</strong> {matchData.teamBId}</li>
                        <li><strong>Gewinner-Team-ID:</strong> {matchData.gewinnTeamId}</li>
                    </ul>
                </div>
            )}

            {/* 显示球队 A 的球员信息 */}
            {teamAPlayers.length > 0 && (
                <div className="mt-4 p-4 bg-blue-100 rounded">
                    <h2 className="text-lg font-bold">Spieler von Team A:</h2>
                    <ul className="list-disc list-inside">
                        {teamAPlayers.map(player => (
                            <li key={player.id}>
                                Spieler-ID: {player.id}, Leistungsniveau: {player.powerLevel}
                            </li>
                        ))}
                    </ul>
                </div>
            )}

            {/* 显示球队 B 的球员信息 */}
            {teamBPlayers.length > 0 && (
                <div className="mt-4 p-4 bg-green-100 rounded">
                    <h2 className="text-lg font-bold">Spieler von Team B:</h2>
                    <ul className="list-disc list-inside">
                        {teamBPlayers.map(player => (
                            <li key={player.id}>
                                Spieler-ID: {player.id}, Leistungsniveau: {player.powerLevel}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default CreateMatch;
