import { Navigate } from 'react-router-dom';
import {JSX} from "react";

export default function PrivateRoute({ children }: { children: JSX.Element }) {
    const token = localStorage.getItem('token');
    return token ? children : <Navigate to="/login" />;
}