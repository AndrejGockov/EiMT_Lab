import { Box, Container, Typography } from '@mui/material';

const Index = () => {
    return (
        <Box sx={{ m: 0, p: 0 }}>
            <Container maxWidth='xl' sx={{ mt: 3, py: 3 }}>
                <Typography variant='h4' gutterBottom>
                    Accommodation website
                </Typography>
            </Container>
        </Box>
    );
};

export default Index;
