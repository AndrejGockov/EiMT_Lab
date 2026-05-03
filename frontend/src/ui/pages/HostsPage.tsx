import { Container, Box, CircularProgress, Alert } from '@mui/material';
import HostCard from '../components/HostCard';
import { useHosts } from '../../hooks/useHosts';

export default function HostsPage() {
    const { data, loading, error } = useHosts();

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 3 }}>
                {data.map((host) => (
                    <Box key={host.id} sx={{ width: { xs: '100%', sm: 'calc(50% - 12px)', md: 'calc(33.33% - 16px)' } }}>
                        <HostCard host={host} />
                    </Box>
                ))}
            </Box>
        </Container>
    );
}