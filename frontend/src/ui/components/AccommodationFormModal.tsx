import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, MenuItem, Select, FormControl, InputLabel } from '@mui/material';
import { useState, useEffect } from 'react';
import { AccommodationRequest } from '../../api/accommodationRepository';
import { useHosts } from '../../hooks/useHosts';
import {Accommodation} from "../../api/types/Accommodation";

interface Props {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: AccommodationRequest) => void;
    initialData?: Accommodation | null;
}

export default function AccommodationFormModal({ open, onClose, onSubmit, initialData }: Props) {
    const { data: hosts } = useHosts();
    const [formData, setFormData] = useState<AccommodationRequest>({
        name: '',
        category: '',
        condition: '',
        numRooms: 0,
        hostId: 0,
        rented: false,
        workStartDate: '',
    });

    useEffect(() => {
        if (initialData) {
            setFormData({
                name: initialData.name,
                category: initialData.category,
                condition: initialData.condition,
                numRooms: initialData.numRooms,
                hostId: initialData.host?.id || 0,
                rented: initialData.rented,
                workStartDate: initialData.workStartDate || '',
            });
        } else {
            setFormData({
                name: '',
                category: '',
                condition: '',
                numRooms: 0,
                hostId: 0,
                rented: false,
                workStartDate: '',
            });
        }
    }, [initialData, open]);

    const handleSubmit = () => {
        onSubmit(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
            <DialogTitle>{initialData ? 'Edit Accommodation' : 'Add Accommodation'}</DialogTitle>
            <DialogContent>
                <TextField
                    label="Name"
                    fullWidth
                    margin="dense"
                    value={formData.name}
                    onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        value={formData.category}
                        label="Category"
                        onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                    >
                        <MenuItem value="ROOM">ROOM</MenuItem>
                        <MenuItem value="HOUSE">HOUSE</MenuItem>
                        <MenuItem value="FLAT">FLAT</MenuItem>
                        <MenuItem value="APARTMENT">APARTMENT</MenuItem>
                        <MenuItem value="HOTEL">HOTEL</MenuItem>
                        <MenuItem value="MOTEL">MOTEL</MenuItem>
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Condition</InputLabel>
                    <Select
                        value={formData.condition}
                        label="Condition"
                        onChange={(e) => setFormData({ ...formData, condition: e.target.value })}
                    >
                        <MenuItem value="GOOD">GOOD</MenuItem>
                        <MenuItem value="BAD">BAD</MenuItem>
                    </Select>
                </FormControl>
                <TextField
                    label="Number of rooms"
                    type="number"
                    fullWidth
                    margin="dense"
                    value={formData.numRooms}
                    onChange={(e) => setFormData({ ...formData, numRooms: parseInt(e.target.value) })}
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        value={formData.hostId}
                        label="Host"
                        onChange={(e) => setFormData({ ...formData, hostId: Number(e.target.value) })}
                    >
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.name} {host.surname}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    label="Work start date (YYYY-MM-DDTHH:mm:ss)"
                    fullWidth
                    margin="dense"
                    value={formData.workStartDate}
                    onChange={(e) => setFormData({ ...formData, workStartDate: e.target.value })}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained">Save</Button>
            </DialogActions>
        </Dialog>
    );
}