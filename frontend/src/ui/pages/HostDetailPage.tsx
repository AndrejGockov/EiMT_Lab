import { useParams } from 'react-router-dom';
import { Container, Typography, Paper, CircularProgress, Alert } from '@mui/material';
import {useHostDetail} from "../../hooks/useHostDetail";

export default function HostDetailPage() {
    const { id } = useParams();
    const { data, loading, error } = useHostDetail(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!data) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3 }}>
                <Typography variant="h4">{data.name} {data.surname}</Typography>
                <Typography>Email: {data.email}</Typography>
                <Typography>Country: {data.country?.name}</Typography>
            </Paper>
        </Container>
    );
}