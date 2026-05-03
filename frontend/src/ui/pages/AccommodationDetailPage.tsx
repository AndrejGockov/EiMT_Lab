import { useParams } from 'react-router-dom';
import { Container, Typography, Paper, CircularProgress, Alert } from '@mui/material';
import {useAccommodationDetail} from "../../hooks/useAccommodationDetail";

export default function AccommodationDetailPage() {
    const { id } = useParams();
    const { data, loading, error } = useAccommodationDetail(Number(id));

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;
    if (!data) return <Alert severity="warning">Not found</Alert>;

    return (
        <Container>
            <Paper sx={{ p: 3 }}>
                <Typography variant="h4">{data.name}</Typography>
                <Typography>Category: {data.category}</Typography>
                <Typography>Condition: {data.condition}</Typography>
                <Typography>Number of rooms: {data.numRooms}</Typography>
                <Typography>Rented: {data.rented ? 'Yes' : 'No'}</Typography>
                <Typography>Work start date: {data.workStartDate ? new Date(data.workStartDate).toLocaleDateString() : 'Not set'}</Typography>
                {data.host && (
                    <>
                        <Typography variant="h6" sx={{ mt: 2 }}>Host</Typography>
                        <Typography>Name: {data.host.name} {data.host.surname}</Typography>
                        <Typography>Email: {data.host.email}</Typography>
                        <Typography>Country: {data.host.country?.name}</Typography>
                    </>
                )}
            </Paper>
        </Container>
    );
}