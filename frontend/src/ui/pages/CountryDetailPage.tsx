import { useParams } from 'react-router-dom';
import { Container, Typography, Paper, CircularProgress, Alert } from '@mui/material';
import {useCountryDetail} from "../../hooks/useCountryDetail";

export default function CountryDetailPage() {
    const { id } = useParams();
    const { data, loading, error } = useCountryDetail(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!data) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3 }}>
                <Typography variant="h4">{data.name}</Typography>
            </Paper>
        </Container>
    );
}