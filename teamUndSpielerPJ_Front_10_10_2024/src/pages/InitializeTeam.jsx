import React, { useState } from 'react';

const InitializeTeam = () => {
    const [userId, setUserId] = useState(''); // 用户 ID
    const [teamData, setTeamData] = useState(null); // 存储团队信息
    const [message, setMessage] = useState(''); // 显示反馈消息

    const handleInitializeTeam = async (e) => {
        e.preventDefault();
        setMessage(''); // 清除之前的消息

        const response = await fetch(`http://localhost:8080/api/v1/users/initializeTeam/${userId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            // 只发送用户 ID，无需团队名称
            body: JSON.stringify({}),
        });

        if (response.ok) {
            const data = await response.json(); // 获取团队数据
            setTeamData(data); // 存储团队信息以供显示
            setMessage('Team initialized successfully!'); // 成功反馈
        } else {
            const errorData = await response.json();
            setMessage(`Failed to initialize team: ${errorData.message || 'Please try again.'}`); // 失败反馈
        }
    };

    return (
        <div className="max-w-md mx-auto p-4">
            <form onSubmit={handleInitializeTeam} className="bg-white shadow-md rounded p-6">
                <div className="mb-4">
                    <label className="block text-gray-700">User ID</label>
                    <input
                        type="number"
                        placeholder="Enter User ID"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                        required
                    />
                </div>
                <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
                    Initialize Team
                </button>
            </form>

            {/* 显示反馈信息 */}
            {message && (
                <div className="mt-4 p-2 text-center text-red-500">
                    {message}
                </div>
            )}

            {/* 显示团队信息 */}
            {teamData && (
                <div className="mt-4 p-2 bg-gray-100 rounded">
                    <h2 className="text-lg font-bold">Team Details:</h2>
                    <ul className="list-disc list-inside">
                        <li><strong>ID:</strong> {teamData.id}</li>
                        <li><strong>Name:</strong> {teamData.name}</li>
                        <li><strong>Players:</strong> {Array.isArray(teamData.spielers) ? teamData.spielers.map(player => player.id).join(', ') : 'No players available'}</li>
                    </ul>
                </div>
            )}
        </div>
    );
};

export default InitializeTeam;