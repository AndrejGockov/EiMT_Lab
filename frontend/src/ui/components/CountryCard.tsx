import { Card, CardContent, Typography, Button, CardActions } from '@mui/material';
import { Link } from 'react-router-dom';
import {Country} from "../../api/types/Accommodation";

interface Props {
    country: Country;
}

export default function CountryCard({ country }: Props) {
    return (
        <Card>
            <CardContent>
                <Typography variant="h6">{country.name}</Typography>
            </CardContent>
            <CardActions>
                <Button size="small" component={Link} to={`/countries/${country.id}`}>Details</Button>
            </CardActions>
        </Card>
    );
}