import { useState } from 'react';
import { Container, TextField, Button, Typography, Paper, Alert } from '@mui/material';
import {useAuth} from "../../hooks/useAuth";

export default function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { login, loading, error } = useAuth();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        await login(username, password);
    };

    return (
        <Container maxWidth="sm">
            <Paper sx={{ p: 4, mt: 8 }}>
                <Typography variant="h5" gutterBottom>Login</Typography>
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
                        label="Password"
                        type="password"
                        fullWidth
                        margin="normal"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <Button type="submit" variant="contained" fullWidth sx={{ mt: 3 }} disabled={loading}>
                        {loading ? 'Loading...' : 'Login'}
                    </Button>
                </form>
            </Paper>
        </Container>
    );
}