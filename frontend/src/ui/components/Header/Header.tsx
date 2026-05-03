import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import {useAuth} from "../../../hooks/useAuth";

export default function Header() {
    const navigate = useNavigate();
    const { logout } = useAuth();
    const token = localStorage.getItem('token');

    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" component={Link} to="/" sx={{ flexGrow: 1, textDecoration: 'none', color: 'white' }}>
                    Accommodation Rental
                </Typography>
                <Box>
                    <Button color="inherit" component={Link} to="/accommodations">Accommodations</Button>
                    <Button color="inherit" component={Link} to="/hosts">Hosts</Button>
                    <Button color="inherit" component={Link} to="/countries">Countries</Button>
                    {token ? (
                        <Button color="inherit" onClick={logout}>Logout</Button>
                    ) : (
                        <>
                            <Button color="inherit" component={Link} to="/login">Login</Button>
                            <Button color="inherit" component={Link} to="/register">Register</Button>
                        </>
                    )}
                </Box>
            </Toolbar>
        </AppBar>
    );
}