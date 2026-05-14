import axiosInstance from "../axios/axios";

export const authRepository = {
    login: (username: string, password: string) =>
        axiosInstance.post<{ token: string; role: string }>('/auth/login', { username, password }),
    register: (username: string, email: string, password: string) =>
        axiosInstance.post('/auth/register', { username, email, password }),
};