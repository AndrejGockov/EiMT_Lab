import { useState } from 'react';
import {
    Container,
    Box,
    CircularProgress,
    Alert,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    Typography,
    Paper
} from '@mui/material';
import AccommodationCard from '../components/AccommodationCard';
import { useAccommodations } from '../../hooks/useAccommodations';
import {Accommodation} from "../../api/types/Accommodation";


export default function AccommodationsPage() {
    const { data, loading, error } = useAccommodations();
    const [selectedAccommodation, setSelectedAccommodation] = useState<Accommodation | null>(null);
    const [open, setOpen] = useState(false);

    const handleOpenModal = (accommodation: Accommodation) => {
        setSelectedAccommodation(accommodation);
        setOpen(true);
    };

    const handleCloseModal = () => {
        setOpen(false);
        setSelectedAccommodation(null);
    };

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        // Main body
        <Container>
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 3 }}>
                {data.map((acc) => (
                    <Box
                        key={acc.id}
                        sx={{ width: { xs: '100%', sm: 'calc(50% - 12px)', md: 'calc(33.33% - 16px)' } }}
                        onClick={() => handleOpenModal(acc)}
                        style={{ cursor: 'pointer' }}
                    >
                        <AccommodationCard accommodation={acc} />
                    </Box>
                ))}
            </Box>

            {/* Card with details*/}
            <Dialog open={open} onClose={handleCloseModal} maxWidth="sm" fullWidth>
                <DialogTitle>
                    {selectedAccommodation?.name}
                </DialogTitle>
                <DialogContent dividers>
                    {selectedAccommodation && (
                        <Paper sx={{ p: 2 }} elevation={0}>
                            <Typography variant="body1"><strong>Category:</strong> {selectedAccommodation.category}</Typography>
                            <Typography variant="body1"><strong>Condition:</strong> {selectedAccommodation.condition}</Typography>
                            <Typography variant="body1"><strong>Number of rooms:</strong> {selectedAccommodation.numRooms}</Typography>
                            <Typography variant="body1"><strong>Rented:</strong> {selectedAccommodation.rented ? 'Yes' : 'No'}</Typography>
                            {selectedAccommodation.workStartDate && (
                                <Typography variant="body1">
                                    <strong>Work start date:</strong> {new Date(selectedAccommodation.workStartDate).toLocaleDateString()}
                                </Typography>
                            )}
                            {selectedAccommodation.host && (
                                <>
                                    <Typography variant="h6" sx={{ mt: 2 }}>Host</Typography>
                                    <Typography variant="body2">Name: {selectedAccommodation.host.name} {selectedAccommodation.host.surname}</Typography>
                                    <Typography variant="body2">Email: {selectedAccommodation.host.email}</Typography>
                                    <Typography variant="body2">Country: {selectedAccommodation.host.country?.name}</Typography>
                                </>
                            )}
                        </Paper>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseModal} variant="contained">Close</Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
}