import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(''); // 用于显示反馈消息
    const [isSuccess, setIsSuccess] = useState(null); // 保存成功或失败的状态
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setMessage('');
        setIsSuccess(null);

        try {
            const response = await fetch(import.meta.env.VITE_BACKEND + '/api/v1/auth/signin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + btoa(`${username}:${password}`) // 使用 Basic Auth
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem("token", data.token);
                sessionStorage.setItem("token", data.token);
                setMessage(`Login erfolgreich! Token: ${data.token}`); // 登录成功反馈
                setIsSuccess(true);
                navigate("/initializeTeam");
            } else {
                const errorData = await response.json();
                setMessage(`Login fehlgeschlagen: ${errorData.message || 'Bitte versuchen Sie es erneut.'}`); // 登录失败反馈
                setIsSuccess(false);
            }
        } catch (error) {
            setMessage(`Fehler: ${error.message}`); // 显示网络或其他错误
            setIsSuccess(false);
        }
    };

    return (
        <div className="max-w-md mx-auto p-4">
            <form onSubmit={handleLogin} className="bg-white shadow-md rounded p-6">
                <div className="mb-4">
                    <label className="block text-gray-700">Benutzername</label> {/* 用户名 */}
                    <input
                        type="text"
                        placeholder="Benutzername eingeben" // 输入用户名
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Passwort</label> {/* 密码 */}
                    <input
                        type="password"
                        placeholder="Passwort eingeben" // 输入密码
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="mt-1 p-2 w-full border rounded"
                    />
                </div>
                <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
                    Anmelden {/* 登录按钮 */}
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

export default LoginPage;
