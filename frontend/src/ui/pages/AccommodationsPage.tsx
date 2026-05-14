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
    Paper,
    IconButton,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import AccommodationCard from '../components/AccommodationCard';
import AccommodationFormModal from '../components/AccommodationFormModal';
import { useAccommodations } from '../../hooks/useAccommodations';
import {
    accommodationRepository,
    AccommodationRequest,
} from '../../api/accommodationRepository';
import { useAuth } from '../../hooks/useAuth';
import { Accommodation } from '../../api/types/Accommodation';

export default function AccommodationsPage() {
    const { data, loading, error, refetch } = useAccommodations();
    const { isAdmin } = useAuth();

    // Detail modal state
    const [selectedAccommodation, setSelectedAccommodation] = useState<Accommodation | null>(null);
    const [detailOpen, setDetailOpen] = useState(false);

    // Form modal state (for create/edit)
    const [formModalOpen, setFormModalOpen] = useState(false);
    const [editingAcc, setEditingAcc] = useState<Accommodation | null>(null);

    // Open detail modal
    const handleOpenDetail = (accommodation: Accommodation) => {
        setSelectedAccommodation(accommodation);
        setDetailOpen(true);
    };

    const handleCloseDetail = () => {
        setDetailOpen(false);
        setSelectedAccommodation(null);
    };

    // Add accommodation (admin only)
    const handleAdd = () => {
        setEditingAcc(null);
        setFormModalOpen(true);
    };

    // Edit accommodation (opens form modal with existing data)
    const handleEdit = () => {
        if (selectedAccommodation) {
            setEditingAcc(selectedAccommodation);
            setFormModalOpen(true);
            setDetailOpen(false); // close detail modal while editing
        }
    };

    // Delete accommodation
    const handleDelete = async () => {
        if (!selectedAccommodation) return;
        if (window.confirm(`Delete "${selectedAccommodation.name}"?`)) {
            try {
                await accommodationRepository.delete(selectedAccommodation.id);
                refetch();           // refresh list
                handleCloseDetail(); // close detail modal
            } catch (err) {
                alert('Delete failed');
            }
        }
    };

    // Submit create/update
    const handleSubmit = async (formData: AccommodationRequest) => {
        try {
            if (editingAcc) {
                await accommodationRepository.update(editingAcc.id, formData);
            } else {
                await accommodationRepository.create(formData);
            }
            refetch();            // refresh list
            setFormModalOpen(false);
            setEditingAcc(null);
        } catch (err) {
            alert('Operation failed');
        }
    };

    if (loading) return <CircularProgress />;
    if (error) return <Alert severity="error">{error}</Alert>;

    return (
        <Container>
            {/* Add button – visible only to admin */}
            {isAdmin() && (
                <Button
                    variant="contained"
                    startIcon={<AddIcon />}
                    onClick={handleAdd}
                    sx={{ mb: 2 }}
                >
                    Add Accommodation
                </Button>
            )}

            {/* Grid of cards */}
            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 3 }}>
                {data.map((acc) => (
                    <Box
                        key={acc.id}
                        sx={{
                            width: { xs: '100%', sm: 'calc(50% - 12px)', md: 'calc(33.33% - 16px)' },
                            cursor: 'pointer',
                        }}
                        onClick={() => handleOpenDetail(acc)}
                    >
                        <AccommodationCard accommodation={acc} />
                    </Box>
                ))}
            </Box>

            {/* Detail Modal */}
            <Dialog open={detailOpen} onClose={handleCloseDetail} maxWidth="sm" fullWidth>
                <DialogTitle>
                    {selectedAccommodation?.name}
                    {/* Admin action buttons inside title bar */}
                    {isAdmin() && selectedAccommodation && (
                        <Box sx={{ display: 'inline-block', float: 'right' }}>
                            <IconButton onClick={handleEdit} size="small" color="primary">
                                <EditIcon />
                            </IconButton>
                            <IconButton onClick={handleDelete} size="small" color="error">
                                <DeleteIcon />
                            </IconButton>
                        </Box>
                    )}
                </DialogTitle>
                <DialogContent dividers>
                    {selectedAccommodation && (
                        <Paper sx={{ p: 2 }} elevation={0}>
                            <Typography variant="body1">
                                <strong>Category:</strong> {selectedAccommodation.category}
                            </Typography>
                            <Typography variant="body1">
                                <strong>Condition:</strong> {selectedAccommodation.condition}
                            </Typography>
                            <Typography variant="body1">
                                <strong>Number of rooms:</strong> {selectedAccommodation.numRooms}
                            </Typography>
                            <Typography variant="body1">
                                <strong>Rented:</strong> {selectedAccommodation.rented ? 'Yes' : 'No'}
                            </Typography>
                            {selectedAccommodation.workStartDate && (
                                <Typography variant="body1">
                                    <strong>Work start date:</strong>{' '}
                                    {new Date(selectedAccommodation.workStartDate).toLocaleDateString()}
                                </Typography>
                            )}
                            {selectedAccommodation.host && (
                                <>
                                    <Typography variant="h6" sx={{ mt: 2 }}>
                                        Host
                                    </Typography>
                                    <Typography variant="body2">
                                        Name: {selectedAccommodation.host.name}{' '}
                                        {selectedAccommodation.host.surname}
                                    </Typography>
                                    <Typography variant="body2">
                                        Email: {selectedAccommodation.host.email}
                                    </Typography>
                                    <Typography variant="body2">
                                        Country: {selectedAccommodation.host.country?.name}
                                    </Typography>
                                </>
                            )}
                        </Paper>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDetail} variant="contained">
                        Close
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Create/Edit Form Modal */}
            <AccommodationFormModal
                open={formModalOpen}
                onClose={() => {
                    setFormModalOpen(false);
                    setEditingAcc(null);
                }}
                onSubmit={handleSubmit}
                initialData={editingAcc}
            />
        </Container>
    );
}