import { Card, CardContent, Typography, Button, CardActions } from '@mui/material';
import { Link } from 'react-router-dom';
import {Accommodation} from "../../api/types/Accommodation";

interface Props {
    accommodation: Accommodation;
}

export default function AccommodationCard({ accommodation }: Props) {
    return (
        <Card>
            <CardContent>
                <Typography variant="h6">{accommodation.name}</Typography>
                <Typography color="textSecondary">Category: {accommodation.category}</Typography>
                <Typography color="textSecondary">Rooms: {accommodation.numRooms}</Typography>
                <Typography color="textSecondary">Condition: {accommodation.condition}</Typography>
                {accommodation.host && (
                    <Typography variant="body2">Host: {accommodation.host.name} {accommodation.host.surname}</Typography>
                )}
            </CardContent>
            <CardActions>
                <Button size="small" component={Link} to={`/accommodations/${accommodation.id}`}>Details</Button>
            </CardActions>
        </Card>
    );
}