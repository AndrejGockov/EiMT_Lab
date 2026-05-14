import { useState } from 'react';
import { authRepository } from '../api/authRepository';
import { useNavigate } from 'react-router-dom';

export const useAuth = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const login = async (username: string, password: string) => {
        setLoading(true);
        try {
            const res = await authRepository.login(username, password);
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('role', res.data.role || 'USER'); // fallback
            navigate('/accommodations');
            return res.data;
        } catch (err: any) {
            setError(err.response?.data?.error || err.message);
            throw err;
        } finally {
            setLoading(false);
        }
    };

    const register = async (username: string, email: string, password: string) => {
        setLoading(true);
        try {
            await authRepository.register(username, email, password);
            navigate('/login');
        } catch (err: any) {
            setError(err.response?.data?.error || err.message);
            throw err;
        } finally {
            setLoading(false);
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        navigate('/login');
    };

    const getRole = () => localStorage.getItem('role');
    const isAdmin = () => getRole() === 'ADMIN';

    return { login, register, logout, loading, error, getRole, isAdmin };
};