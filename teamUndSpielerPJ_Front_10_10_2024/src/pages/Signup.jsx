import React, { useState } from 'react';

const Signup = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(''); // 用于显示反馈消息
    const [isSuccess, setIsSuccess] = useState(null); // 用于保存成功或失败的状态

    const handleSignup = async (e) => {
        e.preventDefault();
        setMessage(''); // 提交前清除之前的消息
        setIsSuccess(null); // 清除之前的成功状态

        const response = await fetch('http://localhost:8080/api/v1/auth/signup', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
            setMessage('Registrierung erfolgreich!'); // 成功提示
            setIsSuccess(true); // 设置为成功状态
        } else {
            setMessage('Registrierung fehlgeschlagen, bitte versuchen Sie es erneut.'); // 失败提示
            setIsSuccess(false); // 设置为失败状态
        }
    };

    return (
        <div className="max-w-md mx-auto p-4">
            <form onSubmit={handleSignup} className="bg-white shadow-md rounded p-6">
                <div className="mb-4">
                    <label className="block text-gray-700">Benutzername</label>
                    <input
                        type="text"
                        placeholder="Benutzername eingeben"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Passwort</label>
                    <input
                        type="password"
                        placeholder="Passwort eingeben"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                    />
                </div>
                <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
                    Registrieren
                </button>
            </form>

            {/* 显示反馈信息 */}
            {message && (
                <div className={`mt-4 p-2 text-center ${isSuccess ? 'text-green-500' : 'text-red-500'}`}>
                    {message}
                </div>
            )}
        </div>
    );
};

export default Signup;
