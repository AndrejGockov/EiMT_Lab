import { useState } from 'react';
import {useAuth} from "../../hooks/useAuth";
import { Container, TextField, Button, Typography, Paper, Alert } from '@mui/material';

export default function RegisterPage() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const { register, loading, error } = useAuth();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        await register(username, email, password);
    };

    return (
        <Container maxWidth="sm">
            <Paper sx={{ p: 4, mt: 8 }}>
                <Typography variant="h5" gutterBottom>Register</Typography>
                {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
                <form onSubmit={handleSubmit}>
                    <TextField
                        label="Username"
                        fullWidth
                        margin="normal"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                    <TextField
                        label="Email"
                        type="email"
                        fullWidth
                        margin="normal"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <TextField
                        label="Password"
                        type="password"
                        fullWidth
                        margin="normal"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <Button type="submit" variant="contained" fullWidth sx={{ mt: 3 }} disabled={loading}>
                        {loading ? 'Loading...' : 'Register'}
                    </Button>
                </form>
            </Paper>
        </Container>
    );
}