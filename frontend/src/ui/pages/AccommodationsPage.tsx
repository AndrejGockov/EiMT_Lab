import { Container, Box, CircularProgress, Alert } from '@mui/material';
import AccommodationCard from '../components/AccommodationCard';
import { useAccommodations } from '../../hooks/useAccommodations';

export default function AccommodationsPage() {
    const { data, loading, error } = useAccommodations();

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 3 }}>
                {data.map((acc) => (
                    <Box key={acc.id} sx={{ width: { xs: '100%', sm: 'calc(50% - 12px)', md: 'calc(33.33% - 16px)' } }}>
                        <AccommodationCard accommodation={acc} />
                    </Box>
                ))}
            </Box>
        </Container>
    );
}