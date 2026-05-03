import { Container, Box, CircularProgress, Alert } from '@mui/material';
import CountryCard from '../components/CountryCard';
import { useCountries } from '../../hooks/useCountries';

export default function CountriesPage() {
    const { data, loading, error } = useCountries();

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 3 }}>
                {data.map((country) => (
                    <Box key={country.id} sx={{ width: { xs: '100%', sm: 'calc(50% - 12px)', md: 'calc(33.33% - 16px)' } }}>
                        <CountryCard country={country} />
                    </Box>
                ))}
            </Box>
        </Container>
    );
}