import { Card, CardContent, Typography, Button, CardActions } from '@mui/material';
import { Link } from 'react-router-dom';
import {Host} from "../../api/types/Host";

interface Props {
    host: Host;
}

export default function HostCard({ host }: Props) {
    return (
        <Card>
            <CardContent>
                <Typography variant="h6">{host.name} {host.surname}</Typography>
                <Typography color="textSecondary">Email: {host.email}</Typography>
                <Typography color="textSecondary">Country: {host.country?.name}</Typography>
            </CardContent>
            <CardActions>
                <Button size="small" component={Link} to={`/hosts/${host.id}`}>Details</Button>
            </CardActions>
        </Card>
    );
}